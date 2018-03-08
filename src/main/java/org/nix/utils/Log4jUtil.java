package org.nix.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.nix.domain.entity.User;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 * <p>
 * 封装日志打包输入数据库
 */
public class Log4jUtil {

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Class classzz) {
        this.logger = Logger.getLogger(classzz);
    }

    public void error(String msg) {
        MDC.put(Log4jToDBKey.USERID,Log4jToDBKey.SYSTEM_ID);
    }

    public void info(String msg) {

    }

    public void error(User user, String msg) {

    }

    public void info(User user, String msg) {

    }
}
