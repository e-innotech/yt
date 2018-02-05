

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
        //一页显示多少条
        var commentData=10;
        var page=0;
        $('.more').click(function(){
            console.log(555,commentData.length);
            if (commentData.length <=0) {
                $('.more').html('没有更多了');
            } else {
                page++;
                getHomeNewsList();
            }
        })

        //getHomeNewsList();
        function getHomeNewsList() {
            //传的权限 页数 一页显示几条数据
            homeList(3, page,10, function callback(newsList) {
                console.log(34,newsList);
                commentData = newsList;
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
            })
        };
    })