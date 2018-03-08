package org.nix.web.listener;


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.nix.domain.entity.User;
import org.nix.utils.Log4jToDBKey;
import org.nix.utils.SessionKey;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 监听用户请求
 */
public class RequestListener implements ServletRequestListener {

    private static Logger logger = Logger.getLogger(RequestListener.class);

    private HttpServletRequest request;

    private HttpSession session;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        session = request.getSession();

    }
}
