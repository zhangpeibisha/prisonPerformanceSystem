package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.PersonalMonthOvertime;
import org.nix.domain.entity.User;
import org.nix.utils.datetime.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 找到历史统计数据
     * @param user
     * @param limit
     * @param currentPage
     * @return
     */
    public List<PersonalMonthOvertime> findPersonalMonthOvertimeByUser(User user , int limit , int currentPage ){

        String sql = "SELECT * FROM personalmonthovertime WHERE `user` = userid limit start,limitSize";

        int start = (currentPage-1)*limit;

        sql = sql.replaceAll("userid", String.valueOf(user.getId()))
                .replaceAll("start", String.valueOf(start))
                .replaceAll("limitSize", String.valueOf(limit));

        return getListBySQL(sql);
    }

    public void updataOrSave(PersonalMonthOvertime personalMonthOvertime){
        sessionFactory.getCurrentSession().saveOrUpdate(personalMonthOvertime);
    }


    public PersonalMonthOvertime findNowMonthOvertime(User user){

        Date date = new Date();

        int year = DateUtil.getYear(date);

        int month = DateUtil.getMonth(date);

        String sql = "SELECT * FROM personalmonthovertime WHERE `user` = ? and year = ? and month = ?";

        return findUniqueBySql(sql , user.getId() , year, month);

    }
}
