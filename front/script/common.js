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
            var userName=$('input[name="userName"]').val();
            sessionStorage.setItem('synUser',userName);
            location.replace('index.html');
        } else {
            alert(re.msg);
        }
    });
}
function memberslogout() {
    AjaxFunc($members.logout, 'get', null, function (re) {
        if (re.success) {
            //sessionStorage.removeItem('userinfo');
            //sessionStorage.removeItem('permissons');
            location.replace('index.html');
        } else {
            alert(re.msg);
        };
    });
}

