package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.HIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 09.09.2017.
 */
public interface InitableHIBuilder extends HIBuilder{
    void setCI(CI ci);
}
