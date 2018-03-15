package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.exception.IdentityOverdueException;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zhangpe0312@qq.com on 2018/3/15.
 */
@Service
public class UserDetailDTO implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(UserDetailDTO.class);

    @Autowired
    private UserService userService;

    //狱警名字
    private String name;
    //基础工资
    private double basicWage;
    //警号 司法警号为 7位
    private String serialNumber;

    @Override
    public ResultDto resultDto(Object... objects) {

        if (SystemUtil.parameterNull(objects)) {
            throw new IdentityOverdueException();
        }
        User user = (User) objects[0];
        user = userService.findById(user.getId());

        setBasicWage(user.getBasicWage());
        setName(user.getName());
        setSerialNumber(user.getSerialNumber());


        return this;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
