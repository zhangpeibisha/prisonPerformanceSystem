package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;
import org.nix.domain.entity.entitybuild.PersonalMonthOvertimeBuild;
import org.nix.utils.datetime.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * Create by zhangpe0312@qq.com on 2018/3/13.
 */
@Service
public class PersonalMonthOvertimeService extends SupperBaseDAOImp<PersonalMonthOvertime> {
    //日志记录
    private static Logger logger = Logger.getLogger(PersonalMonthOvertimeService.class);

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
     * 找到历史统计数据
     *
     * @param user
     * @param limit
     * @param currentPage
     * @return
     */
    public List<PersonalMonthOvertime> findPersonalMonthOvertimeByUser(User user, int limit, int currentPage) {

        String sql = "SELECT * FROM personalmonthovertime WHERE `user` = userid limit start,limitSize";

        int start = (currentPage - 1) * limit;

        sql = sql.replaceAll("userid", String.valueOf(user.getId()))
                .replaceAll("start", String.valueOf(start))
                .replaceAll("limitSize", String.valueOf(limit));

        return getListBySQL(sql);
    }

    public void updataOrSave(PersonalMonthOvertime personalMonthOvertime) {
        sessionFactory.getCurrentSession().saveOrUpdate(personalMonthOvertime);
    }


    public PersonalMonthOvertime findNowMonthOvertime(User user) {

        Date date = new Date();

        int year = DateUtil.getYear(date);

        int month = DateUtil.getMonth(date);

        String sql = "SELECT * FROM personalmonthovertime WHERE `user` = ? and year = ? and month = ?";

        return findUniqueBySql(sql, user.getId(), year, month);

    }

    /**
     * 删除这个月的所有加班统计信息
     */
//    public void deleteAllNowMonthOvertime() {
//        //得到当前年月
//        Date nowTime = new Date();
//        int year = DateUtil.getYear(nowTime);
//        int month = DateUtil.getMonth(nowTime);
//
//        String sql = "DELETE FROM personalmonthovertime WHERE YEAR = ? and month = ?";
//
//        int result = batchUpdateOrDelete(sql, year, month);
//
//        logger.info("删除了" + result + "条当月统计加班信息");
//    }

    public long findPersonalMonthOvertimeCountByUser(User user) {

        String sql = "select count(*) " +
                "from personalmonthovertime " +
                "where user = " + user.getId();

        return findCountBySQL(sql);
    }


    /**
     * 统计当前月所有用户的加班记录
     *
     * @return
     */
    public List<PersonalMonthOvertime> calculationOvertimeNowMonthAll() {

        List<PersonalMonthOvertime> personalMonthOvertimes = new ArrayList<>();

        String sql = "SELECT YEAR(NOW()) AS year, MONTH(NOW()) AS month , \n" +
                "SUM(overtimerecord.overtimeLength) AS duration , SUM(overtimerecord.overtimeMoney) AS overtimeSalary, `user`\n" +
                "FROM overtimerecord \n" +
                "WHERE YEARWEEK(NOW()) = YEARWEEK(overtimerecord.createTime) GROUP BY `user`";

        Query query = sessionFactory
                .getCurrentSession().createSQLQuery(sql)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Object> list = query.list();

        for (int i = 0;i<list.size();i++) {

            Map<String, Object> map = (Map<String, Object>) list.get(i);

            //获取用户
            User user = userService.findById((Serializable) map.get("user"));

            PersonalMonthOvertime personalMonthOvertime =
                    new PersonalMonthOvertimeBuild()
                    .setYear(((BigInteger) map.get("year")).intValue())
                    .setMonth(((BigInteger) map.get("month")).intValue())
                    .setUser(user)
                    .setCreateTime()
                    .setOvertimeSalary((Double) map.get("overtimeSalary"))
                    .setDuration((Double) map.get("duration"))
                    .build();

            personalMonthOvertimes.add(personalMonthOvertime);

        }

        return personalMonthOvertimes;

    }

    /**
     * 批量添加当前月加班统计信息
     */
    public void savePersonalNowMonthOvertimeAll(List<PersonalMonthOvertime> personalMonthOvertimes) {

       batchInsertByHQL(personalMonthOvertimes, personalMonthOvertimes.size());

    }

    /**
     * 统计所有用户当前月的加班信息并存入统计表中
     */
    public void calculationOvertimeNowMonthAllAndSave() {

        //再添加统计信息
        savePersonalNowMonthOvertimeAll(calculationOvertimeNowMonthAll());
    }


    /**
     * 删除当前月、年的用户的加班统计
     */
    public void deletePersonalNowMonthOvertimeAll(){

        String sql = "DELETE FROM personalmonthovertime \n" +
                "WHERE \n" +
                "YEAR(personalmonthovertime.createTime) = YEAR(NOW()) " +
                "AND MONTH(personalmonthovertime.createTime) = MONTH(NOW())"+
                "and DAY(NOW()) != DAY(personalmonthovertime.createTime)";

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);

        int result =  query.executeUpdate();

       logger.info("删除了所有用户"+result+"条加班统计信息");

    }


    /**
     * 通过分页查询
     *
     * @param limit
     * @param currentPage
     * @return
     */
    public List<PersonalMonthOvertime> findAllPage(int limit, int currentPage) {

        int start = (currentPage - 1) * limit;

        String sql = "SELECT * FROM personalmonthovertime LIMIT " +
                start
                + "," +
                limit;

        return getListBySQL(sql);
    }

}
