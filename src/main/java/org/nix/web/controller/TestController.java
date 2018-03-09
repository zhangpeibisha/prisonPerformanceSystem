package org.nix.web.controller;

import org.apache.log4j.Logger;
import org.nix.annotation.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 */
@Controller
public class TestController {

    private Logger logger = Logger.getLogger(TestController.class);

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    @ValidatePermission
    public @ResponseBody
    Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", "请求成功");
        logger.info("请求成功");
        return map;
    }
}
