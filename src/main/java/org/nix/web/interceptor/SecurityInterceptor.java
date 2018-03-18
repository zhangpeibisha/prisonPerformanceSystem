package org.nix.web.interceptor;

import org.apache.log4j.Logger;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nix.exception.ErrorCode;
import org.nix.utils.SessionKey;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 拦截用户是否登陆
 */
public class SecurityInterceptor implements HandlerInterceptor {
    //日志记录
    private static Logger logger = Logger.getLogger(SecurityInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        logger.info("SecurityInterceptor:" + httpServletRequest.getContextPath()
                + "," + httpServletRequest.getRequestURI() + "," + httpServletRequest.getMethod());

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute(SessionKey.USER) == null) {

            logger.error("AuthorizationException:未登录！" + httpServletRequest.getMethod());

            if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
                httpServletResponse.setContentType("text/html; charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write(
                        JSON.toJSONString
                                (new ResultMap()
                                        .setResult(ErrorCode.IDENTITY_OVERDUE.getValue())
                                        .send()));
                out.flush();
                out.close();
            } else {
                //如果不是POST方法请求，就跳转到登陆页
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.html");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
