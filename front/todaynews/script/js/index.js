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
    //当前页
    var page=1;
    $('.more').click(function(){
        getHomeNewsList();
    })

    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,10, function callback(newsList) {
            console.log(2323,newsList.length);
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<div class="news_img">' +
                '<img src=' + newsList[i].topImagePath + ' alt="" onload="drawImage(this,260,160)"/>' +
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
                '<img src="../images/recommend.png" class="recommends"/>'+
                '</div>')
            }
            if(newsList.length>0){
                console.log(2324,newsList.length);
                page++;
            }else{
                $('.more').html('没有更多了');
            }
        })
    };
})
