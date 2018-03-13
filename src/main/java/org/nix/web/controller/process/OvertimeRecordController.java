package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.dao.service.OvertimeRulesService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;
import org.nix.domain.entity.User;
import org.nix.domain.entity.entitybuild.OvertimeRecordBuild;
import org.nix.exception.AuthorizationException;
import org.nix.exception.IdentityOverdueException;
import org.nix.service.overtime.CalculationSalary;
import org.nix.utils.SessionKey;
import org.nix.utils.SystemUtil;
import org.nix.utils.datetime.DateUtil;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 *
 * 加班信息控制器
 */
@RestController
public class OvertimeRecordController {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRecordController.class);

    @Autowired
    private CalculationSalary calculationSalary;

    @Autowired
    private UserService userService;

    @Autowired
    private OvertimeRecordService overtimeRecordService;

    @Autowired
    private OvertimeRulesService overtimeRulesService;
    /**
     * 添加用户加班记录
     * @param serialNumber 警号
     * @param startTime 开始加班时间
     * @param stopTime 结束加班时间
     * @param session 用户进程
     * @return 添加结果
     */
    @RequestMapping(value = "/addOvertime" , method = RequestMethod.POST)
    public
    Map<String, Object> addOvertime(@RequestParam("serialNumber")int serialNumber,
                                    @RequestParam("startTime")long startTime,
                                    @RequestParam("stopTime")long stopTime, HttpSession session)  {

        User user = (User) session.getAttribute(SessionKey.USER);

        if (SystemUtil.parameterNull(user)){
            throw new IdentityOverdueException();
        }

        if (user.getSerialNumber() != serialNumber){
            throw new AuthorizationException();
        }

        user = userService.findById(user.getId());

        Date start = new Date(startTime);

        Date stop = new Date(stopTime);

        double overtimeLength = calculationSalary.getOvertimeLength(start,stop);

        double overtimemoney = calculationSalary.getOvertimeMoney(start,overtimeLength);

        OvertimeRules rules = overtimeRulesService.findRecordByDate(start);

        OvertimeRecord record = new OvertimeRecordBuild()
                .setCreateTime()
                .setOvertimeEnd(stop)
                .setOvertimeStart(start)
                .setOvertimeLength(overtimeLength)
                .setOvertimeMoney(overtimemoney)
                .setUser(user)
                .setRules(rules)
                .build();

        overtimeRecordService.save(record);

        logger.info("记录"+ user.getId() +"的加班工资成功");

        return new ResultMap()
                .resultSuccess()
                .send();
    }

}
