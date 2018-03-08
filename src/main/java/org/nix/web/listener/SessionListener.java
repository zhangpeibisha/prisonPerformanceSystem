package org.nix.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 监听用户session事件
 */
public class SessionListener implements HttpSessionListener , HttpSessionAttributeListener {

    private static Map<Object,Object> loginUsers = new HashMap<>();//保存用户名对应的session

    private static Logger logger = Logger.getLogger(SessionListener.class);

    /**
     * session添加用户信息的时候触发
     * @param se
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        loginUsers.put(se.getSession(),se.getValue().toString());
    }

    /**
     * 移除用户信息的时候触发
     * @param se
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        try{
            loginUsers.remove(se.getSession());
        }catch(Exception e){

        }
    }

    /**
     * 覆盖用户信息的时候触发
     * @param se
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        loginUsers.put(se.getSession(), se.getValue().toString());
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    /**
     * session超时，移除用户信息
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        try{
            loginUsers.remove(se.getSession());
        }catch(Exception e){

        }
    }
}
