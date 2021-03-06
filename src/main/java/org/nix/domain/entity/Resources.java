package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 访问资源控制列表
 */
@Entity
@Table(name = "Resources")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "roles"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Resources extends BaseEntity{

    //资源名字
    private String name;
    //控制url路径
    private String url;

    //一个资源有多个角色，一个角色有多个资源
    private Set<Role> roles = new HashSet<>();

    @Column(name = "name"  , length = 100 , unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "url", unique = true , nullable = false)
    public String getUrl() {
        return url;
    }

    @ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="permission_role",joinColumns = {@JoinColumn(name="resources")},
            inverseJoinColumns =@JoinColumn(name = "role"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
