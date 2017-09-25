package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
//@NodeEntity(label = "KSMHI")
public class HI_Obj  extends AIndicator implements HI{

    @Relationship(type = "AttachedHI")
    public CI relatedCI;
    @Relationship(type = "AttachedHI")
    public AttachedHI attachedHIRelationShip;



    private String hiType;

    public HI_Obj() {
        super();
        this.indicatorType = KSMIndicatorType.HI;
    }

    @Override
    public CI getRelatedCI() {
        return this.relatedCI;
    }

    @Override
    public void setRelatedCI(CI ci) {
        this.relatedCI = ci;

    }

    @Override
    public void setRelatedCI(String ciKsmObjId) {
        /*TODO: IMPLEMENT!!*/
        logger.error("Method setRelatedCI(String ciKsmObjId) in HI_Obj NOT Implemented YET");
        throw new NotImplementedException();

    }

    @Override
    public String getHiType() {
        return hiType;
    }
    @Override
    public void setHiType(String hiType) {
        this.hiType = hiType;
    }
    @Override
    public AttachedHI getAttachedHIRelationShip() {
        return attachedHIRelationShip;
    }
    @Override
    public void setAttachedHIRelationShip(AttachedHI attachedHIRelationShip) {
        this.attachedHIRelationShip = attachedHIRelationShip;
    }
}
