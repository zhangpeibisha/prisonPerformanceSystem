$(document).ready(function () {
    var registerUrl = "http://localhost:8080/register.do";

    $("#login").click(function () {
        $(location).attr('href', 'login.html');
    });

    $("#register").click(function () {
        var name = $("#user").val();
        var pass = $("#password").val();
        pass= hex_md5(pass);
        $.ajax({
            type: 'POST',
            url: registerUrl,
            data: {
                serialNumber:name,
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