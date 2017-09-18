package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 13.09.2017.
 */
public interface CIToCILinker {
    CIToCILinker setStartCI(CI startCi);
    CIToCILinker setEndCI(CI endCi);
    CIToCILinker setLinkType(String linkType);
    void build();
}
