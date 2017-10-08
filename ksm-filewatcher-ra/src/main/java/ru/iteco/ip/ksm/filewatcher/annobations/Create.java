package ru.iteco.ip.ksm.filewatcher.annobations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Scorpio on 06.10.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Create {

    String value() default ".*";

}
