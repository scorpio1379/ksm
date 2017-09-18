package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
public class CI_Obj extends AObject implements CI , EditableCI {
    /*TODO: точно определиться где надо определять значения по умолчанию*/


    protected String description;
    protected KSMCIType ksmCiType;
    protected String statusKPIksmObjId;

    @Relationship(type = "AttachedHI",direction = "INCOMING")
    public Set<AttachedHI> attachedHISet;
    @Relationship(type = "AttachedKPI" ,direction = "INCOMING")
    public Set<AttachedKPI> attachedKPISet;
    @Relationship(type = "LinkedCI" ,direction = "BOTH")
    protected Set<LinkedCIRelationShip> dependentCis;
    @Relationship(type = "LinkedCI" ,direction = "BOTH")
    protected Set<LinkedCIRelationShip> parentCis;

    @Relationship(type = "AttachedKPI" ,direction = "INCOMING")
    public Set<KPI> attachedKPISet1;


    @Inject
    @DefaultKSMLogger
    protected Logger logger;

    public CI_Obj() {
        super();
        /*
        this.attachedHISet = Collections.emptySet();
        this.attachedKPISet = Collections.emptySet();
        this.dependentCis = Collections.emptySet();
        this.parentCis = Collections.emptySet();
        */
        this.ksmObjType = KSMObjectType.CI;
    }

    private CI_Obj(Builder builder) {
        super();
        /*
        this.attachedHISet = Collections.emptySet();
        this.attachedKPISet = Collections.emptySet();
        this.dependentCis = Collections.emptySet();
        this.parentCis = Collections.emptySet();
        */
        this.ksmObjType = KSMObjectType.CI;
        this.description = builder.description;
        this.name = builder.name;
        this.ksmCiType = builder.ksmCiType;
        this.statusKPIksmObjId = builder.statusKPIksmObjId;
        this.ksmObjId = builder.ksmObjId;


    }

    public static class Builder {
        private final String name;
        private static final KSMObjectType ksmObjType = KSMObjectType.CI;


        private String description = "";
        private KSMCIType ksmCiType = KSMCIType.REGULAR;
        private String statusKPIksmObjId = "";
        private String ksmObjId = "00000000-0000-0000-0000-000000000000";

        //private Set<AttachedHI> attachedHISet = Collections.emptySet();

        //private Set<AttachedKPI> attachedKPISet = Collections.emptySet();

       // private Set<LinkedCI> dependentCi = Collections.emptySet();


        public Builder(String ciName){
            this.name = ciName;
        }

        public Builder description( String description){
            this.description = description;
            return this;
        }
        public Builder ksmCiType( KSMCIType ksmCiType){
            this.ksmCiType = ksmCiType;
            return this;
        }
        public Builder statusKPIksmObjId ( String statusKPIksmObjId){
            this.statusKPIksmObjId = statusKPIksmObjId;
            return this;
        }
        public Builder ksmObjId ( String ksmObjId){
            this.ksmObjId = ksmObjId;
            return this;
        }
        public CI_Obj build(){
            return new CI_Obj(this);
        }


    }





    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public KSMCIType getKsmCiType() {
        return ksmCiType;
    }
    @Override
    public void setKsmCiType(KSMCIType ksmCiType) {
        this.ksmCiType = ksmCiType;
    }
    @Override
    public String getStatusKPIksmObjId() {
        return statusKPIksmObjId;
    }
    @Override
    public void setStatusKPIksmObjId(String statusKPIksmObjId) {
        this.statusKPIksmObjId = statusKPIksmObjId;
    }
    @Override
    public Set<AttachedHI> getAttachedHISet() {
        return attachedHISet;
    }
    @Override
    public void setAttachedHISet(Set<AttachedHI> attachedHISet) {
        this.attachedHISet = attachedHISet;
    }
    @Override
    public Set<AttachedKPI> getAttachedKPISet() {
        return attachedKPISet;
    }
    @Override
    public void setAttachedKPISet(Set<AttachedKPI> attachedKPISet) {
        this.attachedKPISet = attachedKPISet;
    }
    @Override
    public Set<LinkedCIRelationShip> getDependentCi() {
        return dependentCis;
    }
    @Override
    public void setDependentCi(Set<LinkedCIRelationShip> dependentCis) {
        this.dependentCis = dependentCis;
    }
}
