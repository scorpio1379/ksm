package ru.iteco.ip.ksm.shared.configmanagers.configurations;

import javax.ejb.Stateless;

/**
 * Created by Scorpio on 02.10.2017.
 */
@Stateless(name = "KSMCoreApplicationConfigEJB")
public class KSMCoreApplicationConfigBean implements KSMCoreApplicationConfig {
    public KSMCoreApplicationConfigBean() {
    }
}
