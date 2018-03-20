package org.nix.web.controller.test;

import org.apache.log4j.Logger;
import org.nix.annotation.AuthPassport;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.ResourcesService;
import org.nix.dao.service.RoleService;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.Role;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 */
@RestController
public class TestController {


    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private RoleService roleService;

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

    @RequestMapping(value = "/model" , method = RequestMethod.POST)
    public
    Map<String, Object> model()  {

        return new ResultMap().resultSuccess().send();
    }

    /**
     * 导入管理员的权限
     * @return
     */
    @RequestMapping(value = "/binRoleResurces", method = RequestMethod.POST)
    @AuthPassport
    public Map<String,Object> binRoleResurces(){

        //得到所有权限
        List<Resources> resources = resourcesService.findAll();

        Role role = roleService.findByProperty("name","管理员");

        for (Resources r :
                resources) {
            role.getResources().add(r);
        }

        roleService.update(role);

        return new ResultMap().resultSuccess().send();
    }


    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
