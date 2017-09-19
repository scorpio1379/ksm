package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import org.neo4j.ogm.transaction.Transaction;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

/**
 * Created by Scorpio on 18.09.2017.
 */
public abstract class AbstractRelationSrvcOGMImpl<ARelationShip extends IAObject> extends AbstractSrvcOGMImpl<ARelationShip> implements AbstractSrvc<ARelationShip> {
    @Override
    public ARelationShip createOrUpdate(ARelationShip ksmRelationShip) {
        try(Transaction trn = session.beginTransaction()){
            session.clear();
            session.save(ksmRelationShip, super.DEPTH_ENTITY);
            ARelationShip newKsmObject = find(ksmRelationShip.getId());
            trn.commit();
            return newKsmObject;
        }
    }
}
