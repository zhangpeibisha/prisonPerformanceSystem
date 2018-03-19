$(document).ready(function(){
    var overtimeAddUrl = "http://localhost:8080/addOvertime.do";

    $("#sure").click(function(){
        var serialNumber = $("#serialNumber").val();
        var startTime =  Date.parse($("#startTime").val());
        var stopTime =  Date.parse($("#stopTime").val());


        if (serialNumber == null || startTime == null || stopTime == null || serialNumber == "" || startTime == "" || stopTime == "" ) {
            alert("输入不能为空");
            return;
        }
        if(serialNumber.length!==7){
            alert("警号必须为7位！");
            return;
        }

        $.ajax({
            type: 'POST',
            url: overtimeAddUrl,
            data: {
                serialNumber:serialNumber,
                startTime:startTime,
                stopTime:stopTime
            },
            success: function (data) {
                console.info(data);
                if(data.result==="0"){
                    alert("添加成功！");
                    $(location).attr("href","overtimeAdmin.html");
                }
                else {
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });

    });

    $("#back").click(function(){
        $(location).attr("href","overtimeAdmin.html");
    });

});