package ru.iteco.ip.ksm.ksmobjects.indicators;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicatorOGMServiceImpl;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;

/**
 * Created by Scorpio on 21.06.2017.
 */
public class KSMKPIOGMServiceImpl<KSMKPI extends KSMBaseIndicator<KSMKPI>> extends KSMBaseIndicatorOGMServiceImpl<KSMBaseIndicator<KSMKPI>> {
    @Override
    public Class getEntityType() {
        return ru.iteco.ip.ksm.ksmobjects.indicators.KSMKPI.class;
    }
}
