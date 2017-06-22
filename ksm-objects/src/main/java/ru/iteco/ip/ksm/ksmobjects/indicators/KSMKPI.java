package ru.iteco.ip.ksm.ksmobjects.indicators;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjects.KSMIndicatorType;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMKPI<T extends KSMKPI<T>> extends KSMBaseIndicator<KSMKPI<T>> {
    private String calculationRule;
    private String kpiType;
    @Relationship(type = "ATTACHED_KPI")
    private KSMBaseCI attachedToCI;

    public KSMKPI(String uuid , String name) {
        super(uuid , name);
        this.indicatorType = KSMIndicatorType.KPI;
    }

    public KSMBaseCI getAttachedToCI() {
        return attachedToCI;
    }

    public void setAttachedToCI(KSMBaseCI attachedToCI) {
        this.attachedToCI = attachedToCI;
    }
}
