package ru.iteco.ip.ksm.core.internal.gdbseventhandlers;

import org.neo4j.graphdb.event.TransactionEventHandler;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMHI;

/**
 * Created by Scorpio on 02.10.2017.
 */
public interface HIStateChangeTransactionEventHandler extends TransactionEventHandler<InternalKSMHI> {
}
