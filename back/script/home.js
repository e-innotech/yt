$(function(){
    config.init();
    var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
    var treeData = JSON.parse(sessionStorage.getItem('permissons'));
   //console.log(treeData);
    var initHeader = function(){
        $('#userinfo').html(userinfo.roleName+':'+userinfo.userName);
        //修改密码
        $('#changepwd').click(function(){
            // console.log('pwd');
            $.get($components.pwdReset,function (result) {
                $('#popPanel').html(result);

                $('#pwdResetModal').modal('show');
                $('#pwdResetModalBtn').click(function () {
                    var pwd = {'currentPwd':$('#oldPwd').val(),'passWord':$('#newPwd').val()};
                    AjaxFunc($user.update,'post',pwd,function (re) {
                        alert(re.msg);
                        if(re.success){
                            $('#pwdResetModal').modal('hide');
                        }
                    });
                });

            })
        })

        //登出
        $('#out').click(function(){
            AjaxFunc($admin.logout,'get',null,function (re) {
                if(re.success) {
                    sessionStorage.removeItem('userinfo');
                    sessionStorage.removeItem('permissons');
                    location.replace('login.html');
                }else{
                    alert(re.msg);
                };
            });
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
            },
            onNodeSelected: function (event, data) {
                // 事件代码...
                console.log(data);
                nodeData = data;
                console.log(getNote(data.uri))
                if(data.uri!=null) {
                    $.get(getNote(data.uri), function (re) {
                        $('#main').html(re);
                    });
                }

            }
        })

    };
    initHeader();
    initMenu();

})