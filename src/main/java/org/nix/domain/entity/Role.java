package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 用户角色类信息
 */
@Entity
@Table(name = "Role")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer"})
public class Role extends BaseEntity{

    //角色名字
    private String name;
    //角色描述
    private String description;

    //一个角色有多个权限，一个权限有多个角色
    private Set<Resources> resources = new HashSet<>();

    @Column(name = "name" , nullable = false , length = 20 , unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public Set<Resources> getResources() {
        return resources;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }
}
