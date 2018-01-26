$(document).ready(function() {
    /*分页*/
    var page = 1;
    var commentData='';
    var channelId = getIdFromUrl();
    var getNavList = function(){
        newsList(channelId,page,8,function callback(NavList){
            commentData=NavList;
        })
    };
    $('.prev').click(function () {
        page--;
        getNavList();
        if(page==0){
            page=1;
        }
    });
    $('.next').click(function () {
        page++;
        getNavList();
    });

});

