package org.nix.service.timer;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 * <p>
 * 每个月定时统计每个用户的加班信息，并计入月统计表中
 */
@Component
public class StatisticsMonthlyOvertime {
    //日志记录
    private static Logger logger = Logger.getLogger(StatisticsMonthlyOvertime.class);

//    @Scheduled(fixedRate = 5000)
    public void test() {
        System.out.println("AAAA***********5秒执行一次");
    }

//    @Scheduled(fixedRate = 10000)
    public void testa() {
        System.out.println("BBBB***********10秒执行一次");
    }




}
