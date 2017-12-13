//正则验证
//昵称
var h = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
//手机号
var d = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
//邮箱
var y = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
//密码
var p = /^[a-zA-Z]\w{5,17}$/;
//身份证
var idCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;

$("input").focus(function () {
    $(this).prev().css("color", "#008DE8");
});

$("[name='name']").blur(function () {
    var v = $(this).val();
    if (v == '') {
        $("[name='name']").next().html("姓名不能为空！");
        $(this).prev().css("color", "#f00");
    } else if (!v.match(h)) {
        $("[name='name']").next().html("姓名不合法！");
        $("[name='name']").prev().css("color", "#f00");
    } else {
        $(this).prev().css("color", "#0EA74A");
        $("[name='name']").next().html("");
    }
});
$("[name='userName']").blur(function () {
    var v = $(this).val();
    if (v == '') {
        $("[name='userName']").next().html("手机号不能为空！");
        $(this).prev().css("color", "#f00");
    } else if (!v.match(d)) {
        $("[name='userName']").next().html("手机号不正确！");
        $("[name='userName']").prev().css("color", "#f00");
    } else {
        $(this).prev().css("color", "#0EA74A");
        $("[name='password']").next().html("");
    }
});
$("[name='password']").blur(function () {
    var v = $(this).val();
    if (v == '') {
        $("[name='password']").next().html("密码不能为空！");
        $(this).prev().css("color", "#f00");
    } else if (!v.match(p)) {
        $("[name='password']").next().html("密码不正确！");
        $("[name='password']").prev().css("color", "#f00");
    } else {
        $(this).prev().css("color", "#0EA74A");
        $("[name='password']").next().html("");
    }
});

$("[name='email']").blur(function () {
    var v = $(this).val();
    if (v == '') {
        $(this).prev().css("color", "#999");
    } else if (!v.match(y)) {
        $("[name='email']").next().html("请填写正确的邮箱！");
        $("[name='email']").prev().css("color", "#f00");
    } else {
        $(this).prev().css("color", "#0EA74A");
        $("[name='email']").next().html("");
    }
});
$("[name='idCard']").blur(function () {
    var v = $(this).val();
    if (v == '') {
        $("[name='idCard']").next().html("身份证号不能为空！");
        $(this).prev().css("color", "#f00");
    } else if (!v.match(idCard)) {
        $("[name='idCard']").next().html("身份证号不正确！");
        $("[name='idCard']").prev().css("color", "#f00");
    } else {
        $(this).prev().css("color", "#0EA74A");
        $("[name='idCard']").next().html("");
    }
});

