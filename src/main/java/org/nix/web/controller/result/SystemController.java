package org.nix.web.controller.result;

import org.apache.log4j.Logger;
import org.nix.exception.LuoErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 * <p>
 * 系统返回信息的Controller
 */
@Controller
public class SystemController {
    //日志记录
    private static Logger logger = Logger.getLogger(SystemController.class);

    /**
     * 权限不足返回的控制接口
     *
     * @return
     */
    @RequestMapping(value = "/accessDenied")
    @ResponseBody
    public Map<String, Object> accessDenied() {
        Map<String, Object> map = new HashMap<>();
        logger.info("权限不足，访问被拒绝");
        map.put("result", LuoErrorCode.PERMISSION_DENIED.getValue());
        return map;
    }
}
