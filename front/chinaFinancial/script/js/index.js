$(document).ready(function () {
    $("#top").load("top.html");
    $("#bottom").load("bottom.html");
    $("#content-right").load("right.html");
    backtop();
//当前页
    var page = 2;
    //点击加载更多时
    $('.more').click(function () {
        if($('.more').html()=='没有更多了'){
            $(this).attr('disabled','true');
        }else{
            getHomeNewsList();
        }
    })
    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,8, function callback(newsList) {
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<div class="list-l">'+
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p class="date">'+newsList[i].createDate+ '</p>'+
                '<div>' +newsList[i].subContent+'</div>' +
                '</div>'+
                '<div class="news_img">'+
                '<img src=' + newsList[i].topImagePath + ' onload="drawImage(this,243,180)"/>'+
                '</div>'+
                '</a>' +
                '</div>')
            }
            if (newsList.length > 0) {
                page++;
                if(newsList.length<8){
                    $('.more').html('没有更多了');
                }
            } else {
                $('.more').html('没有更多了');
            }
        })
    }
})
