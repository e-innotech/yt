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


$(document).ready(function(){
    //声明一个变量代表第几页
    var page=1;
    var allnum;
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(data){
            //console.log(66,newsList);
            //newsList.total
            allnum=data.total;
            console.log(77,allnum);
            var newsList=data.list;
            //console.log(newsList)
            //console.log(23,newsList.length);
                //array=newsList;
                for (var i = 0; i < newsList.length; i++){
                    $('.news_listbox').append('<div class="news_list">' +
                    '<a href="">' +
                    '<h2>' + newsList[i].newsTitle + ' </h2>' +
                    '<div>' + newsList[i].subContent + '</div>' +
                    '<p>' +
                    '<span class="source">' + newsList[i].source + '</span>' +
                    '<span class="date">' + newsList[i].createDate + '</span>' +
                    '</p >' +
                    '</a>' +
                    '</div>')
                }
        })
    };
//点击上一页时
    $('.prevpages').click(function (){
        if(page==1){
            page=1;
            $(this).attr("disabled","true")
        }else{
            $(".news_listbox").html('');
            page--;
            getNavList();
        }
        $('.nextpages').attr("disabled",false);
    });
   //点击下一页时
    $('.nextpages').click(function (){
        if(page>=Math.ceil(allnum/10)){
            $(this).attr("disabled","true");
            return
        }else{
            $(".news_listbox").html('');
            page++;
            getNavList();
        }
        $('.prevpages').attr("disabled",false);
    });
})


