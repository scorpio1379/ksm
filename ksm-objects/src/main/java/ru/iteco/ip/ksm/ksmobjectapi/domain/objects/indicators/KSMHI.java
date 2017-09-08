package ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMIndicator;


/**
 * Created by Scorpio on 20.06.2017.
 */
public class KSMHI<T extends KSMHI<T>> extends BaseKSMIndicator<KSMHI<T>> {

    protected String hiType;

    protected String itemId;


    public KSMHI(){
        super();
    }




}
