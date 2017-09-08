package ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.indicatortocirelationships.AttachedHIKSMRelationShip;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.indicatortocirelationships.AttachedKPIKSMRelationShip;

import java.util.Set;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public class KSMCI<T extends KSMCI<T>> extends BaseKSMCI<KSMCI<T>> implements IKSMCI {

    private Set<KSMHI<?>> attachedHis;
    private Set<KSMKPI<?>> attachedKpis;

    private Set<AttachedHIKSMRelationShip> attachedHIKSMRelationShips;
    private Set<AttachedKPIKSMRelationShip> attachedKPIKSMRelationShips;


    public KSMCI(){
        super();
        this.ksmCiType = KSMCIType.REGULAR;
    }

    public Set<KSMHI<?>> getAttachedHis(){
        return this.attachedHis;
    }
    public Set<KSMKPI<?>> getAttachedKpis(){
        return this.attachedKpis;
    }


}
