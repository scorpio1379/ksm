package ru.iteco.ip.ksm.core.internal.ksmobjects.impl;

import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMCI;

import javax.ejb.Stateless;

/**
 * Created by Scorpio on 25.09.2017.
 */
@Stateless(name = "InternalKSMCIEJB")
public class InternalKSMCIBean implements InternalKSMCI {
    public InternalKSMCIBean() {
    }
}
