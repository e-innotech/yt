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
            console.log(user);
            $('.userAvatar').attr('src',user.infos.icon!=null?user.infos.icon:'../images/ren.png');
            $('.header-right-r').css('width','115px');
            $('.header-right-r p').css('display','block');
            $('#logoutBtn').click(function(){
                sessionStorage.setItem('currentUrl',location.href);
                memberslogout();
            });
            $('.userAvatar').click(function(){
                location.replace('member.html');
            });
        }else{
            $('.userAvatar').click(function(){
                sessionStorage.setItem('currentUrl',location.href);
                location.replace('sign.html');
            });
        }
    };
    renderChannel();
    renderUser();



});