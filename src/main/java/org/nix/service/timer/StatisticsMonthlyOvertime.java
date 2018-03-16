package org.nix.service.timer;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.dao.service.PersonalMonthOvertimeService;
import org.nix.utils.datetime.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 * <p>
 * 每个月定时统计每个用户的加班信息，并计入月统计表中
 */
@Component
public class StatisticsMonthlyOvertime {
    //日志记录
    private static Logger logger = Logger.getLogger(StatisticsMonthlyOvertime.class);

    @Autowired
    private OvertimeRecordService overtimeRecordService;

    @Autowired
    private PersonalMonthOvertimeService personalMonthOvertimeService;

    /**
     * 每个月末统计加班信息  于23：00 28日处理
     *
     *
     */
    @Scheduled(cron = "0 0 23 28 * ?")
    public void statisticsMonthlyOvertime() {


        //首先删除这个月的一些统计信息
        personalMonthOvertimeService.deletePersonalNowMonthOvertimeAll();

        //再通过具体统计的信息写入数据库中

        personalMonthOvertimeService.calculationOvertimeNowMonthAllAndSave();

    }




}
