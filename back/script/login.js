$(function () {

    config.init();
    $("#login").click(function (){
        var user = {'userName':$('.username').val(),'passWord':$('.password').val()};


        $.ajax({
            type:'POST',
            url:$admin.login,
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify(user),
            success:function (data) {
                console.log(data);
                //判断如果登录成功，就跳转到home页面，并且存储权限数据到sessionstorage
                if(data.success){
                    if($('.remember').attr('checked')){
                        sessionStorage.setItem('user',user);
                    }
                    sessionStorage.setItem('permissons',data.data);
                    alert(1)
                    location.replace('home.html');
                }else{
                    alert(data.msg);
                }
            }
        });
    });

})