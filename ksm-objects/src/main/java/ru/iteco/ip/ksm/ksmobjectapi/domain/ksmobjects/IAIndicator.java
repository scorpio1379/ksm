package ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface IAIndicator extends IAObject {
    String getValue();

   // void setValue(String value);

    String getStatus();

    //void setStatus(String status);

    KSMIndicatorType getIndicatorType();

    //void setIndicatorType(KSMIndicatorType indicatorType);

    void setDescription(String description);

    String getChgTimeStamp();

    void setChgTimeStamp(String chgTimeStamp);
}
