$(function () {

    config.init();
    $("#login").click(function (){
        var data = {'userName':$('.username').val(),'passWord':$('.password').val()};


        $.ajax({
            type:'POST',
            url:$admin.login,
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify(data),
            success:function (data) {
                console.log(data);

            }
        });
    });

})