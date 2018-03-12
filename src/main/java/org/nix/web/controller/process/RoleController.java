package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.nix.dao.service.RoleService;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.entitybuild.RoleBuild;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/12.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    //日志记录
    private static Logger logger = Logger.getLogger(RoleController.class);

    @RequestMapping(value = "/saveRole",method = RequestMethod.POST)
    public Map<String,Object> saveRole(@RequestParam("roleName")String roleName,
                                       @RequestParam("describe")String describe){
        Role role = new RoleBuild()
                .setName(roleName)
                .setDescription(describe)
                .setCreateTime()
                .build();

        roleService.save(role);

        return new ResultMap()
                .resultSuccess()
                .send();

    }

}
