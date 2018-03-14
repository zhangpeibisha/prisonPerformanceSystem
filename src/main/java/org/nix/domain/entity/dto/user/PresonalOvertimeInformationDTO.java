package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.dao.service.UserService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.exception.AuthorizationException;
import org.nix.exception.IdentityOverdueException;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 分页返回用户的加班信息
 */
@Service
public class PresonalOvertimeInformationDTO implements ResultDto {
    //日志记录
    private static Logger logger = Logger.getLogger(PresonalOvertimeInformationDTO.class);

    @Autowired
    private OvertimeRecordService overtimeRecordService;

    @Autowired
    private UserService userService;

    //加班信息
    private List<OvertimeRecord> records = new ArrayList<>();
    //加班总条数
    private long total;

    private int limit = 10;

    private int currentPage = 1;


    @Override
    public ResultDto resultDto(Object... objects) throws AuthorizationException {
        if (SystemUtil.parameterNull(objects)) {
            throw new IdentityOverdueException();
        }
        User user;
        //因为是查一个人的，所以只取第一个值
        user = (User) objects[0];
        user = userService.findById(user.getId());

        total = overtimeRecordService.findOvertimeRecordCountByUser(user);
        records = overtimeRecordService.findOvertimeRecordByUser(user,limit,currentPage);
        setParmaterNull();
        return this;
    }

    public List getRecords() {
        return records;
    }

    public PresonalOvertimeInformationDTO setRecords(List records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PresonalOvertimeInformationDTO setTotal(long total) {
        this.total = total;
        return this;
    }

    public PresonalOvertimeInformationDTO setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public PresonalOvertimeInformationDTO setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    /**
     * 设置不用的参数为空
     */
    public void setParmaterNull(){
        for (int i = 0; i <records.size() ; i++) {
            records.get(i).setRules(null);
            records.get(i).setUser(null);
        }
    }

}
