package ru.iteco.ip.ksm.ksmobjects.cis;

import ru.iteco.ip.ksm.ksmobjects.abstracts.BaseCIService;

import javax.ejb.Local;

/**
 * Created by Scorpio on 21.06.2017.
 */
@Local
public interface KSMCIService {
    KSMCI craeteRegularCI(String uuid , String name, KSMCIType type);
    KSMCI createServiceCI(String uuid , String name);

}
