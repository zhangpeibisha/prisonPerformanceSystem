package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.nix.annotation.AuthPassport;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.ResourcesService;
import org.nix.dao.service.RoleService;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.entitybuild.RoleBuild;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/12.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcesService resourcesService;

    //日志记录
    private static Logger logger = Logger.getLogger(RoleController.class);

    /**
     * 添加角色
     * <p>
     * 管理员接口
     *
     * @param roleName
     * @param describe
     * @return
     */
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @AuthPassport
    public Map<String, Object> saveRole(@RequestParam("roleName") String roleName,
                                        @RequestParam("describe") String describe) {
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

    /**
     * 为角色添加资源接口
     * <p>
     * 管理员接口
     *
     * @param role
     * @param resources
     * @return
     */
    @RequestMapping(value = "/addResources", method = RequestMethod.POST)
    @AuthPassport
    public Map<String, Object> addResources(@RequestParam("role") int role,
                                            @RequestParam("resources") int[] resources) {

        Role role1 = roleService.findById(role);

        for (int i = 0; i < resources.length; i++) {

            Resources resources1 = resourcesService.findById(resources[i]);

            role1.addResources(resources1);
        }

        roleService.update(role1);

        logger.info("为角色" + role1.getName() + "添加资源成功");
        return new ResultMap()
                .resultSuccess()
                .send();
    }

}
