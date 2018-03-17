#API文档

1. 登录模块
* 说明：用户及管理员的登录，根据账号分用户及管理员
* HTTP方法: POST
* URL: login.do
* 请求参数{
    userName:账号,
    password:密码
    }
* 返回参数{
    result:返回列表,
    role:0用户，1管理员
    }
    
2. 注册模块
* 说明：用户进行注册
* HTTP方法: POST
* URL: register.do
* 请求参数{
    serialNumber：警号,
    password:密码,
    userName:用户姓名
    }
* 返回参数{
    result：返回列表
    }
    
3. 用户个人信息模块
* 说明：显示用户的个人信息
* HTTP方法: POST
* URL：information.do
* 请求参数{}
* 返回参数{
    result:返回列表,
    data:{
    serialNumber: 警号,
    name:姓名,
    salary: 工资，
    duration: 历史加班总时长,
    overtimeSalary: 历史加班总加班工资
    }
}

4. 用户个人加班信息模块
* 说明:显示用户的个人加班信息
* HTTP方法: POST
* URL:personalOvertime.do
* 请求参数{
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    total:总条数,
    data:{
    overtimeRecordsId: 加班记录id,
    duration:加班时长,
    startTime：加班开始时间,
    stopTime:加班结束时间,
    overtimeSalary: 加班工资，
    }
    
}

5. 用户月度个人加班信息模块
* 说明： 显示用户每月加班信息汇总
* HTTP方法: POST
* URL: personalMonthOvertime.do
* 请求参数{
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    total:总条数,
    data:{
    year:年份，
    month:月份,
    overtimeRecordsId: 加班记录id,
    duration:加班时长,
    overtimeSalary: 加班工资，
    }
}

6. 用户管理模块
* 说明: 显示所有的用户信息包括用户密码
* HTTP方法: POST
* URL: userList.do
* 请求参数{
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    total:总条数,
    data:{
    userId:id
    serialNumber: 警号,
    name:姓名,
    password:密码,
    salary:工资
    }
}

7. 查看用户详情模块
* 说明: 获得一个用户的详细信息
* HTTP方法: POST
* URL: userDetail.do
* 请求参数{
    userId:用户id 
}
* 返回参数{
    result:返回列表,
    data:{
      name:姓名,
      serialNumber: 警号,
      password:密码,
      salary:工资 （注：id后台自动生成）
    }
}

7. 增加用户模块
* 说明: 新增一个用户
* HTTP方法: POST
* URL: addUser.do
* 请求参数{
    name:姓名,
    serialNumber: 警号,
    password:密码,
    salary:工资 （注：id后台自动生成）
}
* 返回参数{
    result:返回列表
}

8. 修改用户模块
* 说明: 修改用户信息
* HTTP方法: POST
* URL: updateUser.do
* 请求参数{
    userId:用户id,
    name:姓名,
    serialNumber: 警号,
    password:密码,
    salary:工资
}
* 返回参数{
    result:返回列表
}

9. 删除用户模块
* 说明: 删除用户信息
* HTTP方法: POST
* URL: deleteUser.do
* 请求参数{
    userId:用户id
}
* 返回参数{
    result:返回列表
}

10. 加班信息录入模块
* 说明: 管理员录入用户加班信息
* HTTP方法: POST
* URL: addOvertime.do
* 请求参数{
    serialNumber: 警号,
    startTime：加班开始时间,
    stopTime:加班结束时间
}
* 返回参数{
    result:返回列表
}

11. 加班工资标准模块
* 说明:显示加班工资的计算标准
* HTTP方法: POST
* URL: wageScale.do
* 请求参数{
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    data:{
        ruleId:规则id,
        ruleName:规则名,
        multiple:倍数,
        remark:备注
        createTime:创建日期
    }
}

12. 加班信息模块
* 说明:显示所有用户每天的加班信息
* HTTP方法: POST
* URL:recordList.do
* 请求参数{
    find:参数
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    data:{
        recordsId: 加班记录id,
        serialNumber: 警号,
        name:姓名,
        startTime：加班开始时间,
        stopTime:加班结束时间,
        duration:加班时长,
        overtimeSalary: 加班工资
        createTime:创建日期
    }
}

13. 加班信息详情模块
* 说明:显示一条加班信息的详情
* HTTP方法: POST
* URL:recordDetail.do
* 请求参数{
    recordsId: 加班记录id
}
* 返回参数{
    result:返回列表,
    data:{
        serialNumber: 警号,
        name:姓名,
        startTime：加班开始时间,
        stopTime:加班结束时间,
        duration:加班时长,
        overtimeSalary: 加班工资
        createTime:创建日期
    }
}

14. 加班信息添加模块
* 说明:显示一条加班信息的详情
* HTTP方法: POST
* URL:addRecord.do
* 请求参数{
    serialNumber: 警号,
    startTime：加班开始时间,
    stopTime:加班结束时间
}
* 返回参数{
    result:返回列表
}

15. 加班信息修改模块
* 说明:修改一条加班信息的详情
* HTTP方法: POST
* URL:updateRecord.do
* 请求参数{
    serialNumber: 警号,
    startTime：加班开始时间,
    stopTime:加班结束时间
}
* 返回参数{
    result:返回列表
}

16. 加班信息删除模块
* 说明:删除一条加班信息的详情
* HTTP方法: POST
* URL:deleteRecord.do
* 请求参数{
    recordsId: 加班记录id
}
* 返回参数{
    result:返回列表
}

17. 月度加班信息统计模块
* 说明：显示所有用户每月加班信息汇总
* HTTP方法: POST
* URL: overtimeMonthList.do
* 请求参数{
    limit:条数,
    currentPage:当前页
}
* 返回参数{
    result:返回列表,
    data:{
        year：年份,
        month:月份,
        overtimeRecordsId: 加班记录id,
        serialNumber: 警号,
        name:姓名,
        duration:加班时长,
        overtimeSalary: 加班工资
    }
}

18. 返回列表
   0：成功
   
   
19.新增添加班工资标准
* 添加工资标准
* HTTP ： POST
* URL ：addOvertimeRules.do
* 请求参数{
   name : 计费标准名字
   description：使用时间段 weekend : 代表周末工资倍数 ; usually : 平时上班工资计算
   payMultiples:计费倍数
   note ：备注
}
* 返回参数{
    result:返回列表
}