package org.nix.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Descrption该注解是标签型注解，被此注解标注的方法需要进行权限校验
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ValidatePermission {
    /**
     * @Description功能Id的参数索引位置  默认为0，表示功能id在第一个参数的位置上，-1则表示未提供，无法进行校验
     */
    int idx() default 0;
}
