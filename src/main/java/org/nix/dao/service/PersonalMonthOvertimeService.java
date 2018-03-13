package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 */
@Service
public class PersonalMonthOvertimeService extends SupperBaseDAOImp<PersonalMonthOvertime> {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertimeService.class);

    @Override
    public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
        return null;
    }

    @Override
    public <T> Long findByCriteriaCount(T object) {
        return null;
    }

}
