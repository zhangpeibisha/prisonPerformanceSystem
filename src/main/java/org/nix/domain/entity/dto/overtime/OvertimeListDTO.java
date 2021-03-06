package org.nix.domain.entity.dto.overtime;

import org.apache.log4j.Logger;
import org.nix.dao.service.OvertimeRecordService;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.domain.entity.entitybuild.UserBuild;
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
    //是否排序
    private boolean isDesc = false;
    //需要查询的
    private String select = "";


    @Override
    public ResultDto resultDto(Object... objects) {

        records =  overtimeRecordService.overtimeRecordList(limit,currentPage,select,isDesc);

        total = overtimeRecordService.overtimeRecordListCount(select);

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

    public OvertimeListDTO setSelect(String select) {
        this.select = select;
        return this;
    }

    public void setParmaterNull(){
        for (int i = 0; i <records.size() ; i++) {

            User user = records.get(i).getUser();

            User nowUser = new UserBuild()
                    .setName(user.getName())
                    .setSerialNumber(user.getSerialNumber())
                    .build();

            records.get(i).setUser(nowUser);
            records.get(i).setRules(null);
        }
    }




}
