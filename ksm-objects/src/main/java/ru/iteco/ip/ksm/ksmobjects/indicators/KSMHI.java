package ru.iteco.ip.ksm.ksmobjects.indicators;

import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;

/**
 * Created by Scorpio on 20.06.2017.
 */
public class KSMHI<T> extends KSMBaseIndicator<KSMHI<T>> {
    protected String hiType;
    @Relationship(type = "ATTACHED_HI")
    private KSMBaseCI<?> attachedToCI;

    public KSMHI(String uuid, String hiType , String name) {
        super(uuid , name);
        this.hiType = hiType;
    }

    public KSMHI(String uuid , String name) {
        super(uuid , name);
        this.hiType = "EVENT";
    }


    public KSMBaseCI<?> getAttachedToCI() {
        return attachedToCI;
    }

    public void setAttachedToCI(KSMBaseCI<?> attachedToCI) {
        this.attachedToCI = attachedToCI;
    }
}
