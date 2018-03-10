package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.build.UserBuild;
import org.nix.exception.AccountNumberException;
import org.nix.utils.SessionKey;
import org.nix.web.controller.exception.UserExceptionResult;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 用户接口
 */
@Controller
public class UserController extends UserExceptionResult {

    @Autowired
    private UserService userService;

    //日志记录
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 登陆接口
     *
     * @param userName 用户警号
     * @param password 用户密码
     * @param session  用户进程
     * @return 查询结果
     * @throws AccountNumberException 用户账号密码错误异常
     * @throws NullPointerException   空指针异常
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> login(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              HttpSession session) throws AccountNumberException, NullPointerException {
        User user = userService.login(userName, password);
        session.setAttribute(SessionKey.USER, user);
        logger.info("登陆成功");
        return new ResultMap().resultSuccess().send();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> register(@RequestParam("serialNumber") String serialNumber,
                                 @RequestParam("password") String password)
            throws NullPointerException, PropertyValueException, ConstraintViolationException {
        User user = new UserBuild()
                .setSiren(serialNumber)
                .setPassword(password)
//                .setName("系统配置001")
//                .setBasicWage(4700)
                .setCreateTime()
                .build();
        userService.registered(user);
        return new ResultMap().resultSuccess().send();
    }
}
