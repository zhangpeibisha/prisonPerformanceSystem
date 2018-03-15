$(document).ready(function(){
    var userDetailUrl ="http://localhost:8080/userDetail.do";

    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容


    $("#back").click(function(){
       $(location).attr("href","userList.html");
    });

    function init(){
        $.ajax({
            type: 'POST',
            url: userDetailUrl,
            data: {
                userId:id
            },
            success: function (data) {
                console.info(data);
                if(data.result==="0"){
                    $("#serialNumber").val(data.data.serialNumber);
                    $("#name").val(data.data.name);
                    $("#wage").val(data.data.basicWage);
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