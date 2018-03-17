package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.dao.service.OvertimeRulesService;
import org.nix.dao.service.PersonalMonthOvertimeService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.dto.overtime.OvertimeListDTO;
import org.nix.domain.entity.dto.overtime.PersonalMonthOvertimeAllDTO;
import org.nix.domain.entity.dto.overtime.PersonalMonthOvertimeDTO;
import org.nix.domain.entity.dto.overtime.RecordDetailDTO;
import org.nix.domain.entity.entitybuild.OvertimeRecordBuild;
import org.nix.exception.SelectException;
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
 * <p>
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

    @Autowired
    private PersonalMonthOvertimeDTO personalMonthOvertimeDTO;

    @Autowired
    private PersonalMonthOvertimeService personalMonthOvertimeService;

    @Autowired
    private PersonalMonthOvertimeAllDTO personalMonthOvertimeAllDTO;

    @Autowired
    private OvertimeListDTO overtimeListDTO;

    @Autowired
    private RecordDetailDTO recordDetailDTO;

    /**
     * 添加用户加班记录
     * 1521115646577
     *
     * @param serialNumber 警号
     * @param startTime    开始加班时间
     * @param stopTime     结束加班时间
     * @return 添加结果
     */
    @RequestMapping(value = "/addOvertime", method = RequestMethod.POST)
    @ValidatePermission
    @ResponseBody
    public Map<String, Object> addOvertime(@RequestParam("serialNumber") String serialNumber,
                                           @RequestParam("startTime") long startTime,
                                           @RequestParam("stopTime") long stopTime) {

        User user;

        user = userService.findUserBySerialNumber(serialNumber);

        Date start = new Date(startTime);

        Date stop = new Date(stopTime);

        double overtimeLength = calculationSalary.getOvertimeLength(start, stop);

        double overtimemoney = calculationSalary.getOvertimeMoney(start, overtimeLength, user);

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

        logger.info("记录" + user.getId() + "的加班工资成功");

        return new ResultMap()
                .resultSuccess()
                .send();
    }


    /**
     * 显示用户每月加班信息汇总
     *
     * @return 统计出来的每个月的信息
     */
    @RequestMapping(value = "/personalMonthOvertime", method = RequestMethod.POST)
    public Map<String, Object> personalMonthOvertime(@RequestParam("limit") int limit,
                                                     @RequestParam("currentPage") int currentPage,
                                                     HttpSession session) {

        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = personalMonthOvertimeDTO
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .resultDto(user);

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA, resultDto)
                .send();
    }

    /**
     * 管理员查询所有用户的月统计信息
     *
     * @param limit
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/overtimeMonthList", method = RequestMethod.POST)
    @ValidatePermission
    @ResponseBody
    public Map<String, Object> overtimeMonthList(@RequestParam("limit") int limit,
                                                 @RequestParam("currentPage") int currentPage) {


        ResultDto resultDto = personalMonthOvertimeAllDTO
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .resultDto();

        return new ResultMap()
                .appendParameter(ResultMap.DATA, resultDto)
                .resultSuccess()
                .send();
    }

    /**
     * 查询所有用户的加班记录
     *
     * @param limit
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/recordList", method = RequestMethod.POST)
    public Map<String, Object> recordList(@RequestParam("limit") int limit,
                                          @RequestParam("currentPage") int currentPage,
                                          @RequestParam("select")String select) {


        ResultDto resultDto = overtimeListDTO
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .setSelect(select)
                .resultDto();


        return new ResultMap()
                .appendParameter(ResultMap.DATA, resultDto)
                .resultSuccess()
                .send();
    }

    /**
     * 加班信息详情
     *
     * @param recordsId
     * @return
     */
    @RequestMapping(value = "/recordDetail", method = RequestMethod.POST)
    public Map<String, Object> recordDetail(@RequestParam("recordsId") int recordsId) {

        ResultDto resultDto = recordDetailDTO.resultDto(recordsId);

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA, resultDto)
                .send();
    }

    /**
     * 有问题，不能确定加班信息，需要一个加班信息id
     *
     * @param serialNumber
     * @param startTime
     * @param stopTime
     * @return
     */
    @RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
    public Map<String, Object> updateRecord(
            @RequestParam("recordId") int recordId,
            @RequestParam("serialNumber") String serialNumber,//警号不需要
            @RequestParam("startTime") long startTime,
            @RequestParam("stopTime") long stopTime) {


        OvertimeRecord record = overtimeRecordService.findById(recordId);

        if (SystemUtil.parameterNull(record)) {
            throw new SelectException();
        }

        User user = record.getUser();

        Date start = new Date(startTime);

        Date stop = new Date(stopTime);

        double overtimeLength = calculationSalary.getOvertimeLength(start, stop);

        double overtimemoney = calculationSalary.getOvertimeMoney(start, overtimeLength, user);

        record.setOvertimeLength(overtimeLength);
        record.setOvertimeMoney(overtimemoney);
        record.setOvertimeStart(start);
        record.setOvertimeEnd(stop);
        overtimeRecordService.update(record);

        logger.info("更新用户" + user.getId()
                + DateUtil.getYear(start) + "年" + DateUtil.getMonth(start) + "月"
                + "的加班信息成功");

        return new ResultMap()
                .resultSuccess()
                .send();
    }


    @RequestMapping(value = "/deleteRecord" , method = RequestMethod.POST)
    public
    Map<String, Object> deleteRecord(@RequestParam("recordId")int recordId )  {

        overtimeRecordService.delete(recordId);

        logger.info("删除加班信息成功 加班信息id：" + recordId);

        return new ResultMap().resultSuccess().send();
    }



}
