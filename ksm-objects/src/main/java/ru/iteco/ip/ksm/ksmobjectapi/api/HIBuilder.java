package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 09.09.2017.
 */
public interface HIBuilder extends ObjectBuilder<HIBuilder , HI> {
    /* общие для ksm indicator*/
    HIBuilder description (String description);
    HIBuilder value (String value);
    HIBuilder status (String status);
    HIBuilder ksmIndicatorType (KSMIndicatorType ksmIndicatorType);

    /* общие для KSMHI*/
    HIBuilder hiType  (String hiType);
}
