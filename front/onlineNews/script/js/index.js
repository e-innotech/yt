
$(document).ready(function(){

    $('.back_to_top').Totop({
        autohide:true,//设置元素是否一开始就隐藏，
        speed:500,//到顶部速度
        right:274,
        bottom:210,
        offsetTop:400,//当scrollTop等于这个值时，如果autohide:true元素显示
        position:true//是否采用fixed定位
    })

    // 加载更多
    var commentData="";
    var page=1;
    getHomeNewsList();
    function getHomeNewsList() {
        //传的网站ID 页数 一页显示几条数据
        homeList(1, page, 8, function callback(newsList) {
            commentData = newsList;
        })
    };

    /*加载更多分页*/
    $('.more').click(function(){
        if (commentData.length <= 0) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeNewsList();
        }
    })
});