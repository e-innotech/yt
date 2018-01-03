$(document).ready(function () {
    setTimeout(function () {}, 500);
    //页面的渲染
    var pageSize=5;
    var pageNum=1;
    var Data;
    $.ajax({
        type: "get",//请求方式
        url: "http://192.168.20.195:8080/user/query",//请求路径
        async: false,
        dataType: "json", //数据格式
        data:{pageSize:pageSize,pageNum:pageNum},
        success: function (data) {
            Data=data;
            ergodic(data.list);
        }
    });
    function ergodic(Data) {
        console.log(Data)

        $("tbody").empty();
        for (var i = 0; i < Data.length; i++) {
            str = "<tr>" +
            "<td>" + Data[i].id + "</td>" +
            "<td>" + Data[i].userName + "</td>" +
            "<td>" + Data[i].passWord + "</td>" +
            '<td><p class="' + (Data[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +  //+ resultdata[i].isUse +
            "<td>" + Data[i].createDate + "</td>" +
            "<td>" + Data[i].user_group_id + "</td>" +
            "<td>" +
            "<input type='button' value='删除' onclick='delanniu(this)'/>" +
            "<input type='button' value='修改' onclick='reviselist(this)'/>" +
            "</td></tr>";
            $("tbody").append(str);
        }
    }
    //新增
    $(".add_btn").click(function () {
        $(".add").show();
    })
    $(".add .btn1").click(function () {
        var userName = $(".userName").val();
        var passWord = $(".passWord").val();
        for (var i = 0; i < Data.length; i++) {
            if (userName == Data[i].userName || passWord == Data[i].passWord) {
                alert('用户已存在');
                return;
            }
        }
        Data.push({userName: userName, passWord: passWord});
        ergodic(Data);//Data是添加后的数据
        $(".add").hide();
    })
    $(".add .btn2").click(function () {
        $(".add").hide();
    })












});
