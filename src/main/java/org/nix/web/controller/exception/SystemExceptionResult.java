package org.nix.web.controller.exception;

import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.nix.exception.AccountNumberException;
import org.nix.exception.LuoErrorCode;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 对一些系统异常进行处理异常进行处理
 */
@ControllerAdvice
public class SystemExceptionResult {
    //日志记录
    private static Logger logger = Logger.getLogger(SystemExceptionResult.class);

    /**
     * 空指针异常返回结果类
     *
     * @return 返回错误通知信息
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Map<String, Object> parameterNullException() {

        return new ResultMap()
                .setResult(LuoErrorCode.PARAMETER_NULL.getValue())
                .appendParameter(LuoErrorCode.PARAMETER_NULL.getValue()
                        , LuoErrorCode.PARAMETER_NULL.getDesc())
                .send();
    }

    /**
     * 违反了数据库唯一约束的插入操作
     * @return 返回错误通知信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Map<String, Object> repeatedDataException() {

        return new ResultMap()
                .setResult(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue())
                .appendParameter(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue()
                        , LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getDesc())
                .send();
    }

    /**
     * 插入数据库的不能为空的字段为空，抛出异常
     * @return
     */
    @ExceptionHandler(PropertyValueException.class)
    @ResponseBody
    public Map<String, Object> nullFieldDatabaseException() {

        return new ResultMap()
                .setResult(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue())
                .appendParameter(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue()
                        , LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getDesc())
                .send();
    }

}
