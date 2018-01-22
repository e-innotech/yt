$(document).ready(function () {
    var publishId = getIdFromUrl();
    var page = 1;
    var commentData = '';
    var commentQuery = function () {
        commentDetailList(publishId, page, 5, initCommentList);
    };
    commentQuery();
    var user = JSON.parse(sessionStorage.getItem('user'));
    $("#commitBtn").click(function () {
        var content = $('#textarea_str').val();
        commentAdd(this.alt, content, function callback(data) {
            if (data.success) {
                $('#textarea_str').val('');
                alert("评论成功！");
                commentQuery();
            }
        });
    });
    //添加收藏
    $("#collectAdd").click(function () {
        collectAdd(publishId, function callback(data) {
        });
    });
    function collectAdd(publishId, callback) {
        //判断是否是登录后
        if (sessionStorage.getItem('user') != null) {
            var data = {publishId: publishId};
            AjaxFunc(apiUrl + '/member/collect/add', 'post', data, function (re) {
                if (re.success) {
                    if (callback) {
                        $(".cang img").attr("src", "../images/collect.png");
                        $(".cang p").css("display", "none");
                        alert("收藏成功");
                    }
                }
            });
        } else {
            alert("请登录后才可以收藏");
        }
    };

    $('.more').click(function () {
        if (commentData.list.length < 5) {
            $('.more').html('没有更多了');
        } else {
            page++;
            commentQuery();
        }
    });

    //有一定滚动时显示这个top
    $(window).scroll(function () {
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
