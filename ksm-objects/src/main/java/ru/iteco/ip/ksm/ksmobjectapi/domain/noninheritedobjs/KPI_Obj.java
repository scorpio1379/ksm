package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Scorpio on 05.09.2017.
 */
//@NodeEntity (label = "KSMKPI")
@NodeEntity
public class KPI_Obj extends AIndicator implements KPI , EditableKPI {
    private String kpiType;
    private String kpiCalcRuleId;

    @Relationship(type = "AttachedKPI" )
    public CI relatedCI;

    @Relationship(type = "AttachedKPI")
    public AttachedKPI attachedKPIRelationShip;

    public KPI_Obj() {
        super();
        this.indicatorType= KSMIndicatorType.KPI;
    }

    @Override
    public String getKpiType() {
        return kpiType;
    }
    @Override
    public void setKpiType(String kpiType) {
        this.kpiType = kpiType;
    }
    @Override
    public String getKpiCalcRuleId() {
        return kpiCalcRuleId;
    }
    @Override
    public void setKpiCalcRuleId(String kpiCalcRuleId) {
        this.kpiCalcRuleId = kpiCalcRuleId;
    }


    @Override
    public CI getRelatedCI() {
        return this.relatedCI;
    }

    @Override
    public void setRelatedCI(CI ci) {
        this.relatedCI=ci;

    }

    @Override
    public void setRelatedCI(String ciKsmObjId) {
        /*TODO: IMPLEMENT!!*/
        logger.error("Method setRelatedCI(String ciKsmObjId) in KPI_Obj NOT Implemented YET");
        throw new NotImplementedException();

    }

    @Override
    public AttachedKPI getAttachedKPIRelationShip() {
        return attachedKPIRelationShip;
    }

    @Override
    public void setAttachedKPIRelationShip(AttachedKPI attachedKPIRelationShip) {
        this.attachedKPIRelationShip = attachedKPIRelationShip;
    }
}
