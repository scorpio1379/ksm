package ru.iteco.ip.ksm.core.internal.statechangeevents;

import java.io.Serializable;

/**
 * Created by Scorpio on 30.09.2017.
 */
public interface IndicatorStateChangeEventMessage<INDICATORTYPE> extends Serializable {
    String getKsmObjId();

    void setKsmObjId(String ksmObjId);

    String getStatus();

    void setStatus(String status);

    String getValue();

    void setValue(String value);

    String getChgTimeStamp();

    void setChgTimeStamp(String chgTimeStamp);
}
