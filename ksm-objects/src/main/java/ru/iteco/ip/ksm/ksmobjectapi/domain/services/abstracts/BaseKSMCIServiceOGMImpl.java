package ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.IBaseKSMCI;

/**
 * Created by Scorpio on 05.09.2017.
 */
public abstract class BaseKSMCIServiceOGMImpl<PARAM extends IBaseKSMCI>
        extends BaseKSMObjectServiceOGMImpl<PARAM>
        implements BaseKSMCIService<PARAM>{
}
