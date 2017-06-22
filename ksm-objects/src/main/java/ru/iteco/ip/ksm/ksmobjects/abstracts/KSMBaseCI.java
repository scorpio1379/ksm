package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjects.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjects.indicators.KSMKPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMBaseCI<T extends KSMBaseCI <T> > extends KSMBaseObject<KSMBaseCI<T>> {
    protected String name;
    protected String description;
    private String ksmCiType;
    protected String statusKPIksmObjId;

    /**
     * Основные показатели эффективности  которые "привязаные" к данному КЭ
     */
    @Relationship(type = "KSM_DEPENDENT_ON_RELATION",direction = "INCOMING")
    private List<KSMKPI> attachedKPIs = new ArrayList<>();
    /**
     * список родительских КЭ , которые зависят  от данного КЭ по связи типа DEPENDENT_ON
     */
    @Relationship(type = "DEPENDENT_ON",direction = "INCOMING")
    private List<KSMBaseCI<?>> ascendentCis = new ArrayList<>();
    /**
     * список дочерних КЭ , от которых зависит данный КЭ по типу связи DEPENDENT_ON
     */
    @Relationship(type = "DEPENDENT_ON",direction = "OUTGOING")
    private List<KSMBaseCI<?>> dependentCis = new ArrayList<>();



    public KSMBaseCI(String uuid, String name) {
        super(uuid);
        this.ksmObjType = KSMObjectType.CI;
        this.name = name;
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

    public String getKsmCiType() {
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
    public void addDependentCI(KSMBaseCI<T>  targetCI) {
        this.dependentCis.add(targetCI);
    }

    /**
     * связывает экземпляр класса с целевым CI в качестве родительского элемента связью DEPENDENT_ON
     * @param targetCI
     */
    public void addAscendentCis(KSMBaseCI<T>  targetCI){
        this.ascendentCis.add(targetCI);
    }

    public void addKPI(KSMKPI kpi){
        this.attachedKPIs.add(kpi);
    }


}
