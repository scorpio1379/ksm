package ru.iteco.ip.ksm.ksmobjects;

import org.neo4j.ogm.session.Session;
import ru.iteco.ip.ksm.ksmobjects.annotations.KSMObjectsOGMSession;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;

import javax.inject.Inject;

/**
 * Created by Scorpio on 21.06.2017.
 */
public class KSMObjectApiImpl {
    @Inject
    private KSMCIService ciService;

    public void getAllServiceCIs(){

    }
}
