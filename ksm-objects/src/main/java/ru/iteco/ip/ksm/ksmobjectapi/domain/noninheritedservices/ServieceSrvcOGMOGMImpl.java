package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.api.ServiceModel;
import ru.iteco.ip.ksm.ksmobjectapi.apiimpl.ServiceModelImpl;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.CI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShipImpl;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.Service_Obj;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scorpio on 18.09.2017.
 */
@Stateless
public class ServieceSrvcOGMOGMImpl extends AbstractSrvcOGMImpl<Service> implements ServiceSrvc {

/* TODO: придумать как в общем виде определять конкрентый класс реализации обьекта CI. скорее всего нужно вынести метод в сервис CI тк здесь нет специфичных для сервиса парамеров*/
    private static final String serviceObjNeo4jNodeLbl = "Service_Obj";
    private static final String ci2ciRelationShipNeo4jRelationShipLbl = "LinkedCI";
    private static final String ciObjNeo4jNodeLbl = "CI_Obj";
    private static final String indexedKsmIdPropName = "ksmObjId";
    private HashMap<String,String> queryParamMap = new HashMap<>();


    @Override
    public Set<CI> getAllRelatedCis(Service service) {
        //String cypherQuery = "MATCH (n:Service)-[linkedCI:Containment*]->(s:`Model Service`:"+ intIdLbl +") WITH DISTINCT n as CIs return CIs";
        //String cypherQuery = "match (ksmCIObj:CI_Obj)-[:LinkedCI*]->(ksmServoceObj:Service_Obj{ksmObjId:'f0d8c398-c95f-45f1-80d3-b80a2a2808d9'}) WITH DISTINCT ksmCIObj as CIs  return CIs";
        queryParamMap.put("ciObjNeo4jNodeLbl",ciObjNeo4jNodeLbl);
        queryParamMap.put("ci2ciRelationShipNeo4jRelationShipLbl",ci2ciRelationShipNeo4jRelationShipLbl);
        queryParamMap.put("serviceObjNeo4jNodeLbl",serviceObjNeo4jNodeLbl);
        queryParamMap.put("serviceKsmObjId",service.getKsmObjId());
        String cypherQuery = "match (ksmCIObj:{ciObjNeo4jNodeLbl})-[:{ci2ciRelationShipNeo4jRelationShipLbl}*]->(ksmServoceObj:{serviceObjNeo4jNodeLbl}{ksmObjId:'{serviceKsmObjId}'}) WITH DISTINCT ksmCIObj as CIs  return CIs";
        Iterable<CI_Obj> cis = this.session.query(CI_Obj.class, cypherQuery, queryParamMap);
        HashSet<CI> retCisSet = new HashSet<CI>();
        cis.forEach(it -> retCisSet.add(it));
        cis = null;
        return retCisSet;
    }

    @Override
    public Set<LinkedCIRelationShip> getAllRelations(Service service) {
        return null;
    }
    @Override
    public ServiceModel getServiceModel(Service service){
        ServiceModel srvcModel = new ServiceModelImpl();
        queryParamMap.put("serviceKsmObjId",service.getKsmObjId());
        queryParamMap.put("ciObjNeo4jNodeLbl",ciObjNeo4jNodeLbl);
        // String cypherQuery = "match p=((ksmCIObj:"+ciObjNeo4jNodeLbl+")-[:"+ci2ciRelationShipNeo4jRelationShipLbl+"*]->(ksmServiceObj{"+indexedKsmIdPropName+":{serviceKsmObjId}})) WITH DISTINCT nodes(p) as cis , relationships(p) as links return cis,links";
        String cypherQueryForCIs = "match p=((ksmIndicator)-[attachedIndicatorRel:AttachedHI|:AttachedKPI*1]->(ksmCIObj:"+ciObjNeo4jNodeLbl+")-[:"+ci2ciRelationShipNeo4jRelationShipLbl+"*]->(ksmServiceObj{"+indexedKsmIdPropName+":{serviceKsmObjId}})) UNWIND relationships(p) as r UNWIND nodes(p) as n  return  distinct ksmCIObj,ksmIndicator ,r";
        // = "match p = ((inds)-[*]->(ksmObj)-[*]->(srvcObj)) where {ciObjNeo4jNodeLbl} in labels(ksmObj) AND  'AIndicator' in labels(inds) and srvcObj.ksmObjId={serviceKsmObjId}  return p";
        String cypherQueryForRelations = "match p=((ksmCIObj:"+ciObjNeo4jNodeLbl+")-[:"+ci2ciRelationShipNeo4jRelationShipLbl+"*]->(ksmServiceObj{"+indexedKsmIdPropName+":{serviceKsmObjId}})) UNWIND relationships(p) as r UNWIND nodes(p) as n  return  collect(distinct r)";
        Iterable<CI_Obj> cis = this.session.query(CI_Obj.class, cypherQueryForCIs, queryParamMap);
        Iterable<LinkedCIRelationShipImpl> rels = this.session.query(LinkedCIRelationShipImpl.class, cypherQueryForRelations, queryParamMap);
        cis.forEach(ci->srvcModel.addCI(ci));
        rels.forEach(rel->srvcModel.addLinkedCIRelationShip(rel));
        return srvcModel;
    }

    @Override
    public Class getEntityType() {
        return Service_Obj.class;
    }
}
