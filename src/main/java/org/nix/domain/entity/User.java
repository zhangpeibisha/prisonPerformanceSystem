package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *-
 * 用户实体
 */
@Entity
@Table(name = "User")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer"})
public class User extends BaseEntity{

    //警号 司法警号为 7位
    private String siren;
    //密码
    private String password;
    //狱警名字
    private String name;
    //基础工资
    private double basicWage;

    //一个用户有多条加班信息，一条加班信息只有一个用户


    @Column(name = "siren" , nullable = false , length = 7 , unique = true)
    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    @Column(name = "password" , nullable = false , length = 18 )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name" , nullable = false , length = 8 )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "basicWage" , nullable = false , length = 8 )
    public double getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(double basicWage) {
        this.basicWage = basicWage;
    }
}
