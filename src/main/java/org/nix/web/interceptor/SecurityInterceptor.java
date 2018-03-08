package org.nix.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 * 参考博客
 * http://blog.csdn.net/houxuehan/article/details/51745175
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private String postPath = "";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        postPath = httpServletRequest.getContextPath()
                + httpServletRequest.getRequestURL()
                + httpServletRequest.getMethod();


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
