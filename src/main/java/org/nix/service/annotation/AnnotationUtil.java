package org.nix.service.annotation;

import org.apache.log4j.Logger;
import org.nix.annotation.ValidatePermission;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/18.
 */
public class AnnotationUtil {
    //日志记录
    private static Logger logger = Logger.getLogger(AnnotationUtil.class);

    public static void validAnnotation(List<Class<?>> clsList){
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                //获取类中的所有的方法
                Method[] methods = cls.getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                        if (requestMapping != null) {
                            //可以做权限验证
                            System.out.println(requestMapping.value()[0]);
                        }
                    }
                }
            }
        }
    }

}
