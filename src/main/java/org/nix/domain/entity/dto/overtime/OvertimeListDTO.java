package org.nix.domain.entity.dto.overtime;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/16.
 *
 *
 * 返回给前端所有用户的所有加班信息
 */
@Service
public class OvertimeListDTO implements ResultDto{
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeListDTO.class);

    @Autowired
    private OvertimeRecordService overtimeRecordService;

    //将要返回的信息
    private List<OvertimeRecord> records = new ArrayList<>();
    //总条数
    private long total;

    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;

    private boolean isDesc = false;

    @Override
    public ResultDto resultDto(Object... objects) {

        records =  overtimeRecordService.overtimeRecordList(limit,currentPage,isDesc);

        total = overtimeRecordService.findAllCount();

        setParmaterNull();

        return this;
    }

    public List<OvertimeRecord> getRecords() {
        return records;
    }

    public long getTotal() {
        return total;
    }

    public OvertimeListDTO setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public OvertimeListDTO setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public OvertimeListDTO setDesc(boolean desc) {
        isDesc = desc;
        return this;
    }

    public void setParmaterNull(){
        for (int i = 0; i <records.size() ; i++) {
            records.get(i).setUser(null);
            records.get(i).setRules(null);
        }
    }



}
