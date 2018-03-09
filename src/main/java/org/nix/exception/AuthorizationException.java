package org.nix.exception;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 未登录异常
 */
public class AuthorizationException extends BaseException{
    //日志记录
    private static Logger logger = Logger.getLogger(AuthorizationException.class);

    public AuthorizationException() {
        super(LuoErrorCode.PERMISSION_DENIED);
    }
}
