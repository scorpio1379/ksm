package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.KPI_Obj;

import javax.ejb.Singleton;


/**
 * Created by Scorpio on 08.09.2017.
 */
@Singleton
public class KPISrvcImpl extends AbstractSrvcOGMImpl<KPI> implements KPISrvc{
    @Override
    public Class getEntityType() {
        return KPI_Obj.class;
    }
}
