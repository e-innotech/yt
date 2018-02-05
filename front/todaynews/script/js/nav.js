//有一定滚动时显示这个top
//$(window).scroll(function () {
//    //获取距离浏览器顶部距离并赋值th
//    var th = $(window).scrollTop();
//    if (th > 600) {
//        $("#backtop").show();
//    } else {
//        $("#backtop").hide();
//    }
//});

//
$(document).ready(function () {
    //当前页
    var pages=1;
    var channelId = 3;
    $('#more').click(function(){
        getNavList();
    })

    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(channelId, pages,10, function callback(newsList) {
            console.log(channelId);
            console.log(223,newsList.length);
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<div>' +newsList[i].subContent+'</div>' +
                '<p>' +
                '<span class="source">' +newsList[i].source+'</span>' +
                '<span class="date">' +newsList[i].createDate+'</span>' +
                '</p>' +
                '</div>' +
                '</a>' +
                '</div>')
            }
            if(newsList.length>0){
                alert(newsList.length);
                pages++;
            }else{
                $(".more").html("没有更多了");
            }
        })
    };
})



