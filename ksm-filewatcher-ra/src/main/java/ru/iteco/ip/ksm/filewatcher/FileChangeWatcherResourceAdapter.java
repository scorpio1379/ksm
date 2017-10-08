package ru.iteco.ip.ksm.filewatcher;


import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Scorpio on 06.10.2017.
 */
@Connector
public class FileChangeWatcherResourceAdapter implements ResourceAdapter {
    FileSystem fileSystem;

    WatchService watchService;

    Map<WatchKey, MessageEndpointFactory> listeners = new ConcurrentHashMap<>();

    Map<MessageEndpointFactory, Class<?>> endpointFactoryToBeanClass = new ConcurrentHashMap<>();

    private BootstrapContext bootstrapContext;

    public FileChangeWatcherResourceAdapter() {
    }

    @Override
    public void start(BootstrapContext bootstrapContext) throws ResourceAdapterInternalException {
        this.bootstrapContext = bootstrapContext;

        try {
            fileSystem = FileSystems.getDefault();
            watchService = fileSystem.newWatchService();
        } catch (IOException e) {
            throw new ResourceAdapterInternalException(e);
        }

        /*TODO: определить насколько безопастно порождать новую нитку , не отслеживая ее статус */
        new FileChangeWatchingThread(watchService, this).start();

    }

    @Override
    public void stop() {
        try {
            watchService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endpointActivation(
            MessageEndpointFactory messageEndpointFactory
            , ActivationSpec activationSpec)
            throws ResourceException {

        FileChangeWatcherActivationSpec fileChangeWatcherActivationSpec = (FileChangeWatcherActivationSpec) activationSpec;

        try {
            WatchKey watchKey = fileSystem.getPath(fileChangeWatcherActivationSpec.getDir()).register(
                    watchService
                    ,StandardWatchEventKinds.ENTRY_CREATE
                    ,StandardWatchEventKinds.ENTRY_DELETE
                    ,StandardWatchEventKinds.ENTRY_MODIFY
            );
            listeners.put(watchKey, messageEndpointFactory);

            // On TomEE the endpoint class is available via activationSpec.getBeanClass()
            // even though not JavaEE 7 compliant yet!
            endpointFactoryToBeanClass.put(
                    messageEndpointFactory
                    , fileChangeWatcherActivationSpec.getBeanClass() != null ? fileChangeWatcherActivationSpec.getBeanClass() : messageEndpointFactory.getEndpointClass());
        } catch (IOException e) {
            throw new ResourceException(e);
        }

    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory messageEndpointFactory, ActivationSpec activationSpec) {
        for (WatchKey watchKey: listeners.keySet()) {
            if (listeners.get(watchKey) == messageEndpointFactory) {
                listeners.remove(watchKey);
                break;
            }
        }
        endpointFactoryToBeanClass.remove(messageEndpointFactory);

    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] activationSpecs) throws ResourceException {
        return new XAResource[0];
    }


    public MessageEndpointFactory getListener(WatchKey watchKey) {
        return listeners.get(watchKey);
    }

    public BootstrapContext getBootstrapContext() {
        return bootstrapContext;
    }

    public Class<?> getBeanClass(MessageEndpointFactory endpointFactory) {
        return endpointFactoryToBeanClass.get(endpointFactory);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
