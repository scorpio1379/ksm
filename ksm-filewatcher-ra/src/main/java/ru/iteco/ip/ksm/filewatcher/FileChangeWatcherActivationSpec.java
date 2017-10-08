package ru.iteco.ip.ksm.filewatcher;


import javax.resource.ResourceException;
import javax.resource.spi.Activation;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.InvalidPropertyException;
import javax.resource.spi.ResourceAdapter;

/**
 * Created by Scorpio on 06.10.2017.
 * утащено отсюда http://robertpanzer.github.io/blog/2014/inboundra-nointfmdbs.html
 */
@Activation(messageListeners = FileChangeWatcher.class)
public class FileChangeWatcherActivationSpec implements ActivationSpec {
    private ResourceAdapter resourceAdapter;

    private String dir;

    Class<?> beanClass;

    @Override
    public void validate() throws InvalidPropertyException {

    }

    @Override
    public ResourceAdapter getResourceAdapter() {
        return this.resourceAdapter;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter resourceAdapter) throws ResourceException {
        this.resourceAdapter = resourceAdapter;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
