package org.nix.service.annotation;

import org.apache.log4j.Logger;
import org.nix.web.controller.process.UserController;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/18.
 */
public class Test {
    //日志记录
    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        // 获取特定包下所有的类(包括接口和类)
        List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(UserController.class.getPackage());
        //输出所有使用了特定注解的类的注解值
        AnnotationUtil.validAnnotation(clsList);
    }

}
