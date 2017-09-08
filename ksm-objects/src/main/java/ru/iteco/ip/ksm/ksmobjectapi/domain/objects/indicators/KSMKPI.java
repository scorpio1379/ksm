package ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMIndicator;


/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class KSMKPI<T extends KSMKPI<T>> extends BaseKSMIndicator<KSMKPI<T>> {
    private String ruleFileName;
    private KSMKPIType kpiType;
    private String rule;




    @JsonCreator
    public KSMKPI(
            @JsonProperty("tmpId") String uuid
            ,@JsonProperty("name") String name
            ,@JsonProperty("ruleFileName") String ruleFileName
            ,@JsonProperty("displayName") String displayName
            ,@JsonProperty("rule") String rule
            ,@JsonProperty("kpiType") String kpiType
    ) {
        super();
        this.ruleFileName = ruleFileName;
        this.rule = rule;
        KSMKPIType ksmKPIType;
        try{
            ksmKPIType = KSMKPIType.valueOf(kpiType);
            if (ksmKPIType == null){
                logger.error("не возможно преобразовать строковую переменную kpiType {} в соответствующий ненулевое  значение enum KSMKPIType, будет использовано значение по умолчанию. ошибка {}" ,kpiType);
                ksmKPIType = KSMKPIType.REGULAR;
            }
        } catch (IllegalArgumentException | NullPointerException e){
            logger.error("не возможно преобразовать строковую переменную kpiType {} в соответствующий enum KSMKPIType, будет использовано значение по умолчанию. ошибка {}" ,kpiType,e.getStackTrace());
            ksmKPIType = KSMKPIType.REGULAR;
        }
        /** это тип связи KPI с CI должен совпадать с типом связи в аннотации к классу связи указанного в поле attachedKPIRelationShip
         *
         */
        this.kpiType = ksmKPIType;

        this.indicatorType = KSMIndicatorType.KPI;
    }
    public KSMKPI(){
        super();
    }

    public KSMKPIType getKpiType(){
        return this.kpiType;
    }




}
