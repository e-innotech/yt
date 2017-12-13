/*
 通用的ajax请求函数
 @param type get、post
 @param url
 @param isAsync true false
 @param data  object
 @param callback function
 */

function doAjax(type, url, isAsync, data, callback){
    //创建通信对象
    var xhr = createXhr();

    //判断通信类型
    if(type.toLowerCase() === "get"){
        //遍历拼接数据到url
        url +="?";
        for(var n in data){
            url += n+ "=" + data[n]+"&";
        }
        //截掉最后一位
        url = url.substr(0, url.length-1)
        //释放data
        data = null;
    }else{
        data = JSON.stringify(data);
    }

    //初始化通信对象
    xhr.open(type, url, isAsync);
    //发送请求
    xhr.send(data);

    //监听readyState变化
    xhr.onreadystatechange = function(){
        if((xhr.status>=200 && xhr.status<300) || xhr.status == 304){
            if(xhr.readyState === 4){
                var data = JSON.parse(xhr.responseText);
                //传递给回调函数
                callback && callback(data);
            }
        }
    };
}

//创建通信对象
function createXhr(){
    //大部分浏览器中使用XMLHttpRequest创建通信对象
    if(typeof XMLHttpRequest !== "undefined"){
        return new XMLHttpRequest();
    }else{
        //IE6及ie6以下浏览器不支持XMLHttpRequest 这些浏览器使用ActiveXObject创建通信对象，
        //在创建对象是需要根据浏览器具体版本传入不同的参数 如下： "MSXML.XMLHttp.6.0","MSXML.XMLHttp.3.0","MSXML.XMLHttp", "Microsoft.XMLHTTP"
        var args = ["MSXML.XMLHttp.6.0","MSXML.XMLHttp.3.0","MSXML.XMLHttp", "Microsoft.XMLHTTP"];
        for(var n=0; n<args.length; n++){
            //异常处理
            try{
                var xhr = new ActiveXObject(args[n]);
                return xhr;
            }catch(e){
                console.log(e);
            }
        }
    }
}

//点击增加按钮时
function add(){
    $(".alertbox").show();
    $(".add").show();
    $(".del").hide();
    $(".find").hide();

    //点击取消关闭弹窗
    $(".add .btn2").click(function(){
        $(".add").hide();
        $(".alertbox").hide();
    })
}

//点击删除按钮时
function del(e){
    $(".alertbox").show();
    $(".add").hide();
    $(".find").hide();
    $(".revise").hide();
    $(".del").show();
    //console.log($(e.target).closest("tr"));
    $(".del>.btn1").click(function(){
        $(e.target).closest("tr").remove();
        $(".alertbox").hide();
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