package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/8.
 *-
 * 用户实体
 */
@Entity
@Table(name = "User")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "role" , "overtimeRecords"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User extends BaseEntity{

    //警号 司法警号为 7位
    private String serialNumber;
    //密码
    private String password;
    //狱警名字
    private String name;
    //基础工资
    private double basicWage;

    //一个用户有一个角色，一个角色有多个用户
    private Role role;

    //一个用户有多条加班信息，一条加班信息只有一个用户
    private Set<OvertimeRecord> overtimeRecords = new HashSet<>();

    @Column(name = "serialNumber" , nullable = false , length = 7 , unique = true)
    @Length(min=7, max=7)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String siren) {
        this.serialNumber = siren;
    }

    @Column(name = "password" , nullable = false , length = 32 )
    @Length(min=32, max=32)
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<OvertimeRecord> getOvertimeRecords() {
        return overtimeRecords;
    }

    public void setOvertimeRecords(Set<OvertimeRecord> overtimeRecords) {
        this.overtimeRecords = overtimeRecords;
    }

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role")
    public Role getRole() {
        System.out.println("获取角色信息---------");
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
