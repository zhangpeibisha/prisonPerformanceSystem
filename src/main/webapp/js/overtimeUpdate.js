$(document).ready(function(){
    var recordDetailUrl ="http://localhost:8080/recordDetail.do";
    var recordUpdateUrl ="http://localhost:8080/updateRecord.do";

    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容


    $("#back").click(function(){
        $(location).attr("href","overtimeAdmin.html");
    });

    function init(){
        $.ajax({
            type: 'POST',
            url: recordDetailUrl,
            data: {
                recordsId:id
            },
            success: function (data) {
                console.info(data);
                if(data.result==="0"){

                    data.data.overtimeRecord.overtimeStart = new Date(data.data.overtimeRecord.overtimeStart).Format("yyyy-MM-ddThh:mm:ss");
                    data.data.overtimeRecord.overtimeEnd =  new Date(data.data.overtimeRecord.overtimeEnd).Format("yyyy-MM-ddThh:mm:ss");

                    $("#id").val(data.data.overtimeRecord.id);
                    $("#serialNumber").val(data.data.serialNumber);
                    $("#name").val(data.data.name);
                    $("#startTime").val(data.data.overtimeRecord.overtimeStart);
                    $("#stopTime").val(data.data.overtimeRecord.overtimeEnd);
                    $("#duration").val(data.data.overtimeRecord.overtimeLength+"小时");
                    $("#overtimeSalary").val(data.data.overtimeRecord.overtimeMoney+"元");
                    $("#createTime").val(data.data.overtimeRecord.overtimeStart);

                }
                else {
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });

    }

    $("#sure").click(function(){
        var startTime =  Date.parse($("#startTime").val());
        var stopTime =  Date.parse($("#stopTime").val());



        if (startTime == null || stopTime == null || startTime == "" || stopTime == "" ) {
            alert("输入不能为空");
            return;
        }

        $.ajax({
            type: 'POST',
            url: recordUpdateUrl,
            data: {
                recordId:id,
                startTime:startTime,
                stopTime:stopTime
            },
            success: function (data) {
                console.info(data);
                if(data.result==="0"){
                    alert("修改成功！");
                    $(location).attr("href","overtimeAdmin.html");
                }
                else {
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });

    });


    init();
});