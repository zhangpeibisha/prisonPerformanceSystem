$(document).ready(function () {
    var registerUrl = "http://localhost:8080/register.do";

    $("#login").click(function () {
        $(location).attr('href', 'login.html');
    });

    $("#register").click(function () {
        var user = $("#user").val();
        var name = $("#name").val();
        var pass = $("#password").val();

        var num = /^[0-9]*$/;//警号必须是数字
        var sum = /^\d{m,n}$/;//必须是7位

        if(!num.test(user)){
            alert("请输入正确的警号！");
            return;
        }
        if(!sum.test(user)){
            alert("警号必须为7位！");
            return;
        }
        pass= hex_md5(pass);
        console.info(pass);
        $.ajax({
            type: 'POST',
            url: registerUrl,
            data: {
                serialNumber:user,
                userName:name,
                password:pass
            },
            success: function (data) {
                console.info(data);
                if(data.result=="0"){
                    alert("注册成功");
                    $(location).attr('href', 'login.html');
                }
                else {
                    alert(data.message);
                }
            },
            dataType: "json"
        });
    });
});