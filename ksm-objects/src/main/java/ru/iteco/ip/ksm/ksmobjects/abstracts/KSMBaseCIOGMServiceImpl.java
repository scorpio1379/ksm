package ru.iteco.ip.ksm.ksmobjects.abstracts;

import java.util.UUID;

/**
 * Created by Scorpio on 21.06.2017.
 */
/*T extends KSMBaseCI <T>    KSMBaseObject<KSMBaseCI<T>>*/
public  class KSMBaseCIOGMServiceImpl<T extends KSMBaseObject<T>>
        extends BaseOGMServiceImpl<KSMBaseObject>
        implements BaseCIService<T>{

    @Override
    public Class getEntityType() {
        return  KSMBaseCI.class;
    }
}
