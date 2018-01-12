var apiUrl = 'localhost:8888';

function AjaxFunc(url,type,data,callBack) {
    var obj = {
        type:type,
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
};

function login(username,password) {
    //AjaxFunc
    
}
function logout() {
    
}