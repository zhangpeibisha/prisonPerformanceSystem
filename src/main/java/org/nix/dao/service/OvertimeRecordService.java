package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.dao.service.utils.Page;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
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
public class OvertimeRecordService extends SupperBaseDAOImp<OvertimeRecord> {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRecordService.class);

    @Override
    public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
        return null;
    }

    @Override
    public <T> Long findByCriteriaCount(T object) {
        return null;
    }


    /**
     * 查询这个人的加班信息记录
     *
     * @param user
     * @return
     */
    public Page findOvertimeRecordByUser(User user) {
        String sql = "    SELECT\n" +
                "        * \n" +
                "    FROM\n" +
                "        overtimerecord \n" +
                "    WHERE\n" +
                "        `user` = ?";
        List<OvertimeRecord> records = findBySql(sql,user.getId());
        return new Page().setList(records);
    }

}
