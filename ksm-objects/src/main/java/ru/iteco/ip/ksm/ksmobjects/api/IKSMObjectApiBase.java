package ru.iteco.ip.ksm.ksmobjects.api;

import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIType;

/**
 * Created by administrator on 04.07.2017.
 */
public interface IKSMObjectApiBase {
    void test();
    String createService( String ksmObjId , String name);
    String createCI(String ksmObjId , String name , String ciType);
    String createKPI(String ksmObjId , String name );
    String createHI(String ksmObjId , String name );
    String deleteKSMObjectByKsmID(String ksmObjId);
    void linkCIToCI(String startCIiD, String endCiId );

}
