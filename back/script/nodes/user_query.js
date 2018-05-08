$(function () {
    pageNum = 1;
    pageSize = 20;

    var userName = '';
    var userList = [];
    var selectUser;

    var ctrl_add = '';
    var ctrl_upate = '';

    var getUserList = function () {
        var data = {pageNum: pageNum, pageSize: pageSize};
        if ( userName != '') {
            data.userName = userName;
        };
        AjaxFunc($query.user, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getUserList);
            } else {
                alert(re.msg);
            }
        });
    };
    var addUser = function () {
        if($('input[name="userName"]').val()==''){
            alert('用户名不能为空');
            return;
        }
        var data = $('#roleForm').serializeObject();
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            if(re.success){
                $('#editModal').modal('hide');
                getUserList();
            }
            alert(re.msg);
        });
    };




    var editUser = function () {
        if($('input[name="userName"]').val()==''){
            alert('用户名不能为空');
            return;
        }
        var data = $('#roleForm').serializeObject();
        data.id = selectUser.id;
        AjaxFunc($apiUrl + ctrl_upate, 'post', data, function (re) {
            if (re.success) {
                getUserList();
                $('#editModal').modal('hide');
            }
            alert(re.msg);
        });
    };
    var statusUser = function (id, status) {
        var data = {id: id, isUse: status};
        AjaxFunc($apiUrl + ctrl_upate, 'post', data, function (re) {
            if (re.success) {
                getUserList();
            }
            alert(re.msg);
        });
    };
    var initialize = function () {
        for (var i = 0; i < nodeData.buttons.length; i++) {
            if (nodeData.buttons[i].uri.indexOf('add') != -1) {
                ctrl_add = nodeData.buttons[i].uri;
            };
            if (nodeData.buttons[i].uri.indexOf('update') != -1) {
                ctrl_upate = nodeData.buttons[i].uri;
            };

        }if (ctrl_add != '') {
            $('#addUserBtn').show();
            $('#addUserBtn').click(function () {
                showUserEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            userName = $('#userTxt').val();
            getUserList();
        });
        getUserList();
    };
    var initTable = function (list) {
        userList = list;
        $('#userT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#userT').append('<tr>' +
            '<td>' + list[i].userName + '</td>' +
            '<td>' + isUserGroup(list[i].userGroup) + '</td>' +
            '<td>' + list[i].createDate + '</td>' +
            '<td><p id="statusBtn_' + list[i].id + '" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '"><span></span></p></td>' +
            '<td>' + (ctrl_upate != '' ? '<button id="editBtn_' + list[i].id + '">编辑</button>' : '') + '</td>' +
            '</tr>');

            $('#editBtn_' + list[i].id).click(function () {
                selectUser = getUserFromId(this.id.split('_')[1]);
                showUserEdit('edit');
            });
            $('#statusBtn_' + list[i].id).click(function () {
                var isUse = 0;
                if ($(this).attr('class') == 'anniu') {
                    isUse = 1;
                } else {
                    isUse = 0;
                }
                ;
                statusUser(this.id.split('_')[1], isUse)
            });
        }
        ;
    };


    var isUserGroup = function (userGroup) {
        if (userGroup) {
            return userGroup.groupName;
        }
        return '';
    }
    var getUserFromId = function (id) {
        for (var i = 0; i < userList.length; i++) {
            if (id == userList[i].id) {
                return userList[i];
            }
        }
    };


    var showUserEdit = function (type) {
        $.get($components.userQuery, function (re) {
            $('#popPanel').html(re);
            $('#editModal').modal('show');
            if (type == 'edit') {
                $('#EditModalLabel').html('编辑用户');
                $('input[name="userName"]').val(selectUser.userName);
                $('#tel1').attr("disabled","false")
                $('#tel2').css('display','none');
                $('input[name="pname"]').val(selectUser.userGroup.groupName);
            };
            $('#userGroupBtn').click(function () {
                $.get($components.adduserGroup, function (re) {
                    $('#popPanel1').html(re);
                    $('#userGroupModal').modal('show');
                });
            });
            $('#selectWebsiteBtn').click(function () {
                $.get($components.addSelectWebsite, function (re) {
                    $('#popPanel1').html(re);
                    $('#selectWebsiteModal').modal('show');
                });
            });
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editUser();
                    return;
                };
                addUser();
            });
        });
    };
    initialize();
})