package org.nix.web.aspect;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.nix.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 * 事件日志 切面,凡是带有 @ValidatePermission 以及@ResponseBody注解 控制器 都要进行 功能权限检查，
 * 若无权限，则抛出AuthorizationException 异常，该异常将请求转发至一个控制器，然后将异常结果返回
 * 权限过滤切面类
 */
@Service
public class PermissionAspect {
    //日志记录
    private static Logger logger = Logger.getLogger(PermissionAspect.class);

    public void doBefore(JoinPoint jp) throws IOException, AuthorizationException {

       String str =  "log PermissionAspect Before method: "
               + jp.getTarget().getClass().getName() + "."
               + jp.getSignature().getName();

        logger.info(str);

        Method soruceMethod = getSourceMethod(jp);
        if (soruceMethod != null) {
            //进行权限检验
//            return;
        }
        throw new AuthorizationException();
    }

    private Method getSourceMethod(JoinPoint jp) {
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        try {
            return jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
