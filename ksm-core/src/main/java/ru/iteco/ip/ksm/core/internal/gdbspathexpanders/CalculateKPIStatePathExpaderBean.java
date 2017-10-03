package ru.iteco.ip.ksm.core.internal.gdbspathexpanders;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilationFailedException;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.traversal.BranchState;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Scorpio on 02.10.2017.
 */
@Stateless(name = "CalculateKPIStatePathExpaderEJB")
@Default
public class CalculateKPIStatePathExpaderBean<STATE extends BranchState<STATE>> implements CalculateKPIStatePathExpader<STATE> {
    @Inject @DefaultKSMLogger
    private Logger logger;
    private List<Relationship> visitedRelationships = new ArrayList<>();
    private HashMap<String,String> affectedNodes = new HashMap<>();

    /*TODO: КОСТЫЛЬ!!!!! нужна зависимость от ломенного класса CI из ksmObjectApi*/
    private static final Label ksmCiObjectLabel = Label.label("CI_Obj") ;
    /*TODO: КОСТЫЛЬ!!!!! нужна зависимость от ломенного класса CI из ksmObjectApi*/
    private static final Label ksmKPIObjectLabel = Label.label("KPI_Obj") ;
    /*TODO: КОСТЫЛЬ!!!!! нужна зависимость от ломенного класса CI из ksmObjectApi*/
    private static final RelationshipType ci2ciRelationshipType = () -> "LinkedCI";
    /*TODO: КОСТЫЛЬ!!!!! нужна зависимость от ломенного класса CI из ksmObjectApi*/
    private static final RelationshipType attachedKPIRelationshipType = () -> "AttachedKPI";

    public CalculateKPIStatePathExpaderBean() {
    }


    @Override
    public Iterable<Relationship> expand(Path path, BranchState<STATE> branchState) {
        if (path.lastRelationship() != null){
            this.visitedRelationships.add(path.lastRelationship());
        }
        Node currentNode = path.endNode();
        String nodeName = (String) currentNode.getProperty("name");
		/* если текущая нода в траверсе не CI то возвращаем список всех исходящих связей типа (containment)*/
        if (!currentNode.hasLabel(ksmCiObjectLabel)){
            System.out.println("Not a CI Node , skipping");
            logger.warn("узел GDBS с id {}  не имеет label {} и вероятно не является KSMCI"
                    , String.valueOf(currentNode.getId())
                    , ksmCiObjectLabel.name()
            );
            return path.endNode().getRelationships(ci2ciRelationshipType , Direction.OUTGOING);
        }
		/* расчет связанных KPI может быть осуществлен ТОЛЬКО если все входящие связи типа (containment) были расчитаны*/
        if (isAllChildSibblingComputed(currentNode, ci2ciRelationshipType )){
            System.out.println("All Child Services was computed , now computing this CI " + nodeName );
            for (Relationship relation : currentNode.getRelationships(attachedKPIRelationshipType )){
				/* TODO:пока надо отфильтровать KPI но необходимо перейти на новый тип связи attached KPI И attached HI*/
                Node kpiNode = relation.getStartNode();
                if(kpiNode.hasLabel(ksmKPIObjectLabel)){
                    String groovyFileName ="";
                    if (kpiNode.hasProperty("rule")) {
                        groovyFileName = (String) kpiNode.getProperty("rule");
                    }
                    String filePath_str = "";
                    if((groovyFileName!=null) && (!groovyFileName.isEmpty())){
                        filePath_str = "META-INF\\" + groovyFileName;
                    }else{
                        filePath_str = "KPICalculatationRule.groovy";
                        groovyFileName = "KPICalculatationRule.groovy";
                    }
                    try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath_str)){
                        if(is!=null){
                            Class<?> scriptClass = new GroovyClassLoader().parseClass(is, groovyFileName);
                            GroovyObject groovyObject = (GroovyObject) scriptClass.newInstance() ;
                            Object[] argz = {currentNode,kpiNode};
                            groovyObject.invokeMethod("calculateKPI" ,argz);
                        }else{
                            throw new IOException("input stream is null for file " + filePath_str);
                        }


                    } catch (CompilationFailedException | InstantiationException | IllegalAccessException
                            | IllegalArgumentException | SecurityException | IOException e) {
                        System.out.println("something went WRONG!!!!!!!!!!!!!" + e.getMessage());
                        filePath_str = "META-INF\\KPICalculatationRule.groovy";
                        groovyFileName = "KPICalculatationRule.groovy";
                        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath_str)){
                            if (is!=null) {
                                Class<?> scriptClass = new GroovyClassLoader().parseClass(is, groovyFileName);
                                GroovyObject groovyObject = (GroovyObject) scriptClass.newInstance();
                                Object[] argz = { currentNode, kpiNode };
                                groovyObject.invokeMethod("calculateKPI", argz);
                                System.out.println("Default Method invoked");
                            }else{
                                throw new IOException("GiveUP , cant fix it, default script " + filePath_str + " is missing");
                            }
                            return path.endNode().getRelationships(ci2ciRelationshipType , Direction.OUTGOING);
                        } catch (InstantiationException | IllegalAccessException | IOException e1) {
                            System.out.println("something went Terrebly!! WRONG!!!!!!!!!!!!!" + e1.getMessage());
                            return path.endNode().getRelationships(ci2ciRelationshipType , Direction.OUTGOING);
                        }

                    }
                }
            }
            return  path.endNode().getRelationships(ci2ciRelationshipType , Direction.OUTGOING);
        }else{
            System.err.println("NOT all incoming related services was traversed for node  "  + nodeName+ ", switching path ");
            return Collections.<Relationship>emptyList();
        }
    }

    @Override
    public PathExpander<STATE> reverse() {
        return null;
    }

    private boolean isAllChildSibblingComputed(Node ci , RelationshipType relType){
        List<Relationship> inRels = new ArrayList<>();
        //String nodeName = (String) ci.getProperty("name");\
        /* добавлять только те входящие связи, в начале которых узлы из мэпы affectedNodes, если она не пустая*/

        ci.getRelationships(relType ,Direction.INCOMING).forEach(inRel ->{
            if (inRel.getStartNode().hasProperty("ksmObjId")){
                if (this.affectedNodes.containsValue(inRel.getStartNode().getProperty("ksmObjId"))){
                    inRels.add(inRel);
                }
            }else{
                logger.warn("связь типа {} с id {} начинается из узла не имеющего ksmObjId" , inRel.getType() , inRel.getId());
            }

        });
        try {
            boolean isAllChildSibblingComputed = this.visitedRelationships.containsAll(inRels);
            System.out.println("All incoming relation is in visited list = " + isAllChildSibblingComputed );
            return isAllChildSibblingComputed;
        } catch (NullPointerException  e) {
            System.out.println("incoming relations list is Empty");
            return true;
        }
    }
    @Override
    public void reset(){
        this.visitedRelationships.clear();
        this.affectedNodes.clear();
    }

    @Override
    public PathExpander<STATE> setAffectedNodes(HashMap<String, String> affectedNodesMap) {
        this.affectedNodes = affectedNodesMap;
        return this ;
    }
}
