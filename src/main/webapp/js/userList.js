$(document).ready(function () {
    var userListUrl = "http://localhost:8080/userList.do";
    var pageLimit = 10;
    var currentPage = 1;

    function init() {
        $.ajax({
            type: 'POST',
            url: userListUrl,
            data: {
                limit: pageLimit,
                currentPage: currentPage
            },
            success: function (data) {
                console.log(data);
                if(data.result==="0"&&data.total!==0){
                    var listData = data.data;
                    var total = data.total;
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
                                url: userListUrl,
                                type: 'POST',
                                data: {
                                    limit: pageLimit,
                                    currentPage: page
                                },
                                dataType: 'json',
                                success: function (data) {
                                    console.info(data);
                                    if(data.data==0){
                                        var listData = data.data;
                                        showData(listData);
                                    }
                                    else if(data.data==1){
                                        noData();
                                    }
                                    else if(data.data==2)
                                        alert("error！");
                                }
                            });
                        }
                    });
                }
                else if(data.data==0&&data.total==0){
                    noData();
                }
                else{
                    alert(data.message);
                }
            },
            dataType: "json"
        });
    }

    function showData(listData) {
        var temp = [], showNum = listData.length;

        temp.push('<table class="table table-hover">');
        temp.push('<thead><tr><th>用户编号</th><th>警号</th><th>姓名</th>' +
            '<th>工资</th><th>密码</th><th>操作</th></tr><tbody>');
        for (var i = 0; i < showNum; i++) {

            var detailHref = "../html/userDetail.html?id=" +listData[i].serialNumber;
            var updateHref = "../html/userUpdate.html?id=" +listData[i].serialNumber;

            temp.push("<tr><td>" + listData[i].month + "</td><td>" + listData[i].serialNumber + "</td><td>"
                + listData[i].name+ "</td><td>" + listData[i].password+ "</td><td>"
                + listData[i].salary+
                "</td><td><a class='point' href="+ detailHref +">详情</a><a class='point' href="+ updateHref +">修改</a></td>");
        }
        temp.push('</tbody></table>');

        $('#list').html(temp.join(''));
    }

    function noData() {
        var temp = [];
        temp.push('<table class="table table-hover">');
        temp.push('<thead><tr><th>用户编号</th><th>警号</th><th>姓名</th>' +
            '<th>工资</th><th>密码</th><th>操作</th></tr><tbody>');
        temp.push("<tr><td colspan='6' style='text-align: center'>暂无数据</td></tr>");
        temp.push('</tbody></table>');

        $('#list').html(temp.join(''));
    }

    init();
});