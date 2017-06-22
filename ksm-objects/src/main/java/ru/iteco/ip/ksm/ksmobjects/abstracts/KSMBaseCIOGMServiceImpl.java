package ru.iteco.ip.ksm.ksmobjects.abstracts;

import java.util.UUID;

/**
 * Created by Scorpio on 21.06.2017.
 */
/*T extends KSMBaseCI <T>    KSMBaseObject<KSMBaseCI<T>>*/
public class KSMBaseCIOGMServiceImpl<T extends KSMBaseObject<T>> implements BaseCIService<T>{


    @Override
    public Class<T> getEntityType() {
        return null;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public T find(Long id) {
        return null;
    }

    @Override
    public T findByKsmObjId(UUID ksmObjId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByKsmObjId(UUID ksmObjId) {

    }

    @Override
    public T createOrUpdate(T object) {
        return null;
    }
}
