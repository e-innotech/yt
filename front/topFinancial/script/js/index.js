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
        homeList(3, page,8, function callback(newsList) {
            console.log(newsList.length);
            for (var i = 0; i < newsList.length; i++) {
                $('.con_lnewsbox').append('<div class="con_lnews">' +
                '<a href="">' +
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<div class="news_list">'+
                '<div class="news_img">' +
                '<img src=' + newsList[i].topImagePath + ' alt="" onload="drawImage(this,260,160)"/>' +
                '</div>' +
                '<div class="list_r">'+
                '<p class="date">' +newsList[i].createDate+ '</p>' +
                '<div class="news_cont">'+newsList[i].subContent+'</div>'+
                '</div>'+
                '</div>' +
                '</a>'+
                '</div>')
            }
            if(newsList.length>0){
                page++;
                if(newsList.length<8){
                    $('.more').html('没有更多了');
                }
            }else{
                $('.more').html('没有更多了');
            }
        })
    };
})

