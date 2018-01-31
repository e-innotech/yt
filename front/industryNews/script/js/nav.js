$(document).ready(function() {
    /*分页*/
    var page = 1;
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
                    '<span class="num">' + (i + 1) + '</span>' +
                    '<h3>'+NavList[i].newsTitle+'</h3>' +
                    '<p>'+NavList[i].subContent +'</p>' +
                    '<div class="list_info">' +
                    '<span class="source"><a href="">'+NavList[i].source +'</a></span>' +
                    '<span class="time">'+NavList[i].createDate+'</span>' +
                    '</div>' +
                    '</div>' +
                    '</a>' +
                    '</li>')
            }
        })
    };
    getNavList();
    $('.prev').click(function () {
        if(page==1){
            page=1;
        }else{
            $("#navList").html('');
            page--;
            getNavList();
        }
    });
    $('.next').click(function () {
        $("#navList").html('');
        page++;
        getNavList();
    });

});

