package ru.iteco.ip.ksm.ksmobjects.cis;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCIOGMServiceImpl;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * Created by administrator on 04.07.2017.
 */
@LocalBean
@Default
@Stateless
public class KSMCIOGMServiceImpl
        extends KSMBaseCIOGMServiceImpl<KSMBaseObject<KSMBaseCI>>
        implements KSMCIService {
    @Override
    public Class getEntityType() {
        return KSMCI.class;
    }

    @Override
    public KSMCI craeteRegularCI(String uuid, String name , KSMCIType type) {
        KSMCI ci = new KSMCI(uuid, name , type );
        return ci;
    }

    @Override
    public KSMCI createServiceCI(String uuid, String name) {
        return null;
    }
}
