package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public abstract class KSMBaseCI<T extends KSMBaseCI <T> > extends KSMBaseObject {
    protected String name;
    protected String description;
    protected String ksmCiType;
    protected String statusKPIksmObjId;
}
