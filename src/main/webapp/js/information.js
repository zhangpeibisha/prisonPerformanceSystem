$(document).ready(function () {
    var informationUrl = "http://localhost:8080/information.do";

    function init() {

        $.ajax({
            type: 'POST',
            url: informationUrl,
            data: {
            },
            success: function (data) {
                console.log(data);
                if(data.result=="0"){
                    info(data.data);
                }
                else {
                    alert(err(data.result));
                }

            },
            dataType: "json"
        });
    }

    function info(data) {
        var temp = [];
        temp.push('<div class="col-md-12"><p class="col-md-6">警号:</p><span>'+ data.serialNumber +'</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-6">姓名:</p><span>'+ data.name +'</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-6">基础工资:</p><span>'+ data.basicWage +'元</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-6">历史加班总时长:</p><span>'+ data.overtimeAllLenth + '小时</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-6">历史加班总工资:</p><span>'+ data.overtimeAllmoney +'元</span></div>');
        $('#info').html(temp.join(''));
    }
    
    init();
});


