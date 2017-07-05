package ru.iteco.ip.ksm.ksmobjects.api;

import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;

/**
 * Created by administrator on 04.07.2017.
 */
public interface IKSMObjectApiBase {
    void test();
    KSMCI<KSMCIService> createService( String uuid , String name);

}
