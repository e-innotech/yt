$(document).ready(function () {
    backtop();
    // 加载更多
    var page=1;
    $('.more').click(function(){
        getNavList();
    })
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,8, function callback(data) {
            //console.log(34,newsList);
            var newsList=data.list
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p>' +newsList[i].createDate+'</p>'+
                '<div>' +newsList[i].subContent+'</div>' +
                '</a>' +
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
    }
})


