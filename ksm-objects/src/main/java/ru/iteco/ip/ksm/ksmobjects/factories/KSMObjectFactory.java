package ru.iteco.ip.ksm.ksmobjects.factories;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIType;

import javax.ejb.Local;

/**
 * Created by administrator on 04.07.2017.
 */
@Local
public interface KSMObjectFactory {
    KSMCI createService(String uuid , String name);
    KSMCI createKSMCI(String uuid , String name , KSMCIType ksmciType);
}
