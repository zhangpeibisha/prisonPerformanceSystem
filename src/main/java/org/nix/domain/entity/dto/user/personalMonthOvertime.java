package org.nix.domain.entity.dto.user;

import org.apache.log4j.Logger;
import org.nix.domain.entity.OvertimeRecord;
import org.nix.domain.entity.User;
import org.nix.domain.entity.dto.ResultDto;
import org.nix.utils.datetime.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 显示用户每月加班信息汇总
 */
public class personalMonthOvertime implements ResultDto<User> {
    //日志记录
    private static Logger logger = Logger.getLogger(personalMonthOvertime.class);

    //记录总条数
    private int total;
    //统计每个月的集合
    private List<UserOvertimeInfo> userOvertimeInfos = new ArrayList<>();
    //需要加工的原始数据
    private List<OvertimeRecord> oldData = new ArrayList<>();
    //设置每页行数
    private int limit = 10;
    //设置当前页
    private int currentPage = 1;

    @Override
    public ResultDto resultDto(User user) {


        return null;
    }

    /**
     * get方法是为了能够返回给前端
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * get方法是为了能够返回给前端
     *
     * @return
     */
    public List<UserOvertimeInfo> getUserOvertimeInfos() {
        return userOvertimeInfos;
    }

    /**
     * set 设置数据
     */

    public personalMonthOvertime setTotal(int total) {
        this.total = total;
        return this;
    }

    public personalMonthOvertime setUserOvertimeInfos(List<UserOvertimeInfo> userOvertimeInfos) {
        this.userOvertimeInfos = userOvertimeInfos;
        return this;
    }

    public personalMonthOvertime setOldData(List oldData) {
        this.oldData = oldData;
        return this;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 统计辅助类
     */
    class UserOvertimeInfo {
        //统计的年份
        private int year;
        //统计的月份
        private int month;
        //加班记录id
        private int overtimeRecordsId;
        //加班时长
        private double duration;
        //加班工资
        private double overtimeSalary;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getOvertimeRecordsId() {
            return overtimeRecordsId;
        }

        public void setOvertimeRecordsId(int overtimeRecordsId) {
            this.overtimeRecordsId = overtimeRecordsId;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getOvertimeSalary() {
            return overtimeSalary;
        }

        public void setOvertimeSalary(double overtimeSalary) {
            this.overtimeSalary = overtimeSalary;
        }
    }


    /**
     * 通过原始数据将所有信息进行相加、或者移除
     */
    private List<OvertimeRecord> statistics() {
        //临时使用存储相同年份和月份的订单
        List<OvertimeRecord> tempList = new ArrayList<>();
        //临时年月
        int year_1;
        int month_1;
        int year_2;
        int month_2;
        Date tempTime_1;
        Date tempTime_2;
        //临时加班记录
        OvertimeRecord tempOvertimeRecord_1;
        OvertimeRecord tempOvertimeRecord_2;

        for (int i = 0; i < oldData.size(); i++) {
            tempOvertimeRecord_1 = oldData.get(i);
            tempTime_1 = tempOvertimeRecord_1.getOvertimeStart();
            year_1 = DateUtil.getYear(tempTime_1);
            month_1 = DateUtil.getMonth(tempTime_1);
            for (int j = i; j <oldData.size() ; j++) {

            }
        }
        return null;
    }

    /**
     * 负责统计获取到相同年份和月份的加班记录的和
     *
     * @param list
     * @return UserOvertimeInfo 组装好的数据
     */
    private UserOvertimeInfo overtimeMonthSum(List<OvertimeRecord> list) {

        for (int i = 0; i < list.size(); i++) {

        }
        return null;
    }

    /**
     * 将组装好的数据
     *
     * @param info
     */
    private void addUserOvertimeInfo(UserOvertimeInfo info) {
        userOvertimeInfos.add(info);
    }

}
