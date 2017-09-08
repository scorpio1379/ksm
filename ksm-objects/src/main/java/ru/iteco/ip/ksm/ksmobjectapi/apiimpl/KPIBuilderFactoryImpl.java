package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Scorpio on 07.09.2017.
 */
@Stateless
public class KPIBuilderFactoryImpl extends ObjectBuilderFactoryImpl<KPI> implements KPIBuilderFactory {
    @Inject
    private KPIBuilder kpiBuilder;
    /*TODO: переделать фабрику с испоьзованием иньекций*/
    @Override
    public KPIBuilder getKPIBuilder() {
        return this.kpiBuilder;
    }

    @Override
    public KPIBuilder getKPIBuilder(CI ci) {
       ((InitableKPIBuilder) this.kpiBuilder).setCI(ci);
       return this.kpiBuilder;

    }
}
