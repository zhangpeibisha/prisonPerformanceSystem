package org.nix.domain.entity.dto.overtime;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.exception.SelectException;
import org.nix.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/3/17.
 * <p>
 * serialNumber: 警号,
 * name:姓名,
 * startTime：加班开始时间,
 * stopTime:加班结束时间,
 * duration:加班时长,
 * overtimeSalary: 加班工资
 * createTime:创建日期
 */
@Service
public class RecordDetailDTO implements ResultDto{
    //日志记录
    private static Logger logger = Logger.getLogger(RecordDetailDTO.class);

    @Autowired
    private OvertimeRecordService overtimeRecordService;

    private String serialNumber;
    private String name;
    private OvertimeRecord overtimeRecord;

    @Override
    public ResultDto resultDto(Object... objects) {

        if (!(objects[0] instanceof Integer)){
            throw new IllegalArgumentException();
        }

        Integer recordsId = (Integer) objects[0];

        overtimeRecord = overtimeRecordService.findById(recordsId);

        if (SystemUtil.parameterNull(overtimeRecord)){
            throw new SelectException();
        }

        User user = overtimeRecord.getUser();
        serialNumber = user.getSerialNumber();
        name = user.getName();

        setParmaterNull();

        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public OvertimeRecord getOvertimeRecord() {
        return overtimeRecord;
    }

    public void setParmaterNull(){
       overtimeRecord.setUser(null);
       overtimeRecord.setRules(null);
    }
}
