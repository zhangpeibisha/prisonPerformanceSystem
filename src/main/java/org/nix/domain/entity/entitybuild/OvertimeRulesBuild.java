package org.nix.domain.entity.entitybuild;

import org.apache.log4j.Logger;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;

import java.util.Date;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class OvertimeRulesBuild {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRulesBuild.class);

    OvertimeRules rules = new OvertimeRules();

    public OvertimeRulesBuild setName(String name) {
        rules.setName(name);
        return this;
    }

    public OvertimeRulesBuild setDescription(String description) {
        rules.setDescription(description);
        return this;
    }

    public OvertimeRulesBuild setPayMultiples(double payMultiples) {
        rules.setPayMultiples(payMultiples);
        return this;
    }

    public OvertimeRulesBuild setNote(String note) {
        rules.setNote(note);
        return this;
    }

//    public OvertimeRulesBuild setOvertimeRecords(Set<OvertimeRecord> overtimeRecords) {
//        rules.setOvertimeRecords(overtimeRecords);
//        return this;
//    }

    public OvertimeRulesBuild setCreateTime() {
        rules.setCreateTime(new Date());
        return this;
    }

    public OvertimeRules build(){
        return rules;
    }
}
