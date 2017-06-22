package ru.iteco.ip.ksm.shared.configmanagers;

import ru.iteco.ip.ksm.shared.configmanagers.configurations.GDBConfig;

import javax.ejb.Local;

/**
 * Created by Scorpio on 11.06.2017.
 */
@Local
public interface KSMConfigurationManager {
    GDBConfig getGDBConfiguration();
}
