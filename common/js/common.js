/*
 通用的ajax请求函数
 @param type get、post
 @param url
 @param isAsync true false
 @param data  object
 @param callback function
 */

function doAjax(type, url, isAsync, data, callback) {
    //创建通信对象
    var xhr = createXhr();

    //判断通信类型
    if (type.toLowerCase() === "get") {
        //遍历拼接数据到url
        url += "?";
        for (var n in data) {
            url += n + "=" + data[n] + "&";
        }
        //截掉最后一位
        url = url.substr(0, url.length - 1)
        //释放data
        data = null;
    } else {
        data = JSON.stringify(data);
    }

    //初始化通信对象
    xhr.open(type, url, isAsync);
    //发送请求
    xhr.send(data);

    //监听readyState变化
    xhr.onreadystatechange = function () {
        if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
            if (xhr.readyState === 4) {
                var data = JSON.parse(xhr.responseText);
                //传递给回调函数
                callback && callback(data);
            }
        }
    };
}

//创建通信对象
function createXhr() {
    //大部分浏览器中使用XMLHttpRequest创建通信对象
    if (typeof XMLHttpRequest !== "undefined") {
        return new XMLHttpRequest();
    } else {
        //IE6及ie6以下浏览器不支持XMLHttpRequest 这些浏览器使用ActiveXObject创建通信对象，
        //在创建对象是需要根据浏览器具体版本传入不同的参数 如下： "MSXML.XMLHttp.6.0","MSXML.XMLHttp.3.0","MSXML.XMLHttp", "Microsoft.XMLHTTP"
        var args = ["MSXML.XMLHttp.6.0", "MSXML.XMLHttp.3.0", "MSXML.XMLHttp", "Microsoft.XMLHTTP"];
        for (var n = 0; n < args.length; n++) {
            //异常处理
            try {
                var xhr = new ActiveXObject(args[n]);
                return xhr;
            } catch (e) {
                console.log(e);
            }
        }
    }
}

//增删改查页面的逻辑
//栏目的增
function add() {
//点击增加按钮时出现弹出框

    $(".add").show();
    //点击确认按钮时
    $(".modal-footer .btn1").click(function () {
        var newuserName = $("userName").val();

        for (var i = 0; i < json_data.length; i++) {
            if (newuserName == json_data[i].userName) {
                alert('栏目存在');
                return;
            }
        }
        json_data.push({userName: newuserName});
        console.log(json_data);
        renderList();
        $(".userName").val("");
        $(".passWord").val("");
//点击关闭按钮时
        $(".add .btn2").click(function () {
            $(".add").hide();
        })
    })




}
//修改
function revise() {
    $(".revise").show();
    //获取当前tr下的td eq（）的值给弹出的input框
    //$(".id").val($(event.target).parents("tr").children("td").eq(0).html());
    var oldsiteName=$(event.target).parents("tr").children("td").eq(1);
    var oldroute=$(event.target).parents("tr").children("td").eq(2);
    var oldtemplteRoute=$(event.target).parents("tr").children("td").eq(5);
    $(".siteName").val(oldsiteName.html());
    $(".route").val(oldroute.html());
    $(".templteRoute").val(oldtemplteRoute.html());


    $(".revise .btn1").click(function () {
        var a=$(".siteName").val();
        console.log(a)
        /*var revise={};
         revise["siteName"]=$(".siteName").val();
         revise["route"]=$(".route").val();
         revise["templteRoute"]=$("templteRoute").val();*/
        oldsiteName.html($(".siteName").val());
        oldroute.html($(".route").val());
        oldtemplteRoute.html($(".templteRoute").val());


        /*$.ajax({
            type: 'post',
            //url: 'http://192.168.20.195:8080/websites/update',
            url:'websites.json',
            async: true,
            contentType: "application/json",
            dataType: "json",//数据格式
            *//*
             data: JSON.stringify(search),
             *//*
            success: function (data) {}
        })*/
    });
    //点击取消关闭弹窗
    $(".revise .btn2").click(function () {
        $(".revise").hide();
    })
}

//点击查询按钮时
function find() {
    var search = {};
    search["userName"] = $(".text_find").val();

    $.ajax({
        type: "get",
        //url: "http://192.168.20.195:8080/user/queryAll/",
        url:"data.json",
        async: true,
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(search),
        success: function (data) {

        }
    })
}

//点击编辑按钮时
function edit() {
    $(".edit").show();
    /*//获取当前tr下的td eq（）的值给弹出的input框
     $(".siteName").val($(event.target).parents("tr").children("td").eq(1).html());
     $(".route").val($(event.target).parents("tr").children("td").eq(2).html());
     $(".templteRoute").val($(event.target).parents("tr").children("td").eq(5).html());
     $(".edit .btn1").click(function () {
     var search = {};
     search["siteName"] = $(".siteName").val();
     search["route"] = $(".route").val();
     search["templteRoute"] = $(".templteRoute").val();//网站管理的修改值
     //search["uesrName"] = $(".uesrName").val();
     //search["passWord"] = $(".passWord").val();//用户管理的值
     $.ajax({
     type: 'get',
     url: 'http://192.168.20.195:8080/websites/update',
     //url:'data.json',
     async: true,
     contentType: "application/json",
     dataType: "json",//数据格式
     data: JSON.stringify(search),
     success: function (data) {
     //alert(msg);
     window.location.reload();
     }
     })
     });*/
    //点击取消关闭弹窗
    $(".edit .btn2").click(function () {
        $(".edit").hide();
    })
}

//点击删除按钮时
function del() {

    $(".del").show();

    //拿到了第一个td里面的值
    //console.log($(e.target).closest("tsr").find("td")[0].html());
    //var del = $(event.target).closest("tr");

    //console.log(444, deltd)
    //var deltd = 0;
    //var deltd2 = 0;
    var search = {};
    search["userName"] = $(".btn_del").val();//用户管理的删除数值
    $(".del .btn1").bind(function () {
        //console.log(666, deltd)
        //console.log(777, deltd2)
        $.ajax({
            type: 'delete',
            //url: 'http://192.168.20.195:8080/user/' + deltd + '/' + deltd2,//传输地址
            url:"data.json",
            async: true,
            contentType: "application/json",
            dataType: "json",//数据格式
            //data: JSON.stringify(search),
            success: function (data) {
                $(event.target).closest("tr").html();
                window.location.reload();


            }
        })
    })
    //点击取消关闭弹窗
    $(".del .btn2").click(function () {
        $(".del").hide();
    })

};

//点击审核
function audit(){
    $(".audit").show();
    var search = {};
    search["id"] = $(".id").val();
    search["status"] = $(".status").val();
    search["aduit_des"] = $(".aduit_des").val();
    search["aduit_user_id"] = $(".aduit_user_id").val();

    $.ajax({
        type: 'get',
        url:"news.json",
        async: true,
        contentType: "application/json",
        dataType: "json",//数据格式
        data: JSON.stringify(search),
        success: function (data) {


        }
    })
    //点击取消关闭弹窗
    $(".audit .btn2").click(function () {
        $(".audit").hide();
    })
}

//正则验证
function verify() {
    //昵称
    var h = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;

    $("[name='name']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='name']").next().html("姓名不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(h)) {
            $("[name='name']").next().html("姓名不合法！");
            $("[name='name']").next().css("color", "#f00");
        } else {
            $(this).next().css("color", "#0EA74A");
            $("[name='name']").next().html("正确");
        }
    });
}
//正则手机号验证（注册）
function userName() {
    var d = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    $("[name='userName']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='userName']").next().html("手机号不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(d)) {
            $("[name='userName']").next().html("手机号不正确！");
            $("[name='userName']").next().css("color", "#f00");
        } else {
            $(this).next().css("color", "#0EA74A");
            $("[name='userName']").next().html("正确");
        }
    });
}
//正则密码验证（注册的）
function password() {
    var p = /^[a-zA-Z]\w{5,17}$/;
    $("[name='password']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='password']").next().html("密码不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(p)) {
            $("[name='password']").next().html("密码格式不正确！");
            $("[name='password']").next().css("color", "#f00");
        } else {
            $(this).next().css("color", "#0EA74A");
            $("[name='password']").next().html("正确");
        }
    });
}

//登录页面的验证
function userNames() {
    var d = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    $("[name='userName']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='userName']").next().html("手机号不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(d)) {
            $("[name='userName']").next().html("手机号格式不正确！");
            $("[name='userName']").next().css("color", "#f00");
        }// else {
        //    $(this).next().css("color", "#0EA74A");
        //    $("[name='userName']").next().html("正确");
        //}
    });
}



//登录页面（密码）的验证
function passwords() {
    var p = /^[a-zA-Z]\w{5,17}$/;
    $("[name='password']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='password']").next().html("密码不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(p)) {
            $("[name='password']").next().html("密码格式不正确！");
            $("[name='password']").next().css("color", "#f00");
        } //else {
        //    $(this).next().css("color", "#0EA74A");
        //    $("[name='password']").next().html("正确");
        //}
    });
}


//正则邮箱验证
function email() {
    var y = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    $("[name='email']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='email']").next().html("邮箱不能为空！")
            $(this).next().css("color", "#f00");
        } else if (!v.match(y)) {
            $("[name='email']").next().html("请填写正确的邮箱！");
            $("[name='email']").next().css("color", "#f00");
        } else {
            $(this).next().css("color", "#0EA74A");
            $("[name='email']").next().html("正确");
        }
    });
}
//正则身份证验证
function idCard() {
    var idCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    $("[name='idCard']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $("[name='idCard']").next().html("身份证号不能为空！");
            $(this).next().css("color", "#f00");
        } else if (!v.match(idCard)) {
            $("[name='idCard']").next().html("身份证号不正确！");
            $("[name='idCard']").next().css("color", "#f00");
        } else {
            $(this).next().css("color", "#0EA74A");
            $("[name='idCard']").next().html("正确");
        }
    });
}

//正则验证栏目名称
function columnname(){
    var columne=/^[\u4e00-\u9fa5]+$/;
    $()



}

//用户状态启停页面的逻辑
//	按钮开关
function anniu(obj) {
    //得到当前点的元素的id
    var qitinghtml=$(obj).parent().prev().prev().prev().prev().html();
    var objindex=$(obj).closest("tr").index()-1;
    //console.log(objindex)
    //console.log(111,$(obj).closest("tr").index())
    console.log(1,qitinghtml)
    //当前点的元素的状态 0或者1
    var statushtml=$(obj).parent().prev().html();
    console.log(2,statushtml)
    var dataqiting={};
    dataqiting["id"]=qitinghtml;
    dataqiting["status"]=statushtml;
    console.log(666,dataqiting)
//得到当前元素的
    $.ajax({
        type:"get",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        //url:"column.json?id=" +"&status="+escape(statushtml)+"",
        url:"column.json",
        data:{"id":qitinghtml,"status": statushtml}, //上送数据
        success:function(data){  //返回成功的json数据
            //console.log(111,data[objindex].isUse);
            //console.log(222,data);
            var flag=data[objindex].isUse;
            console.log(data[objindex].isUse)
            if(flag){
                data[objindex].isUse=0;
                //  console.log(111,data[objindex].isUse);
                //return flag=true;
            }else{
                data[objindex].isUse=1;
                // console.log(22,data[objindex].isUse)
                //return flag=false;
            }


        }
    })
    //console.log(data)

    //console.log(statushtml)
    //console.log($(obj).parent().prev().html())

    if ($(obj).find("span").css("left") == "2px") {
        $(obj).addClass("active");

    } else {
        $(obj).removeClass("active");
    }
}
//删除选中的行
function delanniu(obj) {
    var c = $(obj).closest("tr")[0];
//    console.log(c)
    var checkbox = $(obj).closest("tr").find("input[type='checkbox']")[0];
//判断checkbox的状态是不是选中
    if ($(checkbox).is(':checked')) {
//        alert(11)
        c.remove()
    } else {
        alert('删除选中的信息')
    }

}