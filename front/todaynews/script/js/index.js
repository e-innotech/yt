
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


    var commentData="";
    var page=1;
    getHomeNewsList();
    function getHomeNewsList() {
        //传的网站ID 页数 一页显示几条数据
        homeList(1, page, 10, function callback(newsList) {
            commentData = newsList;
        })
    };

    //点击加载更多时
    $('.more').click(function(){
        if (commentData.length < 8) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeNewsList();
        }
    })





})