package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.HIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;

/**
 * Created by Scorpio on 09.09.2017.
 */
public interface HIBuilderFactory extends IObjectBuilderFactory<HI>{
    HIBuilder getHIBuilder(CI ci);
}
