package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
public class HI_Obj  extends AIndicator implements HI{

    @Relationship(type = "AttachedHI")
    private CI relatedCi;


    @Override
    public CI getRelatedCI() {
        return this.relatedCi;
    }

    @Override
    public void setRelatedCI(CI ci) {
        this.relatedCi = ci;

    }

    @Override
    public void setRelatedCI(String ciKsmObjId) {
        /*TODO: IMPLEMENT!!*/
        logger.error("Method setRelatedCI(String ciKsmObjId) in HI_Obj NOT Implemented YET");
        throw new NotImplementedException();

    }
}
