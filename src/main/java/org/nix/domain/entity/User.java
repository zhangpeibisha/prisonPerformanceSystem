package org.nix.domain.entity;

import org.nix.domain.entity.base.BaseEntity;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *
 * 用户实体
 */
public class User extends BaseEntity{
    //警号
    private String siren;
    //密码
    private String password;
    //狱警名字
    private String name;
    //基础工资
    private double basicWage;

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(double basicWage) {
        this.basicWage = basicWage;
    }
}
