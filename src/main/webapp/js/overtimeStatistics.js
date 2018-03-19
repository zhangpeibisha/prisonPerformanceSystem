$(document).ready(function () {
    var overtimeUserMonthUrl = "http://localhost:8080/personalMonthOvertime.do";
    var pageLimit = 10;
    var currentPage = 1;

    function init() {
        $.ajax({
            type: 'POST',
            url: overtimeUserMonthUrl,
            data: {
                limit: pageLimit,
                currentPage: currentPage
            },
            success: function (data) {
                console.log(data);
                if(data.result==="0"&&data.data.total!==0){
                    var listData = data.data.personalMonthOvertimes;
                    var total = data.data.total;
                    showData(listData);

                    var num = (total+pageLimit -1)/pageLimit;//向上取整
                    $('#page').bootstrapPaginator({
                        bootstrapMajorVersion: 3,
                            currentPage: 1,//当前页码
                                totalPages: num,
                                numberOfPages: 5,
                                shouldShowPage:true,//是否显示该按钮
                                itemTexts: function (type, page, current) {
                                switch (type) {
                                    case "first": return "首页";
                                    case "prev": return "上一页";
                                    case "next": return "下一页";
                                    case "last": return "末页";
                                    case "page": return page;
                                }
                        },
                        onPageClicked: function (event, originalEvent, type, page) {
                            $.ajax({
                                url: overtimeUserMonthUrl,
                                type: 'POST',
                                data: {
                                    limit: pageLimit,
                                    currentPage: page
                                },
                                dataType: 'json',
                                success: function (data) {
                                    console.info(data);
                                    if(data.result==="0"&&data.data.total!==0){
                                        var listData = data.data.personalMonthOvertimes;
                                        showData(listData);
                                    }
                                    else if(data.data==="0"&&data.data.total===0){
                                        noData();
                                    }
                                    else {
                                        alert(err(data.result));
                                    }
                                }
                            });
                        }
                    });
                }
                else if(data.result==="0"&&data.data.total===0){
                    noData();
                }
                else {
                    alert(err(data.result));
                }
            },
            dataType: "json"
        });
    }

    function showData(listData) {
        var temp = [], showNum = listData.length;

        temp.push('<table class="table table-hover">');
        temp.push('<thead><tr><th>年</th></th><th>月</th><th>记录编号</th><th>加班总时长</th>' +
            '<th>加班总工资</th></tr><tbody>');
        for (var i = 0; i < showNum; i++) {

            temp.push("<tr><td>" + listData[i].year + "</td><td>" + listData[i].month + "</td><td>" + listData[i].id + "</td><td>"
                + listData[i].duration+ "小时</td><td>" + listData[i].overtimeSalary + "元</td>");
        }
        temp.push('</tbody></table>');

        $('#list').html(temp.join(''));
    }

    function noData() {
        var temp = [];
        temp.push('<table class="table table-hover">');
        temp.push('<thead><tr><th>年</th><th>月</th><th>记录编号</th><th>加班总时长</th>' +
            '<th>加班总工资</th></tr><tbody>');
        temp.push("<tr><td colspan='5' style='text-align: center'>暂无数据</td></tr>");
        temp.push('</tbody></table>');

        $('#list').html(temp.join(''));
    }

    init();
});