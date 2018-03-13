package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.dao.service.PersonalMonthOvertimeService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 显示用户每月加班信息汇总
 */
@Service
public class PersonalMonthOvertime implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertime.class);

    @Autowired
    private PersonalMonthOvertimeService personalMonthOvertimeService;

    @Autowired
    private UserService userService;

    //记录总条数
    private int total;

    //需要加工的原始数据
    private List<PersonalMonthOvertime> oldData = new ArrayList<>();
    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;

    /**
     * 查询单个用户的月加班情况
     * @param objects
     * @return
     */
    @Override
    public ResultDto resultDto(Object... objects) {

        /**
         * 如果不是用户类抛出
         */
        if (!(objects[0] instanceof User)){
            logger.error("查询月加班信息失败，查询参数异常");
            throw new IllegalArgumentException();
        }

        User user = (User) objects[0];

        return null;
    }

    /**
     * 获取当前用户、当前年、当前月的加班信息
     */
    public void getNowMonthOvertimeRecord(User user){
        //获取当前月用户的加班信息
        List<OvertimeRecord> overtimeRecords =
                userService.findOvertimeNowMonthRecordByUser(user);

        processOvertimeRecordNowMonth(overtimeRecords);
    }

    /**
     * 处理当前用户、当前月、当前年的零散加班信息
     */
    public void processOvertimeRecordNowMonth(List<OvertimeRecord> list){
        //这个月加班总时间
        double overtimeLength;
        //这个月加班总工资
        double overtimeMoney;
    }



}


