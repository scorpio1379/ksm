package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface EditableIAIndicator extends EditableAObj {
    String getValue();

    void setValue(String value);

    String getStatus();

    void setStatus(String status);

    KSMIndicatorType getIndicatorType();

    void setIndicatorType(KSMIndicatorType indicatorType);

    void setDescription(String description);

    String getDescription();

    CI getRelatedCI();
    void setRelatedCI(CI ci);
    void setRelatedCI(String ciKsmObjId);
}
