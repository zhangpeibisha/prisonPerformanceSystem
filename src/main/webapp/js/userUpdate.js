$(document).ready(function(){
    var userDetailUrl = "http://localhost:8080/userDetail.do";
    var userUpdateUrl = "http://localhost:8080/updateUser.do";

    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容


    $("#back").click(function(){
        $(location).attr("href","userList.html");
    });

    $("#sure").click(function(){

        var user = $("#serialNumber").val();
        var name = $("#name").val();
        var wage = $("#wage").val();
        var password = $("#password").val();

        if (user == null || name == null || wage == null || user == "" || name == "" || wage == "") {
            alert("输入不能为空");
            return;
        }
        var num = /^[0-9]*$/;//警号必须是数字
        if(!num.test(user)){
            alert("请输入正确的警号！");
            return;
        }
        if(user.length!==7){
            alert("警号必须为7位！");
            return;
        }
        if(password===""||password==null){
            password="";
        }
        else password= hex_md5(password);

        $.ajax({
            type: 'POST',
            url: userUpdateUrl,
            data: {
                userId:id,
                serialNumber:user,
                name:name,
                salary:wage,
                password:password
            },
            success: function (data) {
                if(data.result==="0"){
                    alert("修改成功！");;
                    $(location).attr("href","../html/userDetail.html?id="+id);
                }
                else{
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });

    });

    function init(){
        $.ajax({
            type: 'POST',
            url: userDetailUrl,
            data: {
                userId:id
            },
            success: function (data) {
                if(data.result==="0"){
                    $("#serialNumber").val(data.data.serialNumber);
                    $("#name").val(data.data.name);
                    $("#wage").val(data.data.basicWage);
                }
                else{
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });
    }

    init();
});