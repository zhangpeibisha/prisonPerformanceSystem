package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.dto.user.PresonalOvertimeInformation;
import org.nix.domain.entity.dto.user.UserInformation;
import org.nix.domain.entity.entitybuild.UserBuild;
import org.nix.exception.AccountNumberException;
import org.nix.exception.AuthorizationException;
import org.nix.exception.IdentityOverdueException;
import org.nix.utils.SessionKey;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 用户接口
 */
@RestController
public class UserController  {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInformation userInformation;

    @Autowired
    private PresonalOvertimeInformation presonalOvertimeInformation;

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

    /**
     * 用户注册接口
     *
     * @param serialNumber 警号
     * @param password     用户密码
     * @return 返回结果
     * @throws NullPointerException         空指针异常
     * @throws PropertyValueException       数据字段为空
     * @throws ConstraintViolationException 数据插入违反唯一约束
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> register(@RequestParam("serialNumber") String serialNumber,
                                 @RequestParam("password") String password)
            throws NullPointerException, PropertyValueException, ConstraintViolationException {
        User user = new UserBuild()
                .setSiren(serialNumber)
                .setPassword(password)
                .setName("系统配置002")
//                .setBasicWage(4700)
                .setCreateTime()
                .build();
        userService.registered(user);
        return new ResultMap().resultSuccess().send();
    }

    /**
     * 显示用户的个人信息
     *
     * @param session 用户进程
     * @return 返回用户个人信息
     * @throws AuthorizationException 未登录异常
     */
    @RequestMapping(value = "/information", method = RequestMethod.POST)
    @ValidatePermission
    public @ResponseBody
    Map<String, Object> information(HttpSession session) throws AuthorizationException, NullPointerException {

        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = userInformation.resultDto(user);

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA, resultDto)
                .send();
    }

    /**
     * 获取用户的加班信息条数
     *
     * @param limit       每页多少条
     * @param currentPage 当前页
     * @param session     与用户会话进程
     * @return 返回查询结果
     * @throws AuthorizationException 身份过期
     */
    @RequestMapping(value = "/personalOvertime", method = RequestMethod.POST)
    @ValidatePermission
    public @ResponseBody
    Map<String, Object> personalOvertime(
            @RequestParam("limit") int limit,
            @RequestParam("currentPage") int currentPage,
            HttpSession session) throws AuthorizationException, IdentityOverdueException, NullPointerException {

        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = presonalOvertimeInformation
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .resultDto(user);

        return new ResultMap()
                .appendParameter(ResultMap.DATA, resultDto)
                .resultSuccess()
                .send();
    }

    /**
     * 显示用户每月加班信息汇总
     *
     * @return 统计出来的每个月的信息
     */
    @RequestMapping(value = "/personalMonthOvertime", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> personalMonthOvertime() {

        return new ResultMap().resultSuccess().send();
    }

}
