package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.User;
import org.nix.domain.entity.base.BaseEntity;
import org.nix.domain.entity.build.UserBuild;
import org.nix.domain.entity.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 *
 * 显示用户的个人信息所返回的信息
 */
@Service
public class UserInformation implements ResultDto<User>{
    //日志记录
    private static Logger logger = Logger.getLogger(UserInformation.class);

    @Autowired
    private UserService userService;

    @Override
    public BaseEntity resultDto(User user) {

        double money = userService.overtimeAllMoney(user);
        double time = userService.overtimeAllTime(user);

        return new UserBuild()
                .setSiren(user.getSiren())
                .setName(user.getName())
                .setBasicWage(user.getBasicWage())
                .build();
    }
}
