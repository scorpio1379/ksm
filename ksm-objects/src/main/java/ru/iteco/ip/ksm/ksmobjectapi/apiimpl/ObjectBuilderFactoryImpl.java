package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.CIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;

import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 * Created by Scorpio on 07.09.2017.
 */
@Stateless
public class ObjectBuilderFactoryImpl<OBJECTTYPE>
        implements IObjectBuilderFactory<OBJECTTYPE> {



    @Inject
    public ObjectBuilderFactoryImpl(CIBuilder ciBuilder , KPIBuilder kpiBuilder) {


    }

    public ObjectBuilderFactoryImpl() {
    }
}
