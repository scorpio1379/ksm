package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

import java.util.UUID;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface AbstractSrvc<T extends IAObject> {
    Iterable<T> findAll();

    T find(Long id);

    T find(String id);

    T findByKsmObjId(String ksmObjId);

    T findByKsmObjId(UUID ksmObjId);

    void delete(Long id);

    void deleteByKsmObjId(UUID ksmObjId);

    T createOrUpdate(T object);
}
