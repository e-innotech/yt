$(document).ready(function () {
    backtop();
    //当前页
    var pages=2;
    var channelId = 3;
    $('#more').click(function(){
        if($('.more').html()=='没有更多了'){
            $(this).attr('disabled','true');
        }else{
            getNavList();
        }


    })

    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(channelId, pages,10, function callback(data) {
            console.log(channelId);
            var newsList=data.list;
            console.log(223,newsList.length);
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
            if(newsList.length>0){
               console.log(newsList.length);
                pages++;
                if(newsList.length<10){
                    $(".more").html("没有更多了");
                }
            }else{
                $(".more").html("没有更多了");
            }
        })
    };
})



