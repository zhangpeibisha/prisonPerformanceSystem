package org.nix.exception;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class AccountNumberException extends BaseException{
    //日志记录
    private static Logger logger = Logger.getLogger(AccountNumberException.class);

    public AccountNumberException() {
        super(LuoErrorCode.USERNAME_PASSWORD_ERROR);
    }
}
