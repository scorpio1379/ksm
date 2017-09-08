package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 08.09.2017.
 */
public interface InitableKPIBuilder extends KPIBuilder {
    void setCI(CI ci);
}
