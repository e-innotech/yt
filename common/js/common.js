/**
 * 全局变量
 */
var nodeData;//选择菜单后的数据
var pageNum = 1;//翻页：页数
var pageSize = 15;//翻页：数量
var resourceListType = 'parent';//资源列表选择类型
var resourceListSelectIds = [];//角色资源选择id集合
var newsContent = '';//稿件编辑器内容

const RW = ['只读','读写'];
const MENU = ['否','是'];
const OFFONLINE = ['下线','上线'];
const HOME = ['','（首页）'];
const TEMPLATE = ['首页','栏目','详页'];
const ADUIT = ['待审核','已通过','已拒绝'];
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
    console.log(uri);
    var arr = uri.split('/');
    console.log("数组"+arr);
    arr.shift();
    console.log(3,arr);
    re = 'nodes/' + arr.join('_',arr) + '.html';
    console.log('last',re);
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
    var obj = {
        type:type,
        url:url,
        async: false,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            if(result.errCode =='E00001'){
                loginTimeOut();
                return;
            }
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
};
function loginTimeOut(){
    sessionStorage.removeItem('user');
    location.replace('index.html');
    alert(re.msg);
}
/**
 * 上传
 * @param url
 * @param type
 * @param data
 * @param callBack
 * @constructor
 */
function AjaxUpload(url,data,callBack) {
    var obj = {
        url:url,
        type:'post',
        dataType: "json",
        data:data,
        cache:false,
        processData:false,
        contentType:false,
        mimeType:"multipart/form-data",
        success:function (result) {
            callBack(result)
        }
    };
    $.ajax(obj);
};


/**
 * 翻页
 * @param total
 * @param callback
 */
function initPage(pgId,pgTxt,total,callback){
    if(pageNum>1){
        return;
    }
    $.jqPaginator('#'+pgId, {
        totalCounts:Number(total)==0?1:Number(total),
        pageSize:pageSize,
        visiblePages: 3,
        currentPage: pageNum,
        first: '<li class="first"><a href="javascript:;"><<</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        last: '<li class="last"><a href="javascript:;">>></a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
//	            alert(type + '：' + num);
            if(type == 'change'){
                pageNum = num;
                callback();
            }
            pgTxt.text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
        }
    });
};







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






