package ru.iteco.ip.ksm.core.internal.ksmobjectupdaters;

import org.neo4j.graphdb.Node;
import ru.iteco.ip.ksm.core.internal.InternalKSMIndicatorUpdater;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMIndicator;

import javax.ejb.Stateless;

/**
 * Created by Scorpio on 27.09.2017.
 */
@Stateless(name = "InternalKSMIndicatorUpdaterEJB")
public class InternalKSMIndicatorUpdaterBean<INDICATORTYPE extends InternalKSMIndicator<INDICATORTYPE>>
        extends InternalKSMObjectUpdaterBean<INDICATORTYPE>
        implements InternalKSMIndicatorUpdater<INDICATORTYPE> {
    /* TODO: придумать как связать поля у узлах GraphDataBase и названия полей д доменных классах*/
    private static final String CHANGE_TIME_STAMP_FIELD_NAME = "chgTimeStamp";
    private static final String DEBUG_INFO_FIELD_NAME ="debugInfo";
    private static final String VALUE_FIELD_NAME="value";
    private static final String STATUS_FIELD_NAME="status";
    public InternalKSMIndicatorUpdaterBean() {
    }

    protected void updateInternalKSMIndicatorDebugInfo(String debugInfo,Node nd) {
        this.updateInternalKSMObjectProperty(DEBUG_INFO_FIELD_NAME , debugInfo , nd);
    }

    protected void updateInternalKSMIndicatorChgTimeStamp(String chgTimeStamp,Node  nd) {
        this.updateInternalKSMObjectProperty(CHANGE_TIME_STAMP_FIELD_NAME,chgTimeStamp,nd);

    }

    protected void updateInternalKSMIndicatorValue(String value, Node nd) {
        this.updateInternalKSMObjectProperty(VALUE_FIELD_NAME ,value,nd);
    }

    protected void updateInternalKSMIndicatorStatus(String status, Node nd) {
        this.updateInternalKSMObjectProperty(STATUS_FIELD_NAME,status,nd);
    }
}
