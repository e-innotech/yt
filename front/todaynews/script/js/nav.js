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
    var commentData=10;
    var page=0;
    $('.more').click(function(){
        if (commentData.length <=0) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getNavList();
        }
    })
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(newsList) {
            console.log(34,newsList);
            commentData = newsList;
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
        })
    };

    $('.prevpages').click(function () {
        if(page==1){
            page=1;
        }else{
            $("#navList").html('');
            page--;
            getNavList();
        }
    });
    $('.nextpages').click(function () {
        $(".news_listbox").html('');
        page++;
        getNavList();
    });

})