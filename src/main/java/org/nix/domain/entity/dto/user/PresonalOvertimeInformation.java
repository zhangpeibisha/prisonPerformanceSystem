package org.nix.domain.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import org.nix.dao.service.UserService;
import org.nix.dao.service.utils.Page;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.exception.AuthorizationException;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 */
public class PresonalOvertimeInformation implements ResultDto<User> {
    //日志记录
    private static Logger logger = Logger.getLogger(PresonalOvertimeInformation.class);

    @Autowired
    private UserService userService;

    //加班信息
    private List records = new ArrayList();
    //加班总条数
    private int total;

    private int limit;

    private int currentPage;

    public PresonalOvertimeInformation(int limit, int currentPage) {
        this.limit = limit;
        this.currentPage = currentPage;
    }

    @Override
    public ResultDto resultDto(User user) throws AuthorizationException {
        if (SystemUtil.parameterNull(user)) {
            throw new AuthorizationException();
        }
        user = userService.findById(user.getId());
        Page recordPage = userService.findOvertimeRecordByUser(user);
        total = recordPage.getTotal();
        records = recordPage.getPageList(limit, currentPage);

        return this;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
