$(function () {

    config.init();

    if(sessionStorage.hasOwnProperty('user')){
        var re = JSON.parse(sessionStorage.getItem('user'));
        $('input[name="username"]').val(re.userName);
        $('input[name="password"]').val(re.passWord);
        $('input[name="remember"]').attr('checked','checked');
    }


    $("#login").click(function (){
        var user = JSON.stringify({'userName':$('input[name="username"]').val(),'passWord':$('input[name="password"]').val()});
        $.ajax({
            type:'POST',
            url:$admin.login,
            contentType:'application/json',
            dataType:'json',
            data:user,
            success:function (data) {
                console.log(data);
                //判断如果登录成功，就跳转到home页面，并且存储权限数据到sessionstorage
                if(data.success){
                    if($('input[name="remember"]').is(':checked')){
                        // console.log('remember')
                        sessionStorage.setItem('user',user);
                    }
                    sessionStorage.setItem('permissons',JSON.stringify(data.data));
                    location.replace('home.html');
                }else{
                    alert(data.msg);
                }
            }
        });
    });
    $('input[name="remember"]').change(function () {
       // console.log(this.checked);
       if(!this.checked){
           sessionStorage.removeItem('user');
       }
    });

})