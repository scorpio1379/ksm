package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.Service_Obj;

import javax.ejb.Stateless;
import java.util.Set;

/**
 * Created by Scorpio on 18.09.2017.
 */
@Stateless
public class ServieceSrvcOGMOGMImpl extends AbstractSrvcOGMImpl<Service> implements ServiceSrvc {
    private static final String serviceObjNeo4jNodeLbl = "Service_Obj";
    private static final String ci2ciRelationShipNeo4jRelationShipLbl = "LinkedCI";
    private static final String ciObjNeo4jNodeLbl = "CI_Obj";
    private static final String indexedKsmIdPropName = "ksmObjId";

    @Override
    public Set<CI> getAllRelatedCis(Service service) {
       // String q_str = "MATCH (n:Service)-[linkedCI:Containment*]->(s:`Model Service`:"+ intIdLbl +") WITH DISTINCT n as CIs return CIs";
        return null;
    }

    @Override
    public Set<LinkedCIRelationShip> getAllRelations(Service service) {
        return null;
    }

    @Override
    public Class getEntityType() {
        return Service_Obj.class;
    }
}
