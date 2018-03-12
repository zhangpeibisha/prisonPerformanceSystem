package org.nix.web.controller.exception;

import org.apache.log4j.Logger;
import org.nix.exception.LuoErrorCode;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 对一些系统异常进行处理异常进行处理
 */
@RestController
@RequestMapping(value = "/exception/system")
public class SystemExceptionResult {
    //日志记录
    private static Logger logger = Logger.getLogger(SystemExceptionResult.class);


    /**
     * 权限不足返回的控制接口
     *
     * @return
     */
    @RequestMapping(value = "/accessDenied")
    public Map<String, Object> accessDenied() {
        logger.error("权限不足，访问被拒绝");
        return new ResultMap()
                .setResult(LuoErrorCode.PERMISSION_DENIED.getValue())
                .appendParameter(LuoErrorCode.PERMISSION_DENIED.getValue(),LuoErrorCode.PERMISSION_DENIED.getDesc())
                .send();
    }

    /**
     * 空指针异常返回结果类
     *
     * @return 返回错误通知信息
     */
    @RequestMapping(value = "/parameterNullException")
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
    @RequestMapping(value = "/repeatedDataException")
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
    @RequestMapping(value = "/nullFieldDatabaseException")
    public Map<String, Object> nullFieldDatabaseException() {

        return new ResultMap()
                .setResult(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue())
                .appendParameter(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue()
                        , LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getDesc())
                .send();
    }



}
