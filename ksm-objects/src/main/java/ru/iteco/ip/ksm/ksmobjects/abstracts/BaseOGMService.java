package ru.iteco.ip.ksm.ksmobjects.abstracts;

import java.util.UUID;

/**
 * Created by Scorpio on 20.06.2017.
 */
public interface BaseOGMService<T extends KSMBaseObject> {
    Iterable<T> findAll();

    T find(Long id);

    T findByKsmObjId( UUID ksmObjId);

    void delete(Long id);

    void deleteByKsmObjId(UUID ksmObjId);

    T createOrUpdate(T object);

}
