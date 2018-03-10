package org.nix.web.controller.exception;

import org.apache.log4j.Logger;
import org.nix.exception.AccountNumberException;
import org.nix.exception.LuoErrorCode;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class UserExceptionResult extends SystemExceptionResult{
    //日志记录
    private static Logger logger = Logger.getLogger(UserExceptionResult.class);

    /**
     * 账号密码不匹配异常
     * @return 返回错误通知信息
     */
    @ExceptionHandler(AccountNumberException.class)
    @ResponseBody
    public Map<String, Object> accountNumberException() {

        return new ResultMap()
                .setResult(LuoErrorCode.USERNAME_PASSWORD_ERROR.getValue())
                .appendParameter(LuoErrorCode.USERNAME_PASSWORD_ERROR.getValue()
                        , LuoErrorCode.USERNAME_PASSWORD_ERROR.getDesc())
                .send();
    }
}
