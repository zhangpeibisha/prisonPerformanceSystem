package org.nix.web.listener;


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 监听用户请求
 */
public class RequestListener implements ServletRequestListener {

    private static Logger logger = Logger.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        MDC.put("userId",1);
        logger.info("测试**********");
    }
}
