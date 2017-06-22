package ru.iteco.ip.ksm.ksmobjects.cis;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCIOGMServiceImpl;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;

import javax.ejb.Stateless;

/**
 * Created by Scorpio on 21.06.2017.
 */
@Stateless
public class KSMCIOGMServiceImpl<T extends KSMBaseCI<T>> extends KSMBaseCIOGMServiceImpl<KSMBaseObject<KSMBaseCI>> implements KSMCIService{


    @Override
    public Class getEntityType() {
        return KSMCI.class;
    }


}
