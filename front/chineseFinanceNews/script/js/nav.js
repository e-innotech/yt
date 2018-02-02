$(document).ready(function() {
    /*分页*/
    var page = 0;
    var commentData='';
    var getNavList = function(){
        //栏目id=1

        newsList(1,page,10,function callback(NavList){
            commentData=NavList;
            for (var i = 0; i < NavList.length; i++) {
                console.log(NavList)
                $('#navList').append(
                    '<li>' +
                    '<a href="#">' +
                    '<div>' +
                    '<h3>'+NavList[i].newsTitle+'</h3>' +
                    '<div class="list_info">' +
                    '<span>发布时间：</span>' +
                    '<span class="time">'+NavList[i].createDate+'</span>' +
                    '</div>' +
                    '</div>' +
                    '</a>' +
                    '</li>')
            }

        })
    };
    $('.prev').click(function () {
        if(page==1){
            page=1;
        }else{
            $("#navList").html('');
            page--;
            getNavList();
        }
    });
    console.log(commentData.length)

    $('.next').click(function () {
        if(commentData.length>0){
            $('.next').attr('disabled',true);
        }else{
            $("#navList").html('');
            page++;
            getNavList();
        }
    });

});

