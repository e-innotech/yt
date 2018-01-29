
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
    //点击那个button给那个加个背景颜色
    $(".pages").on("click","button",function(){
        $(this).css("background","#999999").siblings().css("background","none");

    })


    //如果进来是首页时就给首页的元素加个禁用的属性
    if(page==1){
        $(".homepages").attr("disabled","disabled");
    }


    //点击首页时
    $('.homepages').click(function () {
        if(page!=1){
            page=1;
            getNavList();
            $(".homepages").attr("disabled","disabled");
        }
    });
    //点击上一页时
    $('.prevpages').click(function () {
        page--;
        getNavList();
        if(page==0){
            page=1;
            $(".homepages").attr("disabled","disabled");
        }
    });
    //点击下一页时
    $('.nextpages').click(function () {
        $(".homepages").removeAttr("disabled");
        page++;
        getNavList();
    });
    //点击尾页时
    $('.lastpages').click(function () {
        $(".homepages").removeAttr("disabled");
        //一共有多少页
        //page=0;
        getNavList();
        console.log(77, commentData);
    });



})