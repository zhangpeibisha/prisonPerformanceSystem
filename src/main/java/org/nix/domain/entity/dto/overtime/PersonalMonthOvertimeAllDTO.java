package org.nix.domain.entity.dto.overtime;

import org.apache.log4j.Logger;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/16.
 * <p>
 * 查询所有用户的月统计信息
 * <p>
 * year：年份,
 * month:月份,
 * overtimeRecordsId: 加班记录id,
 * serialNumber: 警号,
 * name:姓名,
 * duration:加班时长,
 * overtimeSalary: 加班工资
 */
@Service
public class PersonalMonthOvertimeAllDTO implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertimeAllDTO.class);

    //总条数
    private int total;
    //月统计集合
    private List<PersonalMonthOvertime> personalMonthOvertimes = new ArrayList<>();


    @Override
    public ResultDto resultDto(Object... objects) {
        return null;
    }


}
