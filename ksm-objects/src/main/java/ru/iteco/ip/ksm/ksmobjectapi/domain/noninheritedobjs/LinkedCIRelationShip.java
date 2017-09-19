package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

/**
 * Created by Scorpio on 18.09.2017.
 */

/** интерфейсы связей должены наследовать от IAObject что бы можно было использовать
 * абстрактный сервис AbstractSrvc, но сам класс реализующий этот интерфейс наследовать от AObject НЕ ДОЛЖЕН!!!
 * в место этого наследуемся от ARelationShip
 */
public interface LinkedCIRelationShip extends IAObject {
    CI getStartci();

    void setStartci(CI startci);

    CI getEnddci();

    void setEnddci(CI enddci);

}
