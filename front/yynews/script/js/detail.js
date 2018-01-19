$(document).ready(function () {
    var publishId = getIdFromUrl();
    var page = 1;
    var commentData='';
    var commentQuery = function () {
        commentDetailList(publishId, page, 5, initCommentList);
    };
    commentQuery();
    var user = JSON.parse(sessionStorage.getItem('user'));
    newsDetail(publishId, function callback(data) {
        $("title").html(data.newsTitle);
        $('#content_left').append('<div class="news-top">' +
        '<h2>' + data.newsTitle + '</h2>' +
        '<div class="dateinfo">' +
        '<div class="times">' +
        '发布时间:' +
        '<span>' + data.createDate + '</span>' +
        '</div>' +
        '<div class="info">' +
        '<p class="review">' +
        '<img src="../images/ping.png"/>' +
        '<span>' + data.comment + '</span>' +
        '</p>' +
        '<p class="read">' +
        '<img src="../images/yan.jpg"/>' +
        '<span>' + data.collect + '</span>' +
        '</p>' +
        '</div>' +
        '</div>' +
        '</div>');

        $('#content_center').append('<div class="news-top">' + data.content + '</div>');
        if (user != null) {
            var imgUrl = '../images/ren.png';
            if (user.infos) {
                if (user.infos.icon) {
                    imgUrl = user.infos.icon;
                }
            }
            $('.infoName').append('<img src="' + imgUrl + '">' + '<span>' + user.uname + '</span>');
        }
        $('.commentAdd').append('<textarea id="textarea_str" placeholder="评论字数限50个以内" onkeyup="countCode(this,50)"></textarea>' +
        '<input type="button"  id="commitBtn" alt="' + data.publishId + '" value="提交评论">');

    });
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


    function initCommentList(data) {
       //console.log("评论数据：",data);
        commentData=data;
        $('#commentCon-box').html('');
        for (var i = 0; i < data.list.length; i++) {
            $('.small:first').css('display', 'block');
            $('#commentCon-box').append('<div class="commentCon">' +
            '<div class="commentCon-l">' +
            '<img class="big" src="' + data.list[i].membersPic + '" alt="图像">' +
            '<img class="small" src="../images/sha.png" alt="沙发">' +
            '<p class="call">' + data.list[i].nickName + '</p>' +
            '<p class="date">' + data.list[i].createDate + '</p>' +
            '</div>' +
            '<div class="commentCon-r">' +
            '<p>' +
            '<span>' + (i + 1) + '楼</span>' +
            '</p>' +
            '<div class="commentbox">' + data.list[i].content + '</div>' +
            '</div>' +
            '</div>');
        }

    }
    $('.more').click(function () {
        if(commentData.list.length< 5){
            $('.more').html('没有更多了');
        }else{
            page++;
            commentQuery();
        }
    });
    //广告
    adList(2, function callback(list) {
        console.log(47, list);
        $(".advertisingDe").append('<img src="' + list[0].source + '"/>');

    });
    //点击收藏时，如上
    $(".cang img").on("click", function () {
        $(this).attr("src", "../images/collect.png");
        $(".cang p").css("display", "none")
    })

})


