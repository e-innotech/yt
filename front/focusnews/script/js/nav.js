$(document).ready(function () {
    backtop();
    var page=1;
    $('.more').click(function(){
        getNavList();
    })
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(data) {
            console.log(data);
            var newsList=data.list;
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p>'+
                '<span class="sourse">'+newsList[i].source+'</span>'+
                '<span class="date">' +newsList[i].createDate+'</span>' +
                '</p>'+
                '<div>' +newsList[i].subContent+'</div>' +
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
    }
})
