package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjects.KSMIndicatorStatus;
import ru.iteco.ip.ksm.ksmobjects.KSMIndicatorType;
import ru.iteco.ip.ksm.ksmobjects.KSMObjectType;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public  abstract class KSMBaseIndicator<T extends KSMBaseIndicator<T>> extends KSMBaseObject<T> {
    protected String name;
    protected String value;
    protected KSMIndicatorStatus status;
    protected KSMIndicatorType indicatorType;

    public KSMBaseIndicator(String uuid , String name) {
        super(uuid);
        this.ksmObjType = KSMObjectType.INDICATOR;
        this.name = name;
    }
}
