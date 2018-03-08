package org.nix.web.listener;

import org.apache.log4j.Logger;
import org.nix.domain.entity.User;
import org.nix.utils.SessionKey;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 * <p>
 * 监听用户session事件
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static Map<Object, Object> loginUsers = new HashMap<>();//保存用户名对应的session

    private static Logger logger = Logger.getLogger(SessionListener.class);

    private HttpSession session;

    /**
     * session添加用户信息的时候触发
     *
     * @param se
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

        session = se.getSession();

        Object object = se.getValue();
        //如果这个值是user类，那么把user和session绑定在一起
        if (object instanceof User) {
            //将User中的密码过滤掉
            User user = (User) object;
            user.setPassword("");
            //覆盖user信息
            session.setAttribute(SessionKey.USER,user);
        }

        loginUsers.put(se.getSession(), se.getValue().toString());
        logger.info("sessionValue " + loginUsers.get(se.getSession()));
    }

    /**
     * 移除用户信息的时候触发
     *
     * @param se
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        session = se.getSession();
        try {
            loginUsers.remove(se.getSession());
        } catch (Exception e) {

        }
    }

    /**
     * 覆盖用户信息的时候触发
     *
     * @param se
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        session = se.getSession();
        loginUsers.put(se.getSession(), se.getValue().toString());
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    /**
     * session超时，移除用户信息
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        session = se.getSession();
        try {
            loginUsers.remove(se.getSession());
        } catch (Exception e) {

        }
    }
}
