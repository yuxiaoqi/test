package Annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: yuyanqi
 * @Date 2016/7/1 18:49
 * @Vesion V1.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {
    String value() default "yyq";

}
