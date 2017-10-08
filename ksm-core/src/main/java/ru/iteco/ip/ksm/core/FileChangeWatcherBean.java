package ru.iteco.ip.ksm.core;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.core.annobations.Amend;
import ru.iteco.ip.ksm.core.annobations.Create;
import ru.iteco.ip.ksm.core.annobations.Delete;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import java.io.File;

/**
 * Created by Scorpio on 06.10.2017.
 * утащено отсюда http://robertpanzer.github.io/blog/2014/inboundra-nointfmdbs.html
 * https://github.com/robertpanzer/filesystemwatch-connector
 * ,activationConfig = {
@ActivationConfigProperty(
propertyName = "dir"
, propertyValue = "c:\\calculationrules\\")
}
 */
@MessageDriven(
        name = "FileChangeWatcherEJB"
        ,
        activationConfig = {
        @ActivationConfigProperty(
                propertyName = "dir"
                , propertyValue = "c:\\calculationrules\\")
        }

)
//@ResourceAdapter("ru.iteco.ip.ksm.ksm-filewatcher-ra-1.0-SNAPSHOT.rar")
@ResourceAdapter("ksm-app.ear#ksm-filewatcher-rar-1.0-SNAPSHOT.rar")
public class FileChangeWatcherBean implements FileChangeWatcher {
    @Inject
    @DefaultKSMLogger
    private Logger logger;


    @Create(".*\\.groovy")
    public void onNewCalculationScript(File f) {
        logger.warn("файл {} создан " ,f.getAbsolutePath());
    }

    @Amend(".*\\.groovy")
    public void onAmendedCalculationScript(File f){
        logger.warn("файл {} изменен " ,f.getAbsolutePath());
    }

    @Delete(".*\\.groovy")
    public void onDeleteTextFile(File f) {
        logger.warn("файл {} удвален " ,f.getAbsolutePath());
    }


}
