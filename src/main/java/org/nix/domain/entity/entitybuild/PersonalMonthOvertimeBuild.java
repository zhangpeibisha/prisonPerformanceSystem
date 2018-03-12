package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;


import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/12.
 *
 * 创建personalMonthOvertime 这个类的创建者类
 */
public class PersonalMonthOvertimeBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertimeBuild.class);

    PersonalMonthOvertime overtime = new PersonalMonthOvertime();


    public PersonalMonthOvertimeBuild setYear(int year) {
        overtime.setYear(year);
        return this;
    }

    public PersonalMonthOvertimeBuild setMonth(int month) {
        overtime.setMonth(month);
        return this;
    }

    public PersonalMonthOvertimeBuild setDuration(double duration) {
        overtime.setDuration(duration);
        return this;
    }

    public PersonalMonthOvertimeBuild setOvertimeSalary(double overtimeSalary) {
        overtime.setOvertimeSalary(overtimeSalary);
        return this;
    }

    public PersonalMonthOvertimeBuild setUser(User user) {
        overtime.setUser(user);
        return this;
    }

    public PersonalMonthOvertimeBuild setCreateTime(){
        overtime.setCreateTime(new Date());
        return this;
    }

    public PersonalMonthOvertime build(){
        return overtime;
    }
}
