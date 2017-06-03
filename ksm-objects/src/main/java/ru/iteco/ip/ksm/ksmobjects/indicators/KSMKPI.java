package ru.iteco.ip.ksm.ksmobjects.indicators;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMKPI<T extends KSMKPI<T>> extends KSMBaseIndicator<KSMKPI<T>> {
    private String calculationRule;
    private String kpiType;
    private KSMBaseCI<?> attachedToCI;

    public KSMKPI() {
        super();
    }
}
