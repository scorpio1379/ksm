package ru.iteco.ip.ksm.ksmobjects.cis;

import ru.iteco.ip.ksm.ksmobjects.abstracts.BaseCIService;

/**
 * Created by Scorpio on 21.06.2017.
 */
public interface KSMCIService<T extends KSMCI<T>> extends BaseCIService {
    T createOrUpdate(T object);
}
