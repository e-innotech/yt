
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

    // 加载更多
    var commentData='';
    var page=1;
    /*分页*/
    $('.more').click(function(){
        console.log(555,commentData.length);
        if (commentData.length <=0) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeNewsList();
        }
    })

    getHomeNewsList();


    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,4, function callback(newsList) {
            console.log(34,newsList);
            commentData = newsList;
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<div class="news_img">' +
                '<img src=' + newsList[i].topImagePath +' onload="drawImage(this,202,150)"/>' +
                '</div>' +
                '<div class="list-r">'+
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






})