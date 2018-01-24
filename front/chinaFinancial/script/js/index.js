
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
})