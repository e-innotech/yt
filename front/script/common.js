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
    AjaxFunc($members.login, 'post', data, function (re) {
        if (re.success) {
            if ($('input[name="remember"]').is(':checked')) {
                // console.log('remember')
                sessionStorage.setItem('user', JSON.stringify(data));
            }
            //var userinfo = {uname: re.data.uname, roleName: re.data.roleName};
            //sessionStorage.setItem('userinfo', JSON.stringify(userinfo));
            //sessionStorage.setItem('permissons', JSON.stringify(re.data.menu));
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

function indexHtml(){
    var data = {id: '网站id', channelIds:'栏目id' ,channelName:'栏目名称'};
    AjaxFunc($members.index, 'post', data, function (re) {
        if (re.success) {
            if ($('input[name="remember"]').is(':checked')) {
                // console.log('remember')
                sessionStorage.setItem('user', JSON.stringify(data));
            }
            //var userinfo = {uname: re.data.uname, roleName: re.data.roleName};
            //sessionStorage.setItem('userinfo', JSON.stringify(userinfo));
            //sessionStorage.setItem('permissons', JSON.stringify(re.data.menu));
            location.replace('index.html');
        } else {
            alert(re.msg);
        }
    });
}
