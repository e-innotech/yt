
$(document).ready(function(){

    $('.back_to_top').Totop({
        autohide:true,//设置元素是否一开始就隐藏，
        speed:500,//到顶部速度
        right:370,
        bottom:192,
        offsetTop:400,//当scrollTop等于这个值时，如果autohide:true元素显示
        position:true//是否采用fixed定位
    })

    // 加载更多
    var commentData='';
    var page=0;
    function getHomeNewsList() {
        //传的权限 页数 一页显示几条数据
        homeList(3, page,1, function callback(newsList) {
            console.log(newsList);
            commentData = newsList;
            for (var i = 0; i < newsList.length; i++) {
                $('#moreList').append('<li>' +
                '<a href="">' +
                '<div>' +
                '<div class="img_box">' +
                '<img src="'+newsList[i].topImagePath+'" alt="" onload="drawImage(this,250,172)"/>' +
                '</div>' +
                '<div class="title">' +
                '<h3>'+newsList[i].newsTitle+'</h3>' +
                '<span class="time">'+newsList[i].createDate+'</span>' +
                '<p>'+newsList[i].subContent+'</p>' +
                '</div>' +
                '</div>' +
                '</a>' +
                '</li>')
            }
        })
    };
    /*分页*/
    $('.more').click(function(){
        if (commentData.length < 0) {
            $('.more').html('没有更多了');
        } else {
            page++;
            getHomeNewsList();
        }
    })
});