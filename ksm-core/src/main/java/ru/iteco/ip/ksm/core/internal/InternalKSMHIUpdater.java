package ru.iteco.ip.ksm.core.internal;

import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMHI;
import ru.iteco.ip.ksm.shared.ksmevent.KSMEvent;

/**
 * Created by Scorpio on 27.09.2017.
 */
public interface InternalKSMHIUpdater extends InternalKSMIndicatorUpdater<InternalKSMHI> {
    void updateHI(KSMEvent ksmEvent);

}
