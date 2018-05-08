/**
 * 全局变量
 */
var nodeData;//选择菜单后的数据
var pageNum = 1;//翻页：页数
var pageSize = 15;//翻页：数量
var version  = '1.0.0';
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
    var arr = uri.split('/');
    arr.shift();
    re = 'nodes/' + arr.join('_',arr) + '.html';
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
        //beforeSend: function() {
        //    $('#saveBtn').css('background','red');
        //},
        success: function (result) {
            console.log('resultresultresult=',result)
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
            //debugger;
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
        visiblePages: 10,
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
}
$('body').on('hidden.bs.modal', '.modal', function () {
    $(this).removeData('bs.modal');
});
function initPages(pgId,pgTxt,total,callback){
    if(pageNums>1){
        return;
    }
    $.jqPaginator('#'+pgId, {
        totalCounts:Number(total)==0?1:Number(total),
        pageSize:pageSizes,
        visiblePages: 3,
        currentPage: pageNums,
        first: '<li class="first"><a href="javascript:;"><<</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        last: '<li class="last"><a href="javascript:;">>></a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
//	            alert(type + '：' + num);
            if(type == 'change'){
                pageNums = num;
                callback();
            }
            pgTxt.text('当前第'+pageNums+'页 共'+Math.ceil(total/pageSizes)+'页（每页'+pageSizes+'条 共：'+total+'条）');
        }
    });
}
//全选
var isCheckAll = false;
function swapCheck() {
    if (isCheckAll) {
        $("input[type='checkbox']").each(function() {
            this.checked = false;
        });
        isCheckAll = false;
    } else {
        $("input[type='checkbox']").each(function() {
            this.checked = true;
        });
        isCheckAll = true;
    }
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


function removeOptions(selectObj) {
    if (typeof selectObj != 'object') {
        selectObj = document.getElementById(selectObj);
    }
    // 原有选项计数
    for (var i=0; i < selectObj.options.length; i++) {
        // 移除当前选项
        selectObj.options[0] = null;
    }
}
function setSelectOption(selectObj, optionList, firstOption, selected) {
    if (typeof selectObj != 'object') {
        selectObj = document.getElementById(selectObj);
    }
    // 清空选项
    removeOptions(selectObj);
    // 选项计数
    var start = 0;
    // 如果需要添加第一个选项
    if (firstOption) {
        selectObj.options[0] = new Option(firstOption, '');
        // 选项计数从 1 开始
        start ++;
    }
    for (var i=0; i < optionList.length; i++) {
        // 设置 option
        selectObj.options[start] = new Option(optionList[i].txt, optionList[i].val);
        // 选中项
        if(selected == optionList[i].val)  {
            selectObj.options[start].selected = true;
        }
        // 计数加 1
        start ++;
    }
}



function setCity(province) {
    var homeWeightArr = [];
    homeWeightArr['鹰眼新闻'] =/*id=1*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'专栏', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'热门文章', val:'5'}
        ];
    homeWeightArr['第一新闻网'] =/*id=2*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'专栏', val:'2'},
            {txt:'新闻列表', val:'3'}
        ];
    homeWeightArr['今日财经网'] =/*id=3*/
        [
            {txt:'专栏', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点推荐', val:'3'}
        ];
    homeWeightArr['中华财经资讯网'] =/*id=6*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'热点要闻', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'财经专题', val:'4'}
        ];
    homeWeightArr['每日新闻'] =/*id=7*/
        [
            {txt:'专栏', val:'1'},
            {txt:'右上', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'焦点新闻', val:'4'}
        ];
    homeWeightArr['中华财经网'] =/*id=8*/
        [
            {txt:'专栏', val:'1'},
            {txt:'轮播图', val:'2'},
            {txt:'新闻列表', val:'3'}
        ];
    homeWeightArr['易趣财经'] =/*id=12*/
        [
            {txt:'专栏', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'右上', val:'3'},
            {txt:'右下', val:'4'}
        ];
    homeWeightArr['中国新闻资讯网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'专栏', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'热点新闻', val:'4'}
        ];
    homeWeightArr['中华新闻网'] =/*id=*/
        [
            {txt:'专栏', val:'1'},
            {txt:'轮播图', val:'2'},
            {txt:'新闻列表', val:'3'}
        ];
    homeWeightArr['中国新闻采编网'] =/*id=*/
        [
            {txt:'专栏', val:'1'},
            {txt:'轮播图', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'今日热点', val:'4'}
        ];
    homeWeightArr['财经观点网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'焦点新闻', val:'3'}
        ];
    homeWeightArr['第一传媒网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'专栏', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'热点', val:'4'}
        ];
    homeWeightArr['焦点新闻网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'推荐', val:'3'},
            {txt:'热点新闻', val:'4'}
        ];
    homeWeightArr['环球财经资讯网'] =/*id=*/
        [
            {txt:'专栏', val:'1'},
            {txt:'轮播图', val:'2'},
            {txt:'新闻列表', val:'3'}
        ];
    homeWeightArr['环球财经网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'专栏', val:'2'},
            {txt:'新闻列表', val:'3'}
        ];
    homeWeightArr['中国产经资讯'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点新闻', val:'3'}
        ];
    homeWeightArr['界面资讯网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'右上', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'新闻排行', val:'4'}
        ];
    homeWeightArr['国际财经网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点新闻', val:'3'}
        ];
    homeWeightArr['中国新闻在线'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点专题', val:'3'}
        ];
    homeWeightArr['掌上财经'] =/*id=*/
        [
            {txt:'专栏', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点专题', val:'3'}
        ];
    homeWeightArr['红鼎财经网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'热点', val:'3'}
        ];
    homeWeightArr['顶端财经'] =/*id=*/
        [
            {txt:'专栏', val:'1'},
            {txt:'轮播图', val:'2'},
            {txt:'新闻列表', val:'3'},
            {txt:'热点推荐', val:'4'}
        ];
    homeWeightArr['中国顶端资讯网'] =/*id=*/
        [
            {txt:'轮播图', val:'1'},
            {txt:'新闻列表', val:'2'},
            {txt:'新闻推荐', val:'3'},
            {txt:'热文排行', val:'4'}
        ];
    setSelectOption('homeWeight', homeWeightArr[province], '请选择位置');
}


