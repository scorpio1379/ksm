package ru.iteco.ip.ksm.ksmobjects.cis;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMCI extends KSMBaseCI<KSMCI> {


    public KSMCI(String uuid, String name) {
        super(uuid, name);
    }

}
