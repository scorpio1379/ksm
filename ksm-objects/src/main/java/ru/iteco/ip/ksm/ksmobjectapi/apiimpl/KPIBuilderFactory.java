package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;

/**
 * Created by Scorpio on 07.09.2017.
 */
public interface KPIBuilderFactory extends IObjectBuilderFactory<KPI> {
    KPIBuilder getKPIBuilder();
    KPIBuilder getKPIBuilder(CI ci) throws NoSuchFieldException, IllegalAccessException;
}
