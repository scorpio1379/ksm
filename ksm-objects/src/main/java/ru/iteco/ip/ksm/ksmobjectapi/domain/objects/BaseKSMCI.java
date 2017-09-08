package ru.iteco.ip.ksm.ksmobjectapi.domain.objects;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public abstract class BaseKSMCI<T extends BaseKSMCI<T>> extends BaseKSMObject<BaseKSMCI<T>> implements IBaseKSMCI{
    protected String name;
    protected String description;
    protected KSMCIType ksmCiType;
    protected String statusKPIksmObjId;



    /**
     * список родительских КЭ , которые зависят  от данного КЭ по связи типа DEPENDENT_ON
     */
    @Relationship(type = "DEPENDS_ON_KSM_RELATIONSHIP",direction = "INCOMING")
    protected List<BaseKSMCI<?>> ascendentCis = new ArrayList<>();
    /**
     * список дочерних КЭ , от которых зависит данный КЭ по типу связи DEPENDENT_ON
     */
    @Relationship(type = "DEPENDS_ON_KSM_RELATIONSHIP",direction = "OUTGOING")
    protected  List<BaseKSMCI<?>> dependentCis = new ArrayList<>();

    /**
     * Основные показатели эффективности  которые "привязаные" к данному КЭ
     */
    //@Relationship(type = "KPI_TO_CI_KSM_RELATIONSHIP" , direction = "INCOMING")
    //protected Set<TstAttachedKPIRelationShip> attachedKPIRelationShipSet;

    //@Relationship(type = "KPI_TO_CI_KSM_RELATIONSHIP" , direction = "INCOMING")
    //private Set<AttachedKPIRelationShip> attachedKPIRelationShips;

    //@Relationship(type = "KPI_TO_CI_KSM_RELATIONSHIP", direction = "INCOMING")
    //private Set<KSMKPI> attachedKPIs;



    public BaseKSMCI(){
        super();
        this.ksmObjType = KSMObjectType.CI;
    }

    public KSMObjectType getObjectType(){
        return this.ksmObjType;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KSMCIType getKsmCiType() {
        return ksmCiType;
    }


    public String getStatusKPIksmObjId() {
        return statusKPIksmObjId;
    }

    public void setStatusKPIksmObjId(String statusKPIksmObjId) {
        this.statusKPIksmObjId = statusKPIksmObjId;
    }

    /**
     * связывает экземпляр класса с целевым CI в качестве дочернего элемента связью DEPENDENT_ON
     * @param targetCI
     */
    public void addDependentCI(BaseKSMCI<T> targetCI) {
        this.dependentCis.add(targetCI);
    }

    /**
     * связывает экземпляр класса с целевым CI в качестве родительского элемента связью DEPENDENT_ON
     * @param targetCI
     */
    public void addAscendentCis(BaseKSMCI<T> targetCI){
        this.ascendentCis.add(targetCI);
    }





}
