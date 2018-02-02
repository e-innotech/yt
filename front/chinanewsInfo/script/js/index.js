
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

    //当前页
    var page=1;
    $('.more').click(function(){
        getHomeNewsList();
    })

    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,10, function callback(newsList) {
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<div class="news_img">' +
                '<img src=' + newsList[i].topImagePath + ' alt="" onload="drawImage(this,206,150)"/>' +
                '</div>' +
                '<div class="list-r">'+
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p>'+newsList[i].createDate+ '</p>'+
                '<div>' +newsList[i].subContent+'</div>' +
                '</div>'+
                '</a>' +
                '</div>')
            }
            if(newsList.length>0){
                page++;
            }else{
                $('.more').html('没有更多了');
            }
        })
    };


})