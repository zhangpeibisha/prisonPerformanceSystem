package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.OvertimeRules;
import org.nix.utils.datetime.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
@Service
@Transactional
public class OvertimeRulesService extends SupperBaseDAOImp<OvertimeRules> {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRulesService.class);

    @Override
    public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
        return null;
    }

    @Override
    public <T> Long findByCriteriaCount(T object) {
        return null;
    }

    /**
     * 通过日期查找加班工资规则
     * @param date 需要查找的日期
     * @return 规则
     */
    public OvertimeRules findRecordByDate(Date date){

        //代表星期几
        int week = DateUtil.getWeek(date).getNumber();

        //1-5 使用正常加班 6-7为周末加班
        String selectRules = null;

        //平时加班
        if (1<=week&&week<=5){
            selectRules = "'usually'";
        }else if (6<=week&& week<=7){
            selectRules = "'weekend'";
        }

        String sql = "SELECT * FROM overtimerules WHERE description = " + selectRules;

        return findUniqueBySql(sql);
    }


}
