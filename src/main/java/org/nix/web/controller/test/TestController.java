package org.nix.web.controller.test;

import org.apache.log4j.Logger;
import org.nix.annotation.ValidatePermission;
import org.nix.web.controller.utils.ResultMap;
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
    Map<String, Object> test()  {
        logger.info("请求成功");
        Map<String, Object> map = new HashMap<>();
        map.put("data", "请求成功");
        return map;
    }

    @RequestMapping(value = "/model" , method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> model()  {

        return new ResultMap().resultSuccess().send();
    }
}
