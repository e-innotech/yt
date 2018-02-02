
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
    var page = 1;
    var commentData='';
    var getNavList = function(){
        //栏目id=1
        newsList(1,page,10,function callback(newsList){
            commentData=newsList;
            for (var i = 0; i < newsList.length; i++) {
                console.log(newsList)
                    $('.news_listbox').append('<div class="news_list">' +
                    '<a href="">' +
                    '<h2>' +newsList[i].newsTitle+' </h2>' +
                    '<p>' +newsList[i].createDate+'</p>' +
                    '<div>' +newsList[i].subContent+'</div>' +
                    '</a>' +
                    '</div>')

            }
        })
    };
    $('.prevpages').click(function () {
        if(page==1){
            page=1;
        }else{
            $(".news_listbox").html('');
            page--;
            getNavList();
        }
    });
    $('.nextpages').click(function () {
        $(".news_listbox").html('');
        page++;
        getNavList();
    });



})