package org.nix.domain.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.exception.AuthorizationException;
import org.nix.exception.IdentityOverdueException;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 显示用户的个人信息所返回的信息
 */
@Service
public class UserInformation implements ResultDto<User> {
    //日志记录
    private static Logger logger = Logger.getLogger(UserInformation.class);

    @Autowired
    private UserService userService;

    //加班总时长
    private double overtimeAllLenth;
    //加班总工资
    private double overtimeAllmoney;
    //狱警名字
    private String name;
    //基础工资
    private double basicWage;
    //警号 司法警号为 7位
    private String siren;

    @Override
    public ResultDto resultDto(User user) throws IdentityOverdueException, NullPointerException {

        if (SystemUtil.parameterNull(user)) {
            throw new IdentityOverdueException();
        }

        user = userService.findById(user.getId());

        overtimeAllLenth = userService.overtimeAllTime(user);
        overtimeAllmoney = userService.overtimeAllMoney(user);

        setOvertimeAllLenth(overtimeAllLenth);
        setOvertimeAllmoney(overtimeAllmoney);
        setBasicWage(user.getBasicWage());
        setName(user.getName());
        setSiren(user.getSiren());

        return this;
    }

    public double getOvertimeAllLenth() {
        return overtimeAllLenth;
    }

    public void setOvertimeAllLenth(double overtimeAllLenth) {
        this.overtimeAllLenth = overtimeAllLenth;
    }

    public double getOvertimeAllmoney() {
        return overtimeAllmoney;
    }

    public void setOvertimeAllmoney(double overtimeAllmoney) {
        this.overtimeAllmoney = overtimeAllmoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(double basicWage) {
        this.basicWage = basicWage;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }
}
