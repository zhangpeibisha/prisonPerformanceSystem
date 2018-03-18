package org.nix.web.interceptor;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nix.annotation.AuthPassport;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.User;
import org.nix.exception.AuthorizationException;
import org.nix.utils.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Iterator;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/18.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    //日志记录
    private static Logger logger = Logger.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        String URI = request.getRequestURI();

        URI = URI.substring(0,URI.lastIndexOf(".do"));

        logger.info("请求接口 " + URI);

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || !authPassport.validate())
                return true;
            else{
                boolean isRequest = false;

                User user = (User) session.getAttribute(SessionKey.USER);

                //获取该用户的角色
                user = userService.findById(user.getId());
                Role role = user.getRole();

                Set<Resources> resources = role.getResources();

                for (Resources resource : resources) {
                    String path = resource.getUrl();
                    if (path.equals(URI)) {
                        isRequest = true;
                        break;
                    }
                }


                //在这里实现自己的权限验证逻辑
                if(isRequest)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
                else//如果验证失败
                {
                  //抛出没有权限异常
                    throw new AuthorizationException();
                }
            }
        }
        else
            return true;
    }
}
