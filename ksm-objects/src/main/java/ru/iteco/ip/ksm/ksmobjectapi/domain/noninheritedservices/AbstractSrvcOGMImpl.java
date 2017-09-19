package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.domain.annotations.KSMObjectsOGMSession;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scorpio on 06.09.2017.
 */
public abstract class AbstractSrvcOGMImpl<T extends IAObject> implements AbstractSrvc<T> {
    @Inject
    @KSMObjectsOGMSession
    protected Session session;
    @Inject @DefaultKSMLogger
    protected Logger logger;

    protected static final int DEPTH_LIST = 0;
    protected static final int DEPTH_ENTITY = 1;

    @Override
    public Iterable<T> findAll() {
        return (Iterable<T>) session.loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return (T) session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public T find(String ksmObjId) {
        session.clear();
        return (T) session.load(getEntityType(), ksmObjId, DEPTH_ENTITY);
    }

    @Override
    public T findByKsmObjId(String ksmObjId){
        try {
            String cypherString = "match (ksmObject{ksmObjId:'"+ ksmObjId.toString()+"'}) WITH ksmObject MATCH p=(ksmObject)-[*0..1]-(trgt) return p";
            //"MATCH (%s) WHERE n." + this.primaryIndex + " = { id } WITH n MATCH p=(n)-[*%d..%d]-(m) RETURN p"
            /*
            Result result = session.query(cypherString, Collections.EMPTY_MAP);
            for(Map entry:result ){
                Set set = entry.entrySet();

            }
            Object a = session.queryForObject(getEntityType(), cypherString, Collections.EMPTY_MAP);
            T obj = (T) a;
            */
            Iterable ksmObjectsIterable = session.query(getEntityType(), cypherString, Collections.EMPTY_MAP);
            List<Object> ksmObjects = new ArrayList<>();
            ksmObjectsIterable.forEach(ksmObjects::add);
            if (ksmObjects.size() == 0) {
                logger.error("нет ни одного узла в Бд со свойством ksmObjId равным {}" ,ksmObjId.toString() );
                throw new ArrayIndexOutOfBoundsException("there is NO node in GDB with property named ksmObjId with value equal to  " +ksmObjId.toString() );

            } else if (ksmObjects.size() == 1){
                T tmpKsmObj = (T) ksmObjects.get(0);
                logger.debug( " cypher query {} return one node with type {}" , cypherString , tmpKsmObj.getKsmObjType());
                return tmpKsmObj;
            }else{
                logger.error("существует более одного узда БД со свойством ksmObjId равным {}" ,ksmObjId.toString() );
                throw new ArrayIndexOutOfBoundsException("there are many node in GDB with property named ksmObjId with value equal to  " +ksmObjId.toString() );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        //return session.load(getEntityType() ,ksmObjId,DEPTH_ENTITY);
    }
    @Override
    public T findByKsmObjId (UUID ksmObjId){
        return findByKsmObjId(ksmObjId.toString());
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public void deleteByKsmObjId(UUID ksmObjId) {
        session.delete(findByKsmObjId(ksmObjId));
    }


    /* TODO: разобраться с транзакционностью*/
    @Override
    public T createOrUpdate(final T ksmObject) {
        System.err.println("FOR OBJECT " + ksmObject.getKsmObjType() + " KSMOBJID IS" + ksmObject.getKsmObjId());
        //try(Transaction trn = session.beginTransaction(Transaction.Type.READ_WRITE)){
        try(Transaction trn = session.beginTransaction()){
            session.clear();
            session.save(ksmObject, DEPTH_ENTITY);
            T newKsmObject = find(ksmObject.getKsmObjId());
            trn.commit();
            return newKsmObject;
        }

    }
    public abstract Class<T> getEntityType();

    @Override
    public Transaction beginTransaction() {
        return session.beginTransaction();
    }

    @Override
    public Transaction beginTransaction(Transaction.Type type) {
        return session.beginTransaction(type);
    }

    @Override
    public Transaction beginTransaction(Transaction.Type type, Iterable<String> iterable) {
        return session.beginTransaction(type, iterable);
    }

    @Override
    public T instansiateFromEntityType() throws IllegalAccessException, InstantiationException {
        return getEntityType().newInstance();
    }
}
