package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;
import org.nix.exception.AccountNumberException;
import org.nix.utils.SystemUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 用户类的信息对数据库操作
 */
@Service
@Transactional
public class UserService extends SupperBaseDAOImp<User> {
    //日志记录
    private static Logger logger = Logger.getLogger(UserService.class);

    @Override
    public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
        return null;
    }

    @Override
    public <T> Long findByCriteriaCount(T object) {
        return null;
    }

    /**
     * 用户登陆查询
     * 如果账号密码不匹配将抛出账号异常
     *
     * @param serialNumber 警号
     * @param password     账户密码
     * @return 账号密码是否匹配成功
     */
    public User login(String serialNumber, String password) {

        //表示警号列的列名
        String columnSiren = "serialNumber";

        if (SystemUtil.parameterNull(serialNumber, password)) {
            throw new NullPointerException();
        }

        User user = findByProperty(columnSiren, serialNumber);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AccountNumberException();
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    public Object registered(User user) {
        if (SystemUtil.parameterNull(user)) {
            throw new NullPointerException();
        }
        return save(user);
    }

    /**
     * 获得加班时间总时长
     *
     * @param user 需要获取的用户
     * @return 时长
     */
    public double overtimeAllTime(User user) {
        String sql = "SELECT sum(overtimeLength) FROM overtimerecord WHERE `user` = id";
        sql = sql.replaceAll("id", String.valueOf(user.getId()));
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return (double) query.uniqueResult();
    }

    /**
     * 查询员工加班获取的总工资
     *
     * @param user
     * @return
     */
    public double overtimeAllMoney(User user) {
        String sql = "SELECT sum(overtimeMoney) FROM overtimerecord WHERE `user` = id";
        sql = sql.replaceAll("id", String.valueOf(user.getId()));
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

        return (double) query.uniqueResult();
    }



    /**
     * 查询该用户历史统计记录
     * @param user
     * @return
     */
    public List<PersonalMonthOvertime> findPersonalMonthOvertimeByUser(User user) {

        String sql = "SELECT * FROM personalmonthovertime WHERE personalmonthovertime.`user` = ?";

        return findBySql(sql, user.getId());
    }

    /**
     * 获得用户的基础工资  以小时计算
     * @param user 需要查找的人
     * @return 以小时计算的工资
     */
    public double findeUserBasicWageHoures(User user){
        String sql = "SELECT `user`.basicWage FROM `user` where `user`.id = " + user.getId();

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

        double money = (double) query.uniqueResult();

        money = money/30/24*1.0;

        return money;
    }

}
