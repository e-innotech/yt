$(document).ready(function () {
    var page = 1;
    var commentData = '';
    $(".nav").on("click", "li", function () {
        $(this).addClass("active").siblings().removeClass("active")
    })
    $('.more').click(function () {
        if (commentData.length < 7) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeBoxList();
        }
    });
    getHomeBoxList();

    // 以下为中间部分新闻列表
    function getHomeBoxList() {
        //传的网站ID 页数 一页显示几条数据
        homeList(3, page, 7, function callback(newlist) {
            commentData = newlist;
        })
    };

    //有一定滚动时显示这个top
    $(window).scroll(function () {
        console.log(11111111111)
        //获取距离浏览器顶部距离并赋值th
        var th = $(window).scrollTop();
        //用if判断，距离顶部大于300时给一个警告弹窗
        if (th > 600) {
            $("#toptop").show();
        } else {
            $("#toptop").hide();
        }
    });
});







