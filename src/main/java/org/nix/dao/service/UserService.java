package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
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
     * @param siren    警号
     * @param password 账户密码
     * @return 账号密码是否匹配成功
     */
    public User login(String siren, String password) {

        //表示警号列的列名
        String columnSiren = "siren";

        if (SystemUtil.parameterNull(siren, password)) {
            throw new NullPointerException();
        }

        User user = findByProperty(columnSiren, siren);

        if (user== null || !user.getPassword().equals(password)) {
            throw new AccountNumberException();
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    public void registered(User user) {
        if (SystemUtil.parameterNull(user)) {
            throw new NullPointerException();
        }
        Object result = save(user);
        logger.info("save add result:" + result);
    }
}