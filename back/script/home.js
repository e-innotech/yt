$(function(){
    config.init();
    var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
    var treeData = JSON.parse(sessionStorage.getItem('permissons'));
   console.log(treeData);
    var initHeader = function(){
        $('#userinfo').html(userinfo.roleName+':'+userinfo.userName);
        //修改密码
        $('#changepwd').click(function(){
            console.log('pwd');
            $.get($components.pwdReset,function (result) {
                $('#popPanel').html(result);

                $('#pwdResetModal').modal('show');
                $('#pwdBtn').click(function () {
                    var pwd = JSON.stringify({'currentPwd':$('#oldPwd').val(),'passWord':$('#newPwd').val()});
                    $.ajax({
                        type: 'PUT',
                        url:$user.update,
                        contentType:'application/json',
                        data: pwd,
                        dataType: 'json',
                        success: function(data) {
                            alert(data.msg);
                            if(data.success){
                                $('#pwdResetModal').hide();
                            }

                        }
                    });
                });

            })
        })

        //登出
        $('#out').click(function(){
            $.ajax({
                type: 'post',
                url: $admin.logout,
                contentType:'application/json',
                dataType: 'json',
                success: function(data) {
                    if(data.success) {
                        sessionStorage.removeItem('userinfo');
                        sessionStorage.removeItem('permissons');
                        location.replace('login.html');
                    }else{
                        alert(data.msg);
                    }
                }
            })
        })

    }

    var initMenu = function(){
        $('#tree').treeview({
            data: treeData,
            showTags: true,
            text: "resourceName",
            levels:2,//折叠
            color: "#000000",
            backColor: "#FFFFFF",
            selectable: true,
            enableLinks: true,//启用链接
            highlightSelected: true,
            collapseAll:{ silent: true },
            state: {
                checked: true,
                disabled: true,
                expanded: true,
                selected: true
            }
        })
        //添加菜单点击加载页面逻辑，click-》body里的div取load相应页面
    };
    initHeader();
    initMenu();

})