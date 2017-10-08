package ru.iteco.ip.ksm.filewatcher;


import ru.iteco.ip.ksm.filewatcher.annobations.Amend;
import ru.iteco.ip.ksm.filewatcher.annobations.Create;
import ru.iteco.ip.ksm.filewatcher.annobations.Delete;

import javax.resource.spi.endpoint.MessageEndpoint;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.List;

/**
 * Created by Scorpio on 06.10.2017.
 */
public final class FileChangeWatchingThread extends Thread {
    private WatchService watchService;
    private FileChangeWatcherResourceAdapter resourceAdapter;

    FileChangeWatchingThread(WatchService watchService ,FileChangeWatcherResourceAdapter resourceAdapter ){
        this.resourceAdapter = resourceAdapter;
        this.watchService = watchService;
    }

    public void run() {
        while (true) {
            try {
                WatchKey watchKey = watchService.take();
                if (watchKey != null) {
                    dispatchEvents(watchKey.pollEvents(), resourceAdapter.getListener(watchKey));
                    watchKey.reset();
                }
            } catch (ClosedWatchServiceException e) {
                // Fine, we've been stopped
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatchEvents(List<WatchEvent<?>> events, MessageEndpointFactory messageEndpointFactory) {
        for (WatchEvent<?> event: events) {
            Path path = (Path) event.context();

            try {
                MessageEndpoint endpoint = messageEndpointFactory.createEndpoint(null);
                Class<?> beanClass = resourceAdapter.getBeanClass(messageEndpointFactory);
                for (Method m: beanClass.getMethods()) {
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())
                            && m.isAnnotationPresent(Create.class)
                            && path.toString().matches(m.getAnnotation(Create.class).value())) {
                        invoke(endpoint, m, path);
                    } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())
                            && m.isAnnotationPresent(Delete.class)
                            && path.toString().matches(m.getAnnotation(Delete.class).value())) {
                        invoke(endpoint, m, path);
                    } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())
                            && m.isAnnotationPresent(Amend.class)
                            && path.toString().matches(m.getAnnotation(Amend.class).value())) {
                        invoke(endpoint, m, path);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void invoke(final MessageEndpoint endpoint, final Method m, final Path path) throws WorkException {
        resourceAdapter.getBootstrapContext().getWorkManager().scheduleWork(new Work() {

            @Override
            public void run() {
                try {
                    Method endpointMethod = endpoint.getClass().getMethod(m.getName(), m.getParameterTypes());
                    endpoint.beforeDelivery(endpointMethod);

                    endpointMethod.invoke(endpoint, path.toFile());

                    endpoint.afterDelivery();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void release() {}
        });
    }


}
