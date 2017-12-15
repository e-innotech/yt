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
//点击增加按钮时
function add() {
    $(".alertbox").show();
    $(".add").show();
    $(".del").hide();
    $(".find").hide();

//
    $(".add .btn1").click(function () {
        var search = {};
        search["userName"] = $("#addinput1").val();
        search["passWord"] = $("#addinput4").val();
        $.ajax({
            type: 'post',
            contentType: "application/json",
            url: 'http://192.168.20.195:8080/user',
            dataType: "json",//数据格式
            data: JSON.stringify(search),
            success: function () {

                $(".alertbox").hide();

                    //$(".add").hide();
            }
        })
    });

    //点击取消关闭弹窗
    $(".add .btn2").click(function () {
        $(".add").hide();
        $(".alertbox").hide();
    })
}
//点击删除按钮时
function del(e) {
    $(".alertbox").show();
    $(".add").hide();
    $(".find").hide();
    $(".revise").hide();
    //$(".alertbox").load('myAlert.html');
    $(".del").show()
    //$(".del").show();
    //拿到了第一个td里面的值
    //console.log($(e.target).closest("tsr").find("td")[0]);
    var del=$(e.target).closest("tr").find("td")[0];
    deltd=$(del).html()
    console.log(444,deltd)
};

var deltd =0;
function test() {
    console.log('ssssss');
    //console.log($('.del'));
    $(".del>.btn1").click(function () {
        console.log(666, deltd)
        $.ajax({
            type: 'get',
            url: 'http://192.168.20.195:8080/user/' + deltd,
            dataType: "json",//数据格式
            //data: JSON.stringify(search),
            success: function () {
                console.log(111)
                //$(".alertbox").hide();
                //window.location.reload();
                //$(".add").hide();
            }
        })
        //$(e.target).closest("tr").remove();
        //$(".alertbox").hide();
    })
    $(".del>.btn2").click(function(){
        $(".alertbox").hide();
        $(".del").hide();
    })
};

//点击修改按钮时
function revise(){
    console.log("我是修改函数")
}

//点击查看按钮时
function find(){
    console.log("我是查找函数")
}

//正则验证
function verify(){
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
//正则手机号验证
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
//正则密码验证
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



//用户状态启停页面的逻辑
//	按钮开关
function anniu(obj){
    if ($(obj).find("span").css("left") == "2px") {
        $(obj).addClass("active");
    } else {
        $(obj).removeClass("active");
    }
}
//删除选中的行
function delanniu(obj){
    var c=$(obj).closest("tr")[0];
//    console.log(c)
    var checkbox=$(obj).closest("tr").find("input[type='checkbox']")[0];
//判断checkbox的状态是不是选中
    if($(checkbox).is(':checked')) {
//        alert(11)
        c.remove()
    }else{
        alert('删除选中的信息')
    }

}
