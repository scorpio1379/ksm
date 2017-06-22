package ru.iteco.ip.ksm.shared.configmanagers.configurations.abstracts;

import ru.iteco.ip.ksm.shared.configmanagers.GDBType;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.GDBConfig;

import java.io.Serializable;

/**
 * Created by Scorpio on 11.06.2017.
 */
public  class  BaseGDBConfiguration<T> implements Serializable , GDBConfig{
    protected GDBType gdbType;

    public BaseGDBConfiguration() {
    }

    public GDBType getGdbType(){
        return this.gdbType;
    }

}
