package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 显示用户每月加班信息汇总
 */
public class personalMonthOvertime implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(personalMonthOvertime.class);

    //记录总条数
    private int total;

    //需要加工的原始数据
    private List<OvertimeRecord> oldData = new ArrayList<>();
    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;


    @Override
    public ResultDto resultDto(Object... objects) {
        return null;
    }
}


