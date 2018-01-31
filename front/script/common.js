//const apiUrl = 'http://192.168.20.195:8080';//
const uploadUrl = 'http://192.168.20.195:8888/yy/upload';
const apiUrl = 'http://123.59.156.27:8080';//测试

const websiteId = 1;
const sex = ['女','男'];
 var title="";
function serializeObject(a){
    var o,h,i,e;
    o={};
    h=o.hasOwnProperty;
    for(i=0;i<a.length;i++){
        e=a[i];
        if(!h.call(o,e.name)){
            o[e.name]=e.value;
        }
    }
    return o;
}
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
            if(result.errCode =='E00001'){
                loginTimeOut();
                return;
            }
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
//权重 页数 一页显示多少条 回调函数
function homeList(homeWeight,pageNum,pageSize,callback){

    var data = {websiteId:websiteId,homeWeight:homeWeight,pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'/web/home','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data.list);
                return;
            }
        }
     });
};
function adList(templateType,callback){
    var data = {websiteId:websiteId,templateType:templateType};
    AjaxFunc(apiUrl+'/common/ad','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};
function channelList(callback){
    var data = {websiteId:websiteId};

    AjaxFunc(apiUrl+'/common/channel','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};
function newsList(channelId,pageNum,pageSize,callback){
    var data = {websiteId:websiteId,channelId:channelId,pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'/web/channel/query','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data.list);
                return;
            }
        }
    });
};


function newsDetail(publishId,callback){
    var data = {publishId:publishId};
    AjaxFunc(apiUrl+'/web/detail','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};
function uploadIcon(data,callback){
    AjaxUpload(uploadUrl,data,function (re) {
        // console.log(re);
        alert(re.msg);
        if(re.success){
            if(callback) {
                callback(re.data[0]);
            }
        }
    });
};
//
function membersEdit(data,callback){
    AjaxFunc(apiUrl+'/members/update/info','post',data,function(re){
        alert(re.msg);
        if(re.success) {
            if(callback) {
                callback(re.data);
            }
        }
    });
};

function membersPwd(data){
    AjaxFunc(apiUrl+'/members/pwd','post',data,function(re){
        alert(re.msg);
    });
}
/**
 * 获取文章评论列表
 * @param publishId
 * @param pageNum
 * @param pageSize
 * @param callback
 */
//function commentList(publishId,pageNum,pageSize,callback){
//    var data = {publishId:publishId,pageNum:pageNum,pageSize:pageSize};
//    AjaxFunc(apiUrl+'/web/detail/comment','get',data,function(re){
//        if(re.success){
//            if(callback){
//                callback(re.data);
//                return;
//            }
//        }
//    });
//};
function addComment(publishId,content,callback){
    var data = {publishId:publishId,content:content};
    AjaxFunc(apiUrl+'/member/comment/add','get',data,function(re){
        callback(re);
    });
}
function getIdFromUrl(){
    var name = 'id';
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return decodeURI(r[2]); return null;
}
function countCode(obj,count){
    var str = obj.value;
    if(str.length>count){
        obj.value = str.substr(0,count);
    }
}
function removeHTMLTag(str) {
    str = str.replace(/<\/?.+?>/g,""); //去除HTML tag
    str = str.replace(/(^\s*)|(\s*$)/g, ""); //去除行尾空白
    str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/ /g,'');//去掉
    return str;
}
function delHtmlTag(str){
    return str.replace(/<[^>]+>/g,"");
};
//在哪个页面浏览的点击登录按钮跳到哪个页面
function memberslogin(uname,pwd) {
    var data = {uname: uname, pwd: pwd};
    AjaxFunc(apiUrl+'/members/login', 'post', data, function (re) {
        if (re.success) {
            sessionStorage.setItem('user',JSON.stringify(re.data));
            //判断关闭页面时在哪个页面
            if(sessionStorage.getItem('currentUrl')){
                var url=sessionStorage.getItem('currentUrl');
                var signurl=sessionStorage.getItem("login");
                //如果关闭前在登陆页面就让它跳到首页
                if(url==signurl){
                    location.replace("index.html");
                    return;
                }else{
                    location.replace(sessionStorage.getItem('currentUrl'));
                }
            }else{
                location.replace("index.html");
            }
        }else {
            alert(re.msg);
        }
    });
}
function memberslogout() {
    AjaxFunc(apiUrl+'/members/logout', 'get', null, function (re) {
        if (re.success) {
            sessionStorage.removeItem('user');
            location.replace(sessionStorage.getItem('currentUrl'));
        } else {
            alert(re.msg);
        };
    });
}
function memberslogadd(){
    var uname=$('input[name="userName"]').val(),pwd=$('input[name="password"]').val()
    var data = {uname: uname, pwd: pwd};
    console.log(uname)
    AjaxFunc(apiUrl+'/members/add', 'post', data, function (re) {
        if (re.success) {
            location.replace("sign.html");
        }else {
            alert(re.msg);
        }
    });
}

//添加评论
function commentAdd(publishId,content,callback) {
    var user=JSON.parse(sessionStorage.getItem('user'));
    if(user == null){
        alert("登陆之后才可以评论，请登陆");
        return;
    }
    var data = {publishId: publishId,content:content};
    AjaxFunc(apiUrl + '/web/member/comment/add', 'post', data, function (re) {
        if (re.success) {
            if (callback) {
                callback(re);
                return;
            }
        }
    });
}
////添加收藏
//function collectAdd(publishId,callback){
//    //console.log(2222225,publishId)
//    var data = {publishId: publishId};
//        AjaxFunc(apiUrl+'/member/collect/add', 'post', data, function (re){
//            if(re.success) {
//                if (callback) {
//                    console.log(222333, callback.data);
//                    callback(re.data);
//                    return;
//                }
//            }
//        });
//}
//会员收藏列表
function collectList(pageNum,pageSize,callback){
    var data = {pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'/member/collect/query','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};

//会员取消收藏
function cancelCollect(collect_id,callback){
    var data = {collectId:collect_id};
    AjaxFunc(apiUrl+'/member/collect/delete','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};
//删除会员的收藏
function deleteCollect(id){
    cancelCollect(id);
    sessionStorage.setItem("ddd","1");
    location.reload();

    //coll();
    //$(".content-right .rights").css("display","none");
    //$("#collectss").show();


}


////独家标题
//function newOriginallist(newsTitle,pageNum,pageSize,callback){
//    var data = {websiteId:websiteId,newsTitle:newsTitle,pageNum:pageNum,pageSize:pageSize};
//    AjaxFunc(apiUrl+'/web/channel/query','get',data,function(re){
//        if(re.success){
//            if(callback){
//                callback(re.data.list);
//                return;
//            }
//        }
//    });
//};

//搜索
function globalQuery(websiteId, newsTitle, pageNum, pageSize, callback) {
    var data = {websiteId: websiteId, newsTitle: newsTitle, pageNum: pageNum, pageSize: pageSize};
    AjaxFunc(apiUrl + '/web/global/query', 'get', data, function (re) {
        if (re.success) {
            if (callback) {
                callback(re.data);
                return;
            }
        }
    });
}


function commentDetailList(publishId,pageNum,pageSize,callback){
    var data = {publishId:publishId,pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'/web/detail/comment','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};
////添加评论
//function commentAdd(publishId,content,callback) {
//    var user=JSON.parse(sessionStorage.getItem('user'));
//    if(user == null){
//        alert("登陆之后才可以评论，请登陆");
//        return;
//    }
//    var data = {publishId: publishId,content:content};
//    AjaxFunc(apiUrl + '/web/member/comment/add', 'post', data, function (re) {
//        if (re.success) {
//            if (callback) {
//                callback(re);
//                return;
//            }
//        }
//    });
//}
//会员收藏列表

//会员评论列表
function memcommentList(pageNum,pageSize,callback){
    var data = {pageNum:pageNum,pageSize:pageSize};
    AjaxFunc(apiUrl+'/web/member/comment/query','get',data,function(re){
        if(re.success){
            if(callback){
                callback(re.data);
                return;
            }
        }
    });
};


function loginTimeOut(){
    sessionStorage.removeItem('user');
    location.replace('sign.html');
    alert(re.msg);
}

function drawImage(ImgD,twidth,theight){
    console.log(ImgD);
    ImgD.style = 'margin:0 0 0 0';
    var image=new Image();
    image.src=ImgD.src;
    var radix = twidth/theight;

    if(image.width/image.height >= radix){
        ImgD.height = theight;
    }else{
        ImgD.width = twidth;
    }
}


//回到顶部
'use strict';
$.fn.Totop = function (obj) {
    var othis = this;
    var win = $(window);
    var hb = $('html,body');
    var Option = $.extend(
        {
            autohide: true,//设置元素是否一开始就隐藏，
            speed: 500,//到顶部速度
            right: 10,
            bottom: 500,
            offsetTop: 400,//当scrollTop等于这个值时，如果autohide:true元素显示
            position: true//是否采用fixed定位
        }, obj);

    if (Option.autohide) {
        othis.css('display', 'none')
    } else {
        othis.css('display', 'block')
    }
    if (Option.position) {
        othis.css({
            'position': 'fixed',
            'right': Option.right,
            'bottom': Option.bottom
        })
    }
    othis.click(function () {
        hb.animate({
            scrollTop: 0
        }, Option.speed)
    });
    win.scroll(function () {
        var scrollT = win.scrollTop();
        if (Option.autohide) {
            if (scrollT > Option.offsetTop) {
                othis.fadeIn(Option.speed)
            } else {
                othis.fadeOut(Option.speed)
            }
        }
    })
}
