package org.nix.web.interceptor;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.nix.domain.entity.User;
import org.nix.utils.Log4jToDBKey;
import org.nix.utils.SessionKey;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 * <p>
 *     //拦截所有control
 * 在拦截器中加载用户id
 * 之后使用logger就可以绑定指定用户的id并加入数据库中
 */
public class Log4jInterceptor implements HandlerInterceptor {

    private HttpSession session;

    private static Logger logger = Logger.getLogger(Log4jInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpServletRequest req = (HttpServletRequest) httpServletRequest;

        session = req.getSession();

        if (session==null){
            MDC.put(Log4jToDBKey.USERID,Log4jToDBKey.SYSTEM_ID);
        }
        else{
            User customer=(User)session.getAttribute(SessionKey.USER);
            //如果用户为空，那么就是系统日志
            if (customer == null){
                MDC.put(Log4jToDBKey.USERID,Log4jToDBKey.SYSTEM_ID);
            }else {
                MDC.put(Log4jToDBKey.USERID,customer.getId());
            }
        }

        logger.info("进入springmvc");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
