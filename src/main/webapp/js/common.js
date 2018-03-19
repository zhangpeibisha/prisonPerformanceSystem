
function MillisecondToDate(msd) {
    var time = parseFloat(msd) / 1000;
    if (null != time && "" != time) {
        if (time > 60 && time < 60 * 60) {
            time = parseInt(time / 60.0) + "分钟" + parseInt((parseFloat(time / 60.0) -
                parseInt(time / 60.0)) * 60) + "秒";
        } else if (time >= 60 * 60 && time < 60 * 60 * 24) {
            time = parseInt(time / 3600.0) + "小时" + parseInt((parseFloat(time / 3600.0) -
                parseInt(time / 3600.0)) * 60) + "分钟" +
                parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
                    parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        } else {
            time = parseInt(time) + "秒";
        }
    } else {
        time = "0 时 0 分0 秒";
    }
    return time;
}

function err(data) {

    switch(data){
        case "ERROR003":return "没有权限,拒绝访问";
        case "ERROR004":return "账号或者密码错误";
        case "ERROR005":return "空指针异常";
        case "ERROR006":return "Insert或Update数据时违反了完整性";
        case "ERROR007":return "插入字段为空";
        case "ERROR008":return "身份过期请重新登录";
        case "ERROR009":return "数据库发生错误，无法归类";
        case "ERROR011":return "数据无法检测";
        case "ERROR012":return "参数传递异常";
        case "ERROR013":return "方法参数类型不匹配";
        case "ERROR014":return "参数缺失";
        case "ERROR015":return "数据库查询失败";
        case "ERROR016":return "参数规格不符合要求";
    }
}

Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};