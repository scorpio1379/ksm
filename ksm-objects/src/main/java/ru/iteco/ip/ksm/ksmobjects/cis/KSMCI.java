package ru.iteco.ip.ksm.ksmobjects.cis;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMCI<T extends KSMCI<T>> extends KSMBaseCI<KSMCI<T>> {


    public KSMCI(String uuid, String name) {
        super(uuid, name);
    }

}
