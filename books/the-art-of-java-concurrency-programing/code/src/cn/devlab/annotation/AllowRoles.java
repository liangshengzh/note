package cn.devlab.annotation;

import java.lang.annotation.*;

/**
 * Created by zhong on 2016/12/24.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowRoles {

    Role[] roles() default {};
}
