package org.nix.web.controller.exception;

import org.apache.log4j.Logger;
import org.nix.exception.LuoErrorCode;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.dao.DataIntegrityViolationException;
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
                .appendParameter(LuoErrorCode.PERMISSION_DENIED.getValue(), LuoErrorCode.PERMISSION_DENIED.getDesc())
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
     * Insert或Update数据时违反了完整性，例如违反了惟一性限制
     *
     * @return 返回错误通知信息
     */
    @RequestMapping(value = "/dataIntegrityViolationException")
    public Map<String, Object> dataIntegrityViolationException() {

        return new ResultMap()
                .setResult(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue())
                .appendParameter(LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getValue()
                        , LuoErrorCode.CONSTRAINT_VIOLATE_DATABASES.getDesc())
                .send();
    }

    /**
     * 某些数据不能被检测到，例如不能通过关键字找到一条记录
     *
     * @return
     */
    @RequestMapping(value = "/dataRetrievalFailureException")
    public Map<String, Object> dataRetrievalFailureException() {

        return new ResultMap()
                .setResult(LuoErrorCode.DATABASES_ERROR_DATA_RETRIEVAL_FAIL.getValue())
                .appendParameter(LuoErrorCode.DATABASES_ERROR_DATA_RETRIEVAL_FAIL.getValue()
                        , LuoErrorCode.DATABASES_ERROR_DATA_RETRIEVAL_FAIL.getDesc())
                .send();
    }

    /**
     * 有错误发生，但无法归类到某一更为具体的异常中
     *
     * @return
     */
    @RequestMapping(value = "/uncategorizedDataAccessException")
    public Map<String, Object> uncategorizedDataAccessException() {

        return new ResultMap()
                .setResult(LuoErrorCode.DATABASES_ERROR_UNCATEGORIZED.getValue())
                .appendParameter(LuoErrorCode.DATABASES_ERROR_UNCATEGORIZED.getValue()
                        , LuoErrorCode.DATABASES_ERROR_UNCATEGORIZED.getDesc())
                .send();
    }


}
