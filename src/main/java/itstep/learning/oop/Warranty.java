package itstep.learning.oop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Warranty {
    int warrantyPeriod() default 0;
}
