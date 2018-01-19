/**
 * Created by admin on 2018/1/16.
 */
$(function(){

    var renderChannel = function(){
        if(sessionStorage.getItem("channel") != null){
            console.log(22222);
            var channels= JSON.parse(sessionStorage.getItem("channel"));
            for(var i=0;i<channels.length;i++){
                $(".header .nav").append('<li><a href="nav.html?id='+channels[i].id+'"> ' + channels[i].channelName + ' </a></li>');
            }
        }else{
            channelList(function callback(list){
                sessionStorage.setItem('channel',JSON.stringify(list));
                for(var i=0;i<list.length;i++) {
                    $(".header .nav").append('<li><a href="nav.html?id=' +list[i].id +'">'+list[i].channelName + ' </a></li>');
                }
            })
        }
    };
    var renderUser = function(){
        if(sessionStorage.getItem("user") != null){
            var user = JSON.parse(sessionStorage.getItem("user"));
            console.log(999,user);
            var imgUrl = '../images/ren.png';
            if(user.infos){
                if(user.infos.icon) {
                    imgUrl = user.infos.icon;
                }
            }
            $('.userAvatar').attr('src',imgUrl);

            $('.header-right-r').css('width','115px');
            $('.header-right-r p').css('display','block');
            $('#logoutBtn').click(function(){
                sessionStorage.setItem('currentUrl',location.href);
                memberslogout();
            });


            $('.userAvatar').one("click",function(){
                sessionStorage.setItem("one",JSON.stringify("one"));
                //location.replace('member.html');
            });
            if(sessionStorage.getItem("one") !=null){
                  return;
            }else{
                location.replace('member.html');

            }
            //$('.userAvatar').click(function(){
            //    location.replace('member.html');
            //});
        }else{
            $('.userAvatar').click(function(){
                sessionStorage.setItem('currentUrl',location.href);
                location.replace('sign.html');
            });

        }
    };
    renderChannel();
    renderUser();


    //搜索页面调用
    var page = 1;
    var getSearchList = function (data) {
        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
        location.href = 'search.html';
    };
    //搜索
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        //if(newsTitle==''){
        //    alert("请输入内容");
        //    return;
        //}
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(1, newsTitle, 1, 20, getSearchList);
    });
    $('.more').click(function () {
        page++;

    });



    //添加样式
    $(".nav a").each(function(){
        $this = $(this);
        if($this[0].href==String(window.location)){
            //console.log(12,$this);
            $this.addClass("active");  //hover表示被选中效果的类名
        }
    })

    //搜索页面调用
    var page = 1;
    var getSearchList = function (data) {
        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
        location.href = 'Originallist.html';
    };
//搜索
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        //if(newsTitle==''){
        //    alert("请输入内容");
        //    return;
        //}
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(1, newsTitle, 1, -1, getSearchList);
    });
    $('.more').click(function () {
        page++;

    });




});