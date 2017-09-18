package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import org.neo4j.ogm.transaction.Transaction;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

import javax.ejb.Lock;
import javax.ejb.LockType;
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

    @Lock(LockType.WRITE)
    T createOrUpdate(T object);

    Transaction beginTransaction();

    Transaction beginTransaction(Transaction.Type type);

    Transaction beginTransaction(Transaction.Type type, Iterable<String> iterable);

    T instansiateFromEntityType() throws IllegalAccessException, InstantiationException;
}
