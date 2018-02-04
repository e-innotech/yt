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
$(document).ready(function () {
    // 加载更多

    var page=1;
    $('.more').click(function(){
        getNavList();
    })
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(newsList) {
            console.log(34,newsList);
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
                page++;
            }else{
                $('.more').html('没有更多了');
            }
        })
    }
})


