//$(document).ready(function() {
//    /*分页*/
//    var page = 1;
//    var commentData='';
//    var getNavList = function(){
//        //栏目id=1
//        newsList(1,page,10,function callback(NavList){
//            commentData=NavList;
//            for (var i = 0; i < NavList.length; i++) {
//                console.log(NavList)
//                $('#navList').append('<li> <a href=""> <div class="title"> <h3>'+NavList[i].newsTitle+'</h3> <span class="time">'+NavList[i].createDate+'</span> </div> <p class="content_news">'+NavList[i].subContent+'</p> </a> </li>')
//            }
//        })
//    };
//    $('.prev').click(function () {
//        if(page==1){
//            page=1;
//        }else{
//            $("#navList").html('');
//            page--;
//            getNavList();
//        }
//    });
//    $('.next').click(function () {
//        $("#navList").html('');
//        page++;
//        getNavList();
//    });
//
//});
//
$(document).ready(function(){
    //声明一个变量代表第几页
    var page=2;
    var totalnum;
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(data){
            console.log(26,data);
            totalnum=data.total;
            var NavList=data.list;
            for (var i = 0; i < NavList.length; i++){
                $('#navList').append('<li> <a href=""> <div class="title"> <h3>'+NavList[i].newsTitle+'</h3> <span class="time">'+NavList[i].createDate+'</span> </div> <p class="content_news">'+NavList[i].subContent+'</p> </a> </li>')
            }
        })
    };
//点击上一页时
    $('.prev').click(function (){
        if(page==1){
            page=1;
            $(this).attr("disabled","true")
        }else{
            $("#navList").html('');
            page--;
            getNavList();
        }
        $('.next').attr("disabled",false);
    });
    //点击下一页时
    $('.next').click(function (){
        if(page>=Math.ceil(totalnum/10)){
            $(this).attr("disabled","true");
            return
        }else{
            $("#navList").html('');
            page++;
            getNavList();
        }
        $('.prev').attr("disabled",false);
    });
})

