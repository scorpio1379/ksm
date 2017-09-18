package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.HIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Scorpio on 09.09.2017.
 */
@Stateless
public class HIBuilderFactoryImpl implements HIBuilderFactory {
    @Inject
    private HIBuilder hiBuilder;
    @Override
    public HIBuilder getHIBuilder(CI ci) {
        ((InitableHIBuilder) this.hiBuilder).setCI(ci);
        return this.hiBuilder;
    }
}
