package org.nix.service.overtime;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.dao.service.OvertimeRulesService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;
import org.nix.exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 *
 * 加工加班信息
 */
@Service
public class CalculationSalary {
    //日志记录
    private static Logger logger = Logger.getLogger(CalculationSalary.class);

    @Autowired
    private OvertimeRulesService overtimeRulesService;

    /**
     * 计算加班工资
     * @param nowTime 当前时间，为了得出加班规则
     * @param overtimeLength 加班时长
     * @return 加班应该获取的工资
     */
    public double getOvertimeMoney(Date nowTime , double overtimeLength){

        //找到当前日期应该使用的规则
        OvertimeRules rules = overtimeRulesService.findRecordByDate(nowTime);

        if (rules == null){
            throw new SelectException();
        }

        return overtimeLength * rules.getPayMultiples();
    }

    /**
     * 计算加班时长
     * @param start 开始时间
     * @param stop 结束时间
     * @return 小时
     */
    public double getOvertimeLength(Date start , Date stop){

        return (stop.getTime() - start.getTime())/1000/60/60*1.0;

    }

}
