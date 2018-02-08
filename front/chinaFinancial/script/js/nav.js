$(document).ready(function(){
    backtop();
    //声明一个变量代表第几页
    var page=1;
    var totalnum=$("#alltotalnum").val();
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(data){
            totalnum=data.total;
            var newsList=data.list;
            for (var i = 0; i < newsList.length; i++){
                $('.news_listbox').append('<ul class="news_list">' +
                '<li>'+
                '<a href="#">'+newsList[i].newsTitle+'</a>'+
                '<span>'+newsList[i].createDate+'</span>'+
                '</li>'+
                '<li>'+
                '<a href="#">'+newsList[i].source+'</a>'+
                '<span>'+newsList[i].createDate+'</span>'+
                '</li>'+
                '<li>'+
                '<a href="#">'+newsList[i].submitUserId+'</a>' +
                '<span>'+newsList[i].createDate+'</span>'+
                '</li>'+
                '<li>'+
                '<a href="#">'+newsList[i].subContent
                +'</a>' +
                '<span>'+newsList[i].createDate+'</span>'+
                '</li>'+
                '</ul>')
            }
        })
    };

    if(page==1){
        $(".homepages").attr("disabled",true);
        $('.prevpages').attr("disabled",true);
    }
    $(".pages").on("click","button",function(){
        $(this).css("background","#999999").siblings().css("background","none");
    })
//点击上一页时
    $('.prevpages').click(function (){
        if(page==1){
            $(this).attr("disabled","true");
            $(".homepages").attr("disabled",true);
        }else{
            $(".news_listbox").html('');
            page--;
            getNavList();
        }
        $(this).css("background","#999999").siblings().css("background","none");
        $('.nextpages').attr("disabled",false);
        $(".lastpages").attr("disabled",false);
    });

//点击下一页时
    $('.nextpages').click(function (){
            if(page>=Math.ceil(totalnum/10)){
                $(this).attr("disabled","true");
                return
            }else{
                $(".news_listbox").html('');
                page++;
                getNavList();
            }
        $('.prevpages').attr("disabled",false);
        $('.homepages').attr("disabled",false);
        $(this).css("background","#999999").siblings().css("background","none");
    });
//点击首页时
    $(".homepages").click(function(){
        if(page!=1){
            page=1;
        }
        $(this).attr("disabled",true);
        $(".news_listbox").html('');
        getNavList();
        $(".lastpages").attr("disabled",false);
        $(".nextpages").attr("disabled",false);
        $('.prevpages').attr("disabled",true);
        $(this).css("background","#999999").siblings().css("background","none");
    })

//点击尾页时
    $(".lastpages").click(function(){
            //var totalnum = $("#totalnum").html();
            //console.log(totalnum);
            page=Math.ceil(totalnum/10);
            $(".news_listbox").html('');
            getNavList();
            $(".nextpages").attr("disabled",true);
            $(this).attr("disabled",true);
            $(".homepages").attr("disabled",false);
            $('.prevpages').attr("disabled",false);
        $(this).css("background","#999999").siblings().css("background","none");
    })
})
