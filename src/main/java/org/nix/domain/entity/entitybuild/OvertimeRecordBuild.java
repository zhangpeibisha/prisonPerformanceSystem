package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;
import org.nix.domain.entity.User;

import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class OvertimeRecordBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRecordBuild.class);

    OvertimeRecord record = new OvertimeRecord();

    public OvertimeRecordBuild setOvertimeLength(double overtimeLength) {
        record.setOvertimeLength(overtimeLength);
        return this;
    }

    public OvertimeRecordBuild setOvertimeStart(Date overtimeStart) {
        record.setOvertimeStart(overtimeStart);
        return this;
    }

    public OvertimeRecordBuild setOvertimeEnd(Date overtimeEnd) {
        record.setOvertimeEnd(overtimeEnd);
        return this;
    }

    public OvertimeRecordBuild setOvertimeMoney(double overtimeMoney) {
        record.setOvertimeMoney(overtimeMoney);
        return this;
    }

    public OvertimeRecordBuild setRules(OvertimeRules rules) {
        record.setRules(rules);
        return this;
    }

    public OvertimeRecordBuild setUser(User user) {
        record.setUser(user);
        return this;
    }

    public OvertimeRecordBuild setCreateTime() {
        record.setCreateTime(new Date());
        return this;
    }

    public OvertimeRecord build(){
        return record;
    }
}
