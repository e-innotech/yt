$(function(){
    config.init();
    var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
    var treeData = JSON.parse(sessionStorage.getItem('permissons'));
   console.log(45,treeData);
    var initHeader = function(){
        $('#userinfo').html(userinfo.roleName+"&nbsp;"+':'+"&nbsp;"+userinfo.userName);
        //修改密码
        $('#changepwd').click(function(){
            // console.log('pwd');
            $.get($components.pwdReset,function (result) {
                $('#popPanel').html(result);

                $('#pwdResetModal').modal('show');
                $('#pwdResetModalBtn').click(function () {
                    var pwd = {'currentPwd':$('#oldPwd').val(),'passWord':$('#newPwd').val()};
                    AjaxFunc($user.pwd,'post',pwd,function (re) {
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
                console.log(567,re);
                if(re.success) {
                    sessionStorage.removeItem('userinfo');
                    sessionStorage.removeItem('permissons');
                    location.replace('index.html');
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
            levels:1,//折叠
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
            onNodeExpanded: function (event,data) {
                console.log(event);
                var brothers = $('#tree').treeview('getSiblings', data);
                console.log(111,brothers);
                for(var i=0;i<brothers.length;i++){
                    $('#tree').treeview('collapseNode',brothers[i]);
                }
            },
            onNodeSelected: function (event, data) {
                // 事件代码...
                console.log(data);
                nodeData = data;
                console.log(getNote(data.uri))
                if(data.uri) {
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