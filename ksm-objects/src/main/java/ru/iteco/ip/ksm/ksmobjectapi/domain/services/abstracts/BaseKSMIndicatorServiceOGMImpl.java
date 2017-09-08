package ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts;


import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMIndicator;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Scorpio on 21.06.2017.
 */
public class BaseKSMIndicatorServiceOGMImpl<INDICATOR extends BaseKSMIndicator<INDICATOR>>
        extends BaseKSMObjectServiceOGMImpl<BaseKSMIndicator<INDICATOR>>
implements BaseKSMIndicatorService<INDICATOR>{

    protected KSMCI attachedToCI(BaseKSMIndicator indicator){
        String cs = "MATCH (indicator{ksmObjId:{indicatorKsmObjId}})-[r{ksmIndicatorToCiRelationShipType:{attachedRelationtype}}]->(ci:KSMBaseCI)  return ci";
        HashMap<String, Object> params = new HashMap<>();
        String attachedRelationtype = indicator.getKsmIndicatorToCiRelationType().name();
        String indicatorKsmObjId = indicator.getKsmObjId();
        params.put("attachedRelationtype" , attachedRelationtype);
        params.put("indicatorKsmObjId" , indicatorKsmObjId);
        Iterable<KSMCI> ksmcisItrbl = session.query(KSMCI.class, cs, params);
        List<KSMCI> ksmCis = new ArrayList<>();
        ksmcisItrbl.forEach(ksmCis::add);
        logger.info("!!!");
        return null;

    }

    @Override
    public Class getEntityType() {
        return BaseKSMIndicator.class;
    }
}
