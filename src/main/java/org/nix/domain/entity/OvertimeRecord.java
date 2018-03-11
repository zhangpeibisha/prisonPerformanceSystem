package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 加班记录表
 */
@Entity
@Table(name = "OvertimeRecord")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "user" ,"rules"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OvertimeRecord extends BaseEntity{

    //加班时长 以天数保存
    private double overtimeLength;
    //加班开始时间
    private Date overtimeStart;
    //加班结束时间
    private Date overtimeEnd;
    //加班工资
    private double overtimeMoney;

    //一条加班记录只有一个计费规则，一个计费规则有多个计费记录
    private OvertimeRules rules;
    //一个订单一个用户，一个用户多个订单
    private User user;

    @Column(name = "overtimeLength",length = 20)
    public double getOvertimeLength() {
        return overtimeLength;
    }

    @Column(name = "overtimeStart", length = 19, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOvertimeStart() {
        return overtimeStart;
    }

    @Column(name = "overtimeEnd", length = 19, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOvertimeEnd() {
        return overtimeEnd;
    }

    //加班费精确到两位
    @Column(name = "overtimeMoney",columnDefinition = "double(10,2) default '1.00'",length = 10)
    public double getOvertimeMoney() {
        return overtimeMoney;
    }

    @ManyToOne(targetEntity = Role.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "rules")
    public OvertimeRules getRules() {
        return rules;
    }

    @ManyToOne(targetEntity = Role.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setOvertimeLength(double overtimeLength) {
        this.overtimeLength = overtimeLength;
    }

    public void setOvertimeStart(Date overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public void setOvertimeEnd(Date overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
    }

    public void setOvertimeMoney(double overtimeMoney) {
        this.overtimeMoney = overtimeMoney;
    }

    public void setRules(OvertimeRules rules) {
        this.rules = rules;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
