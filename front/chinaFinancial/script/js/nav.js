
$(document).ready(function () {
    //有一定滚动时显示这个top
    $(window).scroll(function () {
        //获取距离浏览器顶部距离并赋值th
        var th = $(window).scrollTop();
        if (th > 600) {
            $("#backtop").show();
        } else {
            $("#backtop").hide();
        }
    });


    /*分页*/
    var page = 1;
    var commentData='';
    var channelId = getIdFromUrl();
    var getNavList = function(){
        newsList(channelId,page,8,function callback(NavList){
            commentData=NavList;
        })
    };
    //点击首页时
    $('.homepages').click(function () {
        page=1;
        getNavList();

    });
    //点击上一页时
    $('.prevpages').click(function () {
        alert(1111)
        page--;
        getNavList();
        if(page==0){
            page=1;
        }
    });
    //点击下一页时
    $('.nextpages').click(function () {
        page++;
        getNavList();
    });
    //点击尾页时
    $('.lastpages').click(function () {
        //一共有多少页
        page=0;
        getNavList();
    });



})