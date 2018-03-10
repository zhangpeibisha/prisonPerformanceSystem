package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 用户角色类信息
 */
@Entity
@Table(name = "Role")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "users"})
public class Role extends BaseEntity{

    //角色名字
    private String name;
    //角色描述
    private String description;

    //一个角色有多个权限，一个权限有多个角色
    private Set<Resources> resources = new HashSet<>();

    //一个角色有多个用户，一个用户只有一个角色
    private Set<User> users = new HashSet<>();

    @Column(name = "name" , nullable = false , length = 20 , unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="permission_role",joinColumns = {@JoinColumn(name="role")},
            inverseJoinColumns =@JoinColumn(name = "resources"))
    public Set<Resources> getResources() {
        return resources;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<User> getUsers() {
        return users;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
