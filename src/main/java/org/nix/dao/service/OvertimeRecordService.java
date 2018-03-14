package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
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
    public List<OvertimeRecord> findOvertimeRecordByUser(User user , int limit , int currentPage) {

        int start = (currentPage-1)*limit;

        String sql = "select overtime from OvertimeRecord overtime where overtime.user = "
                + user.getId();

        return batchGetDataIteratorByHQL(sql,start,limit);
    }

    /**
     * 发现用户的加班总条数
     * @param user
     * @return
     */
    public long findOvertimeRecordCountByUser(User user) {

        String sql = "    SELECT\n" +
                "        count(*) \n" +
                "    FROM\n" +
                "        overtimerecord \n" +
                "    WHERE\n" +
                "        `user` = ?";

        return findBySqlCount(sql,user.getId());
    }


    /**
     * 发现指定用户当月的加班信息
     *
     * @param user 需要查询的用户
     * @return 当月加班信息集合
     */
    public List<OvertimeRecord> findOvertimeNowMonthRecordByUser(User user) {
//
//        String sql = "SELECT * FROM overtimerecord WHERE `user` = userID " +
//                "AND  YEARWEEK(`overtimerecord`.createTime) = YEARWEEK(NOW())";

//        String sql = "SELECT * FROM overtimerecord WHERE `user` = userID " +
//                "AND  YEARWEEK(`overtimerecord`.createTime) = YEARWEEK(NOW())";

        String sql = "SELECT * FROM OvertimeRecord WHERE `user` = userID " +
                "AND  YEARWEEK(`overtimerecord`.createTime) = YEARWEEK(NOW())";

        sql = sql.replaceAll("userID", String.valueOf(user.getId()));

//        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
//
//        query.setCacheable(false);

        return getListBySQL(sql);
    }

}
