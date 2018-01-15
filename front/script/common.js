config.init();
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
    console.log($members.login)
    AjaxFunc($members.login, 'post', data, function (re) {
        if (re.success) {
            if ($('input[name="remember"]').is(':checked')) {
                // console.log('remember')
                sessionStorage.setItem('user', JSON.stringify(data));
            }
            console.log(re)
            var userinfo = {userName:re.data.userName,roleName:re.data.roleName};
            sessionStorage.setItem('userinfo',JSON.stringify(userinfo));
            sessionStorage.setItem('permissons',JSON.stringify(re.data.menu));
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

function indexBanner(){
    var data = {websiteId: '3', homeWeight:'1',pageSize:pageSize,pageNum:pageNum };
    AjaxFunc($yynews.index, 'post', data, function (re) {
        if (re.success) {
            alert(1)
        } else {
            alert(re.msg);
        }
    });
}

function indexBanner(){
    var data = {websiteId: '3', homeWeight:'2',pageSize:pageSize,pageNum:pageNum };
    AjaxFunc($yynews.index, 'post', data, function (re) {
        if (re.success) {

        } else {
            alert(re.msg);
        }
    });
}
function indexBanner(){
    var data = {websiteId: '3', homeWeight:'3',pageSize:pageSize,pageNum:pageNum };
    AjaxFunc($yynews.index, 'post', data, function (re) {
        if (re.success) {

        } else {
            alert(re.msg);
        }
    });
}
function indexBanner(){
    var data = {websiteId: '3', homeWeight:'4',pageSize:pageSize,pageNum:pageNum };
    AjaxFunc($yynews.index, 'post', data, function (re) {
        if (re.success) {

        } else {
            alert(re.msg);
        }
    });
}
function indexBanner(){
    var data = {websiteId: '3', homeWeight:'5',pageSize:pageSize,pageNum:pageNum };
    AjaxFunc($yynews.index, 'post', data, function (re) {
        if (re.success) {

        } else {
            alert(re.msg);
        }
    });
}
