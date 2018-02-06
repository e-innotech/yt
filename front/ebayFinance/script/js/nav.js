
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
        var totalnum;
        function getNavList() {
            //传的权限 页数 一页显示几条数据
            newsList(3, page,8, function callback(data){
                totalnum=data.total;
                var newsList=data.list;
                for (var i = 0; i < newsList.length; i++){
                    $('.news_listbox').append('<div class="news_list">' +
                        '<a href="">' +
                        '<h2>' +newsList[i].newsTitle+' </h2>' +
                        '<p>' +newsList[i].createDate+'</p>' +
                        '<div>' +newsList[i].subContent+'</div>' +
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
            if(page>=Math.ceil(totalnum/8)){
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
