package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.User;

import java.util.Date;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 *
 * 创建User的方法
 */
public class  UserBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(UserBuild.class);

    User user = new User();

    public UserBuild setSerialNumber(int serialNumber) {
        user.setSerialNumber(serialNumber);
        return this;
    }
    public UserBuild setPassword(String password) {
       user.setPassword(password);
        return this;
    }
    public UserBuild setName(String name) {
        user.setName(name);
        return this;
    }
    public UserBuild setBasicWage(double basicWage) {
        user.setBasicWage(basicWage);
        return this;
    }
    public UserBuild setOvertimeRecords(Set<OvertimeRecord> overtimeRecords) {
        user.setOvertimeRecords(overtimeRecords);
        return this;
    }
    public UserBuild setRole(Role role) {
        user.setRole(role);
        return this;
    }

    public UserBuild setCreateTime(){
        user.setCreateTime(new Date());
        return this;
    }

    public User build(){
        return user;
    }
}
