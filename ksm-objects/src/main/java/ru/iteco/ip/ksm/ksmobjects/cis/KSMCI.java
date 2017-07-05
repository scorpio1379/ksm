package ru.iteco.ip.ksm.ksmobjects.cis;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMCI<T> extends KSMBaseCI<KSMCI<T>> {

    private  KSMCIType ciType;
    public KSMCI(String uuid, String name) {
        super(uuid, name);
        this.ciType = KSMCIType.REGULAR;
    }
    public KSMCI(String uuid, String name ,KSMCIType type) {
        super(uuid, name);
        this.ciType = type;
    }


}
