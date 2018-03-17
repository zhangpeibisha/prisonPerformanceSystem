package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
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

    @Autowired
    private UserService userService;


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
    public List<OvertimeRecord> findOvertimeRecordByUser(User user, int limit, int currentPage) {

        int start = (currentPage - 1) * limit;

        String sql = "select overtime from OvertimeRecord overtime where overtime.user = "
                + user.getId();

        return batchGetDataIteratorByHQL(sql, start, limit);
    }

    /**
     * 发现用户的加班总条数
     *
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

        return findBySqlCount(sql, user.getId());
    }


    /**
     * 发现指定用户当月的加班信息
     *
     * @param user 需要查询的用户
     * @return 当月加班信息集合
     */
    public List<OvertimeRecord> findOvertimeNowMonthRecordByUser(User user) {

        String sql = "SELECT * FROM OvertimeRecord WHERE `user` = userID " +
                "AND  YEARWEEK(`overtimerecord`.createTime) = YEARWEEK(NOW())";

        sql = sql.replaceAll("userID", String.valueOf(user.getId()));


        return getListBySQL(sql);
    }

    /**
     * 通过模糊查询用户，通过警号
     *
     * @param limit
     * @param currentPage
     * @param select      用户警号
     * @param desc
     * @return
     */
    public List<OvertimeRecord> overtimeRecordList(int limit, int currentPage, String select, boolean desc) {


        List<User> users = userService.findBlurryUserBySerialNumber(select);

        //如果用户为空，则查询全部
        String selectUsers = "";

        if (!SystemUtil.parameterNull(users) && users.size() != 0) {

            selectUsers = " where `user` in (";

            for (int i = 0; i < users.size(); i++) {

                if (i == users.size() - 1) {

                    selectUsers += users.get(i).getId();
                    break;

                }

                selectUsers += users.get(i).getId() + ",";
            }

            selectUsers += ")";

        }
        //如果查询字符不为空，但是找到的用户为空，则返回空
        else if (select != null && select.length() != 0
                && users.size() == 0){
            return new ArrayList<>();
        }

        int start = (currentPage - 1) * limit;

        String isDesc = desc ? "DESC" : "";

        String sql = "SELECT * FROM overtimerecord " + " selectUsers " +
                " ORDER BY id  isDesc" +
                " LIMIT  start , amount";

        sql = sql.replaceAll("isDesc", isDesc)
                .replaceAll("start", String.valueOf(start))
                .replaceAll("amount", String.valueOf(limit))
                .replaceAll("selectUsers", selectUsers);

        return getListBySQL(sql);
    }

    public long overtimeRecordListCount(String select) {


        List<User> users = userService.findBlurryUserBySerialNumber(select);

        //如果用户为空，则查询全部
        String selectUsers = "";

        if (!SystemUtil.parameterNull(users) && users.size() != 0) {
            selectUsers = "  where `user` in (";
            for (int i = 0; i < users.size(); i++) {
                if (i == users.size() - 1) {
                    selectUsers += users.get(i).getId();
                    break;
                }
                selectUsers += users.get(i).getId() + ",";
            }

            selectUsers += ")";
        }
        //如果查询字符不为空，但是找到的用户为空，则返回空
        else if (select != null && select.length() != 0
                && users.size() == 0){
            return 0;
        }

        String sql = "SELECT count(*) FROM overtimerecord " + " selectUsers ";

        sql = sql.replaceAll("selectUsers", selectUsers);

        return findBySqlCount(sql);
    }


}
