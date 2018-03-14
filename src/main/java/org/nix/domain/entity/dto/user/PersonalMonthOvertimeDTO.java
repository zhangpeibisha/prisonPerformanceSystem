package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.dao.service.PersonalMonthOvertimeService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.entitybuild.PersonalMonthOvertimeBuild;
import org.nix.utils.datetime.DateUtil;
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
public class PersonalMonthOvertimeDTO implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertimeDTO.class);

    @Autowired
    private PersonalMonthOvertimeService personalMonthOvertimeService;

    @Autowired
    private UserService userService;


    //记录总条数
    private int total;

    //需要加工的原始数据
    private List<PersonalMonthOvertimeDTO> oldData = new ArrayList<>();

    //加工组合后的数据
    private List<PersonalMonthOvertime> personalMonthOvertimes = new ArrayList<>();

    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;

    private User user;

    /**
     * 查询单个用户的月加班情况
     *
     * @param objects
     * @return
     */
    @Override
    public ResultDto resultDto(Object... objects) {

        /**
         * 如果不是用户类抛出
         */
        if (!(objects[0] instanceof User)) {
            logger.error("查询月加班信息失败，查询参数异常");
            throw new IllegalArgumentException();
        }

        User user = (User) objects[0];
        this.user = user;
        //获取当前月总数
        getNowMonthOvertimeRecord(user);
        //获取历史统计记录
        personalMonthOvertimes.addAll(personalMonthOvertimeService
                .findPersonalMonthOvertimeByUser(user,limit,currentPage));

        return this;
    }

    /**
     * 获取当前用户、当前年、当前月的加班信息
     */
    public void getNowMonthOvertimeRecord(User user) {
        //获取当前月用户的加班信息
        List<OvertimeRecord> overtimeRecords =
                userService.findOvertimeNowMonthRecordByUser(user);

        processOvertimeRecordNowMonth(overtimeRecords);
    }

    /**
     * 处理当前用户、当前月、当前年的零散加班信息
     */
    public void processOvertimeRecordNowMonth(List<OvertimeRecord> list) {
        //这个月加班总时间
        double overtimeLength = 0;
        //这个月加班总工资
        double overtimeMoney = 0;

        int month = DateUtil.getMonth(list.get(0).getOvertimeStart());
        int year = DateUtil.getYear(list.get(0).getOvertimeStart());

        for (int i = 0; i < list.size(); i++) {
            overtimeLength += list.get(i).getOvertimeLength();
            overtimeMoney += list.get(i).getOvertimeMoney();
        }

        PersonalMonthOvertime personalMonthOvertime
                = new PersonalMonthOvertimeBuild()
                .setMonth(month)
                .setYear(year)
                .setCreateTime()
                .setDuration(overtimeLength)
                .setOvertimeSalary(overtimeMoney)
                .setUser(user)
                .build();

        personalMonthOvertimeService.update(personalMonthOvertime);
    }

    public void setTotal() {
        this.total = personalMonthOvertimes.size();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }



}


