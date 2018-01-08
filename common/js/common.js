/**
 * 全局变量
 */
var nodeData;//选择菜单后的数据
var pageNum = 1;//翻页：页数
var pageSize = 15;//翻页：数量
var resourceListType = 'parent';//资源列表选择类型
var resourceListSelectIds = [];//角色资源选择id集合

const RW = ['只读','读写'];
const MENU = ['否','是'];
const OFFONLINE = ['下线','上线'];

/**
 * 扩展jquery form序列化成json对象
 * @returns {{}|*}
 */
jQuery.prototype.serializeObject=function(){
    var a,o,h,i,e;
    a=this.serializeArray();
    o={};
    h=o.hasOwnProperty;
    for(i=0;i<a.length;i++){
        e=a[i];
        if(!h.call(o,e.name)){
            o[e.name]=e.value;
        }
    }
    return o;
};

/**
 * 根据资源地址uri转换为页面相对路径
 * @param uri
 * @returns {string|*}
 */
function getNote(uri) {
    var arr = uri.split('/');
    re = 'nodes/' + arr[1] + '_' + arr[2] + '.html';
    return re;
};

/**
 * 自定义封装ajax请求
 * @param url
 * @param type
 * @param data
 * @param callBack
 * @constructor
 */
function AjaxFunc(url,type,data,callBack) {
    var obj = {type:type,
        url:url,
        async: false,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success:function (result) {
            callBack(result)
        }
        };
    switch (type){
        case 'get':
            if(data){
                obj.data = data;
            }
            break;
        case 'post':
            obj.contentType = 'application/json';
            obj.data = JSON.stringify(data);
            break;
    };
    $.ajax(obj);
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
//正则手机号验证
function userName() {
    var d = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    $("[name='username']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $(".verifyCall").html("用户名不能为空！");
            //$(this).next().css("color", "#f00");
        } else if (!v.match(d)) {
            $(".verifyCall").html("用户名不正确！");
        } else {
            //$(this).next().css("color", "#0EA74A");
            $(".verifyCall").html("正确");
        }
    });
}
//正则密码验证
function passWord() {
    var p = /^[a-zA-Z]\w{5,17}$/;
    $("[name='password']").blur(function () {
        var v = $(this).val();
        if (v == '') {
            $(".verifyPassword").html("密码不能为空！");
        } else if (!v.match(p)) {
            $(".verifyPassword").html("密码格式不正确！");
        } else {
            $(".verifyPassword").html("正确");
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






