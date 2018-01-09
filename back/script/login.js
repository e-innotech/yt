$(function () {

    config.init();

    if(sessionStorage.hasOwnProperty('user')){
        var re = JSON.parse(sessionStorage.getItem('user'));
        $('input[name="username"]').val(re.userName);
        $('input[name="password"]').val(re.passWord);
        $('input[name="remember"]').attr('checked','checked');
    }


    $("#login").click(function (){
        var user = {userName:$('input[name="username"]').val(),passWord:$('input[name="password"]').val()};
        AjaxFunc($admin.login,'post',user,function (re) {
            if(re.success){
                if($('input[name="remember"]').is(':checked')){
                    // console.log('remember')
                    sessionStorage.setItem('user',JSON.stringify(user));
                }
                var userinfo = {userName:re.data.userName,roleName:re.data.roleName};
                sessionStorage.setItem('userinfo',JSON.stringify(userinfo));
                sessionStorage.setItem('permissons',JSON.stringify(re.data.menu));
                location.replace('home.html');
            }else{
                alert(re.msg);
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