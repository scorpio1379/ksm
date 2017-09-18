package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.HI_Obj;

import javax.ejb.Stateless;

/**
 * Created by Scorpio on 09.09.2017.
 */
@Stateless
public class HISrvcImpl extends AbstractSrvcOGMImpl<HI> implements HISrvc {
    @Override
    public Class getEntityType() {
        return HI_Obj.class;
    }
}
