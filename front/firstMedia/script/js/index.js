$(document).ready(function () {
   backtop();
    //当前页
    var page=2;
    $('.more').click(function(){
        if($('.more').html()=='没有更多了'){
            $(this).attr('disabled','true');
        }else{
            getHomeNewsList();
        }
    })

    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,10, function callback(newsList) {
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<div class="news_img">' +
                '<img src=' + newsList[i].topImagePath + ' alt="" onload="drawImage(this,260,160)"/>' +
                '</div>' +
                '<div class="list-r">'+
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p>' +newsList[i].createDate+'</p>'    +
                '<div>' +newsList[i].subContent+'</div>' +
                '</div>' +
                '</a>' +
                '</div>')
            }
            if(newsList.length>0){
                page++;
                if(newsList.length<10){
                    $('.more').html('没有更多了');
                }
            }else{
                $('.more').html('没有更多了');
            }
        })
    };


})