
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

    // 加载更多
    var commentData='';
    var page=1;
    /*分页*/
    $('.more').click(function(){
        console.log(555,commentData.length);
        if (commentData.length <=0) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeNewsList();
        }
    })

    //<div class="news_list">
    //<a href="detail.html">
    //<h2>
    //独家 | 输给了空间阿里斯顿拉科技师范大咖啡里斯顿拉科技师范大里斯顿拉科技师范大
    //</h2>
    //<p><span class="source">中南海</span><span class="date">2017-03-17</span></p>
    //<div>
    //咖啡色卡萨丁卡仕达克里斯多夫客户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克色卡
    //萨丁卡仕达克里斯多夫客户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克色卡萨丁卡仕达克里斯多夫客
    //户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克里斯多夫客户卡好久水电费卡拉丁卡仕达克里斯多夫客户卡好久水电费卡拉
    //<!--<span>查看详情</span>-->
    //</div>
    //</a>
    //</div>
    getHomeNewsList();
    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,4, function callback(newsList) {
            console.log(34,newsList);
            commentData = newsList;
            for (var i = 0; i < newsList.length; i++) {
                $('.news_listbox').append('<div class="news_list">' +
                '<a href="">' +
                '<h2>' +newsList[i].newsTitle+' </h2>' +
                '<p>'+
                '<span class="sourse">'+newsList[i].source+'</span>'+
                '<span class="date">' +newsList[i].createDate+'</span>' +
                '</p>'+
                '<div>' +newsList[i].subContent+'</div>' +
                '</div>' +
                '</a>' +
                '</div>')
            }
        })
    };


})