$(document).ready(function(){
    var recordDetailUrl ="http://localhost:8080/recordDetail.do";

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


                    data.data.overtimeRecord.overtimeStart = new Date(data.data.overtimeRecord.overtimeStart).toLocaleString();
                    data.data.overtimeRecord.overtimeEnd = new Date(data.data.overtimeRecord.overtimeEnd).toLocaleString();
                    data.data.overtimeRecord.overtimeLength = MillisecondToDate(data.data.overtimeRecord.overtimeLength);

                    $("#id").val(data.data.overtimeRecord.id);
                    $("#serialNumber").val(data.data.serialNumber);
                    $("#name").val(data.data.name);
                    $("#startTime").val(data.data.overtimeRecord.overtimeStart);
                    $("#stopTime").val(data.data.overtimeRecord.overtimeEnd);
                    $("#duration").val(data.data.overtimeRecord.overtimeLength);
                    $("#overtimeSalary").val(data.data.overtimeRecord.overtimeMoney);
                    $("#createTime").val(data.data.overtimeRecord.overtimeStart);

                }
                else{
                    alert(data.message);
                }
            },
            dataType: "json"
        });

    }
    init();
});