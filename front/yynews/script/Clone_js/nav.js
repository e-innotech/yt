/**
 * Created by Administrator on 2018/1/14.
 */
$(document).ready(function () {
    var page = 1;
    var commentData='';
    var channelId = getIdFromUrl();

    console.log('channelId='+channelId);
    var getNewsList = function(){
        newsList(channelId,page,6,function callback(newlist){
            commentData=newlist;
        })
    };

    $('.more').click(function(){
        if(commentData.length< 6){
            $('.more').html('没有更多了');
        }else{
            page++;
            getNewsList();
        }
    });


    //有一定滚动时显示这个top
    $(window).scroll(function(){
        //获取距离浏览器顶部距离并赋值th
        var th = $(window).scrollTop();
        //用if判断，距离顶部大于300时给一个警告弹窗
        if(th>600){
            $("#toptop").show();
        }else{
            $("#toptop").hide();
        }
    });
})