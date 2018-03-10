package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.OvertimeRules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
