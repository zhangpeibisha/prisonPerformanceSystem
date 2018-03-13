package org.nix.exception;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 */
public class SelectException extends BaseException{
    //日志记录
    private static Logger logger = Logger.getLogger(SelectException.class);

    public SelectException() {
        super(ErrorCode.SELECT_EXCEPTION);
    }
}
