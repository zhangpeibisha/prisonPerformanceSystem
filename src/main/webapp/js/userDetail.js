$(document).ready(function(){
    var userDetailUrl ="http://localhost:8080/userDetail.do";
    $("#back").click(function(){
       $(location).attr("href","userList.html");
    });

    function init(){
        $.ajax({
            type: 'POST',
            url: userDetailUrl,
            data: {
                userId:""
            },
            success: function (data) {
                console.info(data);
                if(data.result==="0"){
                    $("#user").val(data.name);

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