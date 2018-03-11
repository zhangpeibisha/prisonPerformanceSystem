package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.User;

import java.util.Date;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class RoleBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(RoleBuild.class);

    Role role = new Role();

    public RoleBuild setName(String name) {
        role.setName(name);
        return this;
    }

    public RoleBuild setDescription(String description) {
        role.setDescription(description);
        return this;
    }

    public RoleBuild setResources(Set<Resources> resources) {
        role.setResources(resources);
        return this;
    }

    public RoleBuild setUsers(Set<User> users) {
        role.setUsers(users);
        return this;
    }

    public RoleBuild setCreateTime() {
        role.setCreateTime(new Date());
        return this;
    }

    public Role build(){
       return role;
    }
}
