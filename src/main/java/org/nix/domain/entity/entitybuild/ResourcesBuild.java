package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.Role;

import java.util.Date;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class ResourcesBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(ResourcesBuild.class);

    Resources resources = new Resources();

    public ResourcesBuild setName(String name) {
        resources.setName(name);
        return this;
    }

    public ResourcesBuild setUrl(String url) {
        resources.setUrl(url);
        return this;
    }

    public ResourcesBuild setRoles(Set<Role> roles) {
        resources.setRoles(roles);
        return this;
    }

    public ResourcesBuild setCreateTime() {
        resources.setCreateTime(new Date());
        return this;
    }

    public Resources build(){
       return resources;
    }
}
