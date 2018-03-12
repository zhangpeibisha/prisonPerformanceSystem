package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * Create by zhangpe0312@qq.com on 2018/3/12.
 *
 * 显示用户每月加班信息汇总
 */
@Entity
@Table(name = "PersonalMonthOvertime")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "user"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PersonalMonthOvertime extends BaseEntity{
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertime.class);

    //记录年份
    private int year;
    //记录月份
    private int month;
    //记录加班时长
    private double duration;
    //记录加班工资
    private double overtimeSalary;
    //这条记录的主人
    private User user;

    @Column(name = "year",length = 4,nullable = false)
    public int getYear() {
        return year;
    }

    @Column(name = "month",length = 2,nullable = false)
    public int getMonth() {
        return month;
    }

    @Column(name = "duration",length = 10,nullable = false)
    public double getDuration() {
        return duration;
    }

    @Column(name = "overtimeSalary",length = 10,nullable = false)
    public double getOvertimeSalary() {
        return overtimeSalary;
    }

    @ManyToOne(targetEntity = User.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setOvertimeSalary(double overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
