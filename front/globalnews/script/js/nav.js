
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

    /*分页*/
    var page =0;
    var commentData=10;
    var getNavList = function(){
        //栏目id=1
        newsList(1,page,8,function callback(newsList){
            commentData=newsList;
            for (var i = 0; i < newsList.length; i++) {
                console.log(newsList)
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
                    '</div>'
                )
            }
        })
    };
    $('.prev').click(function () {
        if(page==1){
            page=1;
        }else{
            $(".news_listbox").html('');
            page--;
            getNavList();
        }
    });
    $('.next').click(function () {
        $(".news_listbox").html('');
        page++;
        getNavList();
    });





})