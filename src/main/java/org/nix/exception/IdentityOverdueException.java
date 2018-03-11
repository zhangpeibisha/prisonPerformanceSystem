package org.nix.exception;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 *
 * 身份过期异常
 */
public class IdentityOverdueException extends BaseException{
    //日志记录
    private static Logger logger = Logger.getLogger(IdentityOverdueException.class);

    public IdentityOverdueException() {
        super(LuoErrorCode.IDENTITY_OVERDUE);
    }
}
