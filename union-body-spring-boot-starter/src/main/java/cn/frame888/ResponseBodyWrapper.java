package cn.frame888;

import java.lang.annotation.*;

/**
 * @author SwayJike
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ResponseBodyWrapper {

    String RESPONSE_BODY_WRAPPER_FLAG = "response_body_wrapper_flag";
}
