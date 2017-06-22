package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.annotations.KSMObjectsOGMSession;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scorpio on 20.06.2017.
 */
public abstract class BaseOGMServiceImpl<T extends KSMBaseObject> implements BaseOGMService<T>  {
    @Inject @KSMObjectsOGMSession
    protected Session session;
    @Inject @DefaultKSMLogger
    protected Logger logger;

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }



    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public T findByKsmObjId(UUID ksmObjId){
        String cypherString = "match (ksmObject{ksmObjId:"+ ksmObjId.toString()+"}) return ksmObject";
        Iterable<T> ksmObjectsIterable = session.query(getEntityType(), cypherString, Collections.EMPTY_MAP);
        List<T> ksmObjects = new ArrayList<>();
        ksmObjectsIterable.forEach(ksmObjects::add);
        if (ksmObjects.size() == 0) {
            logger.error("нет ни одного узла в Бд со свойством ksmObjId равным {}" ,ksmObjId.toString() );
            throw new ArrayIndexOutOfBoundsException("there is NO node in GDB with property named ksmObjId with value equal to  " +ksmObjId.toString() );

        } else if (ksmObjects.size() == 1){
            T tmpKsmObj = ksmObjects.get(0);
            logger.debug( " cypher query {} return one node with type {}" , cypherString , tmpKsmObj.getKSMObjType());
            return tmpKsmObj;
        }else{
            logger.error("существует более одного узда БД со свойством ksmObjId равным {}" ,ksmObjId.toString() );
            throw new ArrayIndexOutOfBoundsException("there are many node in GDB with property named ksmObjId with value equal to  " +ksmObjId.toString() );
        }
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public void deleteByKsmObjId(UUID ksmObjId) {
        session.delete(findByKsmObjId(ksmObjId));
    }


    @Override
    public T createOrUpdate(T ksmObject) {
        session.save(ksmObject, DEPTH_ENTITY);
        return find(ksmObject.getId());
    }
    public abstract Class<T> getEntityType();
}
