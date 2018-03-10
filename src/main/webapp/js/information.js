$(document).ready(function () {
    var informationUrl = "http://localhost:8080/information.do";

    function init() {
        $.ajax({
            type: 'POST',
            url: informationUrl,
            data: {
            },
            success: function (data) {
                if(data.result=='')
                console.log(data.result);
                info(data.data);
            },
            dataType: "json"
        });
    }

    function info(data) {
        var temp = [];
        temp.push('<div class="col-md-12"><p class="col-md-3">警号:</p><span>'+ data.serialNumber +'</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-3">姓名:</p><span>'+ data.name +'</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-3">工资:</p><span>'+ data.salary +'元</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-3">历史加班总时长:</p><span>'+ data.duration + '</span></div>');
        temp.push('<div class="col-md-12"><p class="col-md-3">历史加班总加班工资:</p><span>'+ data.overtimeSalary +'</span></div>');
        $('#info').html(temp.join(''));
    }
    init();
});


