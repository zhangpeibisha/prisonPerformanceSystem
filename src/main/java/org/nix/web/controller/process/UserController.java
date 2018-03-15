package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.hibernate.QueryException;
import org.hibernate.exception.ConstraintViolationException;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.RoleService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.Role;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.dto.user.PresonalOvertimeInformationDTO;
import org.nix.domain.entity.dto.user.UserDetailDTO;
import org.nix.domain.entity.dto.user.UserInformationDTO;
import org.nix.domain.entity.dto.user.UserListDTO;
import org.nix.domain.entity.entitybuild.UserBuild;
import org.nix.exception.AccountNumberException;
import org.nix.exception.AuthorizationException;
import org.nix.exception.IdentityOverdueException;
import org.nix.exception.SelectException;
import org.nix.utils.SessionKey;
import org.nix.utils.SystemUtil;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 * <p>
 * 用户接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInformationDTO userInformation;

    @Autowired
    private PresonalOvertimeInformationDTO presonalOvertimeInformation;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserListDTO userListDTO;

    @Autowired
    private UserDetailDTO userDetailDTO;

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
    public Map<String, Object> login(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     HttpSession session) throws AccountNumberException, NullPointerException {

        User user = userService.login(userName, password);
        session.setAttribute(SessionKey.USER, user);
        String roleName = user.getRole().getName();
        logger.info(user.getName() + "登陆成功 角色为" + roleName);

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.ROLE_CLASS, roleName.equals("普通用户") ? 0 : 1)
                .send();
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
    public Map<String, Object> register(@RequestParam("serialNumber") String serialNumber,
                                        @RequestParam("password") String password,
                                        @RequestParam("userName") String userName)
            throws NullPointerException, PropertyValueException, DataAccessException {

        String column = "name";
        Role role = roleService.findByProperty(column, "普通用户");

        User user = new UserBuild()
                .setSerialNumber(serialNumber)
                .setPassword(password)
                .setName(userName)
                .setBasicWage(4700)
                .setCreateTime()
                .setRole(role)
                .build();

        Object result = userService.registered(user);
        logger.info(result + " 用户执行了注册操作成功");
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
    public Map<String, Object> information(HttpSession session) throws AuthorizationException, NullPointerException {

        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = userInformation.resultDto(user);

        logger.info("查看了用户" + user.getId() + "的个人信息");

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
    public Map<String, Object> personalOvertime(
            @RequestParam("limit") int limit,
            @RequestParam("currentPage") int currentPage,
            HttpSession session) throws AuthorizationException, IdentityOverdueException, NullPointerException {

        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = presonalOvertimeInformation
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .resultDto(user);

        logger.info("查看了用户" + user.getId() + "的个人加班信息");

        return new ResultMap()
                .appendParameter(ResultMap.DATA, resultDto)
                .resultSuccess()
                .send();
    }


    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public Map<String, Object> userList(@RequestParam("limit") int limit,
                                        @RequestParam("currentPage") int currentPage,
                                        HttpSession session) {

        User user = (User) session.getAttribute(SessionKey.USER);

        if (SystemUtil.parameterNull(user)) {
            throw new IdentityOverdueException();
        }

        user = userService.findById(user.getId());

        if (!user.getRole().getName().equals("管理员")) {
            throw new AuthorizationException();
        }

        ResultDto resultDto = userListDTO
                .setLimit(limit)
                .setCurrentPage(currentPage)
                .setDesc(false)
                .resultDto();

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA, resultDto)
                .send();
    }

    /**
     * 查看用户详细信息
     *
     * @param session 用户进程
     * @return
     */
    @RequestMapping(value = "/userDetail", method = RequestMethod.POST)
    public Map<String, Object> userDetail(HttpSession session) {


        User user = (User) session.getAttribute(SessionKey.USER);

        ResultDto resultDto = userDetailDTO.resultDto(user);

        logger.info("查看了用户" + user.getId() + "的详细个人信息");

        return new ResultMap()
                .resultSuccess()
                .appendParameter(ResultMap.DATA, resultDto)
                .send();

    }

    /**
     * 管理员更新用户基础信息
     *
     * @param userId       需要修改用户的id
     * @param name         用户更新的姓名
     * @param serialNumber 用户警号
     * @param password     用户密码
     * @param salary       用户工资
     * @param session      用户进程
     * @return 更新结果
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map<String, Object> updateUser(@RequestParam("userId") int userId,
                                          @RequestParam("name") String name,
                                          @RequestParam("serialNumber") String serialNumber,
                                          @RequestParam("password") String password,
                                          @RequestParam("salary") double salary,
                                          HttpSession session) {

        User user = (User) session.getAttribute(SessionKey.USER);
        if (SystemUtil.parameterNull(user)) {
            throw new IdentityOverdueException();
        }

        //查询当前用户是否为管理
        user = userService.loadById(user.getId());

        Role role = user.getRole();

        if (!role.getName().equals("管理员")) {
            throw new AuthorizationException();
        }

        //获取需要更新的用户信息
        user = userService.findById(userId);

        if (SystemUtil.parameterNull(user)) {
            throw new SelectException();
        }

        user.setName(name);
        user.setPassword(password);
        user.setSerialNumber(serialNumber);
        user.setBasicWage(salary);
        userService.update(user);
        logger.info("为用户" + user.getId() + "修改信息成功");

        return new ResultMap()
                .resultSuccess()
                .send();
    }


}
