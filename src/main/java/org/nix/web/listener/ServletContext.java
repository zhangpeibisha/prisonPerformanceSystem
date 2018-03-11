package org.nix.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.nix.utils.Log4jToDBKey;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Create by zhangpe0312@qq.com on 2018/2/27.
 * 该监听器用来监听ServletContext的初始化和销毁事件
 * 容器启动关闭监听器
 */
public class ServletContext implements ServletContextListener {

    private static Logger logger = Logger.getLogger(ServletContext.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.info("Tomcat 开启");
        MDC.put(Log4jToDBKey.USERID,Log4jToDBKey.SYSTEM_ID);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Tomcat 关闭");
    }
}
