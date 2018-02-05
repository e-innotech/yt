
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
        var page=1;
        function getNavList() {
            //传的权限 页数 一页显示几条数据
            newsList(3, page,4, function callback(newsList) {
                console.log(23,newsList);
                if(newsList.length>0) {
                    for (var i = 0; i < newsList.length; i++) {
                        $('.news_listbox').append('<ul class="news_list">' +
                       '<li>'+
                        '<a href="#">'+newsList[i].newsTitle+'</a>'+
                        '<span>'+newsList[i].createDate+'</span>'+
                       '</li>'+
                        '<li>'+
                        '<a href="#">'+newsList[i].source+'</a>'+
                        '<span>'+newsList[i].createDate+'</span>'+
                        '</li>'+
                        '<li>'+
                        '<a href="#">'+newsList[i].submitUserId+'</a>' +
                        '<span>'+newsList[i].createDate+'</span>'+
                        '</li>'+
                        '<li>'+
                        '<a href="#">'+newsList[i].subContent
                        +'</a>' +
                        '<span>'+newsList[i].createDate+'</span>'+
                        '</li>'+
                        '</ul>')
                    }

                }
            })
        };

//点击上一页时
        $('.prevpages').click(function () {
            if(page==1){
                page=1;
            }else{
                $(".news_listbox").html('');
                page--;
                getNavList();
            }
            $('.nextpages').attr("disabled",false);

        });
//点击下一页时
    $('.nextpages').click(function () {
        $(".homepages").attr("disabled","false");
        $(".news_listbox").html('');
        page++;
        getNavList();
    });

    //点击按钮时
    $(".pages").on("click","button",function(){
        $(this).css("background","#999999").siblings().css("background","none");

    })

    //如果进来是首页时就给首页的元素加个禁用的属性
    if(page==1){
        $(".homepages").attr("disabled","disabled");
    }else{
        $(".homepages").attr("disabled","false");
    }
    //点击首页时
    $(".homepages").click(function(){
        if(page!=1){
            page=1;
            getNavList();
            $(".homepages").attr("disabled","true");
        }
    });

    ///*分页*/
    //var page = 1;
    //var commentData='';
    //var channelId = getIdFromUrl();
    //var getNavList = function(){
    //    newsList(channelId,page,8,function callback(NavList){
    //        commentData=NavList;
    //    })
    //};
    ////点击那个button给那个加个背景颜色
    //$(".pages").on("click","button",function(){
    //    $(this).css("background","#999999").siblings().css("background","none");
    //
    //})
    //
    //

    ////点击上一页时
    $('.prevpages').click(function () {
        $(".news_listbox").html('');
        page--;
        getNavList();
        if(page==1){
            page=1;
            $(".homepages").attr("disabled","disabled");
        }
    });
    ////点击下一页时
    //$('.nextpages').click(function () {
    //    $(".homepages").removeAttr("disabled");
    //    page++;
    //    getNavList();
    //});
    ////点击尾页时
    //$('.lastpages').click(function () {
    //    $(".homepages").removeAttr("disabled");
    //    //一共有多少页
    //    //page=0;
    //    getNavList();
    //    console.log(77, commentData);
    //});
})