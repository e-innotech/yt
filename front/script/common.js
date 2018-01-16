var apiUrl = 'http://192.168.20.195:8080/';
var websiteId = 3;
function AjaxFunc(url, type, data, callBack) {
    var obj = {
        type: type,
        url: url,
        async: false,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            callBack(result)
        }
    };
    switch (type) {
        case 'get':
            if (data) {
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
function homeList(homeWeight,pageNum,pageSize,callback){

    var data = {websiteId:websiteId,homeWeight:homeWeight,pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'home','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data.list);
                return;
            }
        }
     });
}

function removeHTMLTag(str) {
    str = str.replace(/<\/?.+?>/g,""); //去除HTML tag
    str = str.replace(/(^\s*)|(\s*$)/g, ""); //去除行尾空白
    str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/ /g,'');//去掉
    return str;
}

function memberslogadd() {
    var data = {uname: $('input[name="userName"]').val(), pwd: $('input[name="password"]').val()};
    AjaxFunc($members.logadd, 'post', data, function (re) {

        if (re.success) {

            location.replace('template.html');
        } else {
            alert(re.msg);
        }
    });
}
function memberslogin() {
    var data = {uname: $('input[name="userName"]').val(), pwd: $('input[name="password"]').val()};
    AjaxFunc($members.login, 'post', data, function (re) {
        if (re.success) {
            if ($('input[name="remember"]').is(':checked')) {
                // console.log('remember')
                sessionStorage.setItem('data', JSON.stringify(data));
            }
            sessionStorage.setItem('memberinfo', JSON.stringify(re.data));
            var userName=$('input[name="userName"]').val();
            sessionStorage.setItem('synUser',userName);
           // console.log("会员登陆:" + re.data.uname);
          location.replace('index.html');
        } else {
            alert(re.msg);
        }
    });
}
function memberslogout() {
    AjaxFunc($members.logout, 'get', null, function (re) {
        if (re.success) {
            sessionStorage.removeItem('userinfo');
            sessionStorage.removeItem('permissons');
            location.replace('index.html');
        } else {
            alert(re.msg);
        };
    });
}



////添加收藏
function collectAdd(publishId){
    var memberinfo=JSON.parse(sessionStorage.getItem('memberinfo'));
    console.log(11111,memberinfo.id)
    var data={membersId:memberinfo.id,publishId:publishId};
    AjaxFunc($members.collectAdd, 'post', data, function (re) {
        //p[]
    });
}


