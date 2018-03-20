package org.nix.domain.entity.dto.overtime;

import org.apache.log4j.Logger;
import org.nix.dao.service.PersonalMonthOvertimeService;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.entitybuild.UserBuild;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonalMonthOvertimeService personalMonthOvertimeService;

    //总条数
    private Long total;
    //月统计集合
    private List<PersonalMonthOvertime> personalMonthOvertimes = new ArrayList<>();

    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;

    @Override
    public ResultDto resultDto(Object... objects) {

        boolean isupdata = personalMonthOvertimeService.deletePersonalNowMonthOvertimeAll();

        if (isupdata){
            personalMonthOvertimeService.calculationOvertimeNowMonthAllAndSave();
        }

        personalMonthOvertimes = personalMonthOvertimeService.findAllPage(limit, currentPage);

        total = personalMonthOvertimeService.findAllCount();

        setParmaterNull();

        return this;
    }

    /**
     * 清理不需要的数据
     */
    public void setParmaterNull() {
        for (int i = 0; i < personalMonthOvertimes.size(); i++) {

            User user = personalMonthOvertimes.get(i).getUser();

            user = new UserBuild()
                    .setName(user.getName())
                    .setSerialNumber(user.getSerialNumber())
                    .build();

            personalMonthOvertimes.get(i).setUser(user);
        }
    }


    public Long getTotal() {
        return total;
    }

    public List<PersonalMonthOvertime> getPersonalMonthOvertimes() {
        return personalMonthOvertimes;
    }

    public PersonalMonthOvertimeAllDTO setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public PersonalMonthOvertimeAllDTO setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }
}
