package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.ServiceBuilder;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by Scorpio on 18.09.2017.
 */
@Dependent
public class ServiceBuilderFactoryImpl implements ServiceBuilderFactory {
    @Inject
    private ServiceBuilder serviceBuilder;
    @Override
    public ServiceBuilder getServiceBuilder() {
        return this.serviceBuilder;
        //throw new NotImplementedException("method getServiceBuilder() not implemented yet");
    }
}
