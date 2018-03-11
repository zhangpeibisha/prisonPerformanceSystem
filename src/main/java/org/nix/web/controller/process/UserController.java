package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.dto.user.UserInformation;
import org.nix.domain.entity.entitybuild.UserBuild;
import org.nix.exception.AccountNumberException;
import org.nix.exception.AuthorizationException;
import org.nix.utils.SessionKey;
import org.nix.utils.SystemUtil;
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

    @Autowired
    private UserInformation userInformation;

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
//                .setName("系统配置001")
//                .setBasicWage(4700)
                .setCreateTime()
                .build();
        userService.registered(user);
        return new ResultMap().resultSuccess().send();
    }

    /**
     * 显示用户的个人信息
     * @param session 用户进程
     * @return 返回用户个人信息
     * @throws AuthorizationException 未登录异常
     */
    @RequestMapping(value = "/information", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> information(HttpSession session) throws AuthorizationException{

        User user = (User) session.getAttribute(SessionKey.USER);

        if (SystemUtil.parameterNull(user)){
            throw new AuthorizationException();
        }

        ResultDto resultDto = userInformation.resultDto(user);

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA,resultDto)
                .send();
    }
}
