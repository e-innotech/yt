$(function () {
    pageNum = 1;
    pageSize = 20;

    var name = ''
    var value = ''
    var userList = [];
    var selectUser;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getUserList = function () {
        var data = {pageNum: pageNum, pageSize: pageSize};
        if (name != '') {
            data.name = name;
        };
        AjaxFunc($query.config, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                initPage(re.data.total);
            } else {
                alert(re.msg);
            }
        });
    };
    var addUser = function () {
        if($('input[name="groupName"]').val()==''){
            alert('参数不sf能为空');
            return;
        }
        //var data={rolesId:selectUser.rolesId};
        //console.log(11111,selectUser.rolesId)
        var data = $('#roleForm').serializeObject();
        console.log(data)
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            if(re.success){
                $('#editModal').modal('hide');
                getUserList();
            }
            alert(re.msg);
        });
    };




    var editUser = function () {
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
    var deleteRole = function (id) {
        var data = {id:id};
        AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
            if(re.success){
                getUserList();
            }
            alert(re.msg);
        });
    }
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
            if (nodeData.buttons[i].uri.indexOf('delete') != -1) {
                ctrl_delete = nodeData.buttons[i].uri;
            };

        }if (ctrl_add != '') {
            $('#addUserBtn').show();
            $('#addUserBtn').click(function () {
                showUserEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            value = $('#valueTxt').val();
            name = $('#nameTxt').val();
            getUserList();
        });
        getUserList();
    };
    var initTable = function (list) {
        userList = list;
        $('#userT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#userT').append('<tr>' +
            '<td>' + list[i].name + '</td>' +
            '<td>' + list[i].value + '</td>' +
            '<td>' + list[i].createDate + '</td>' +
            '<td><p id="statusBtn_' + list[i].id + '" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '"><span></span></p></td>' +
            '<td>' + (ctrl_upate != '' ? '<button id="editBtn_' + list[i].id + '">编辑</button>' : '') + (ctrl_delete != '' ? '<button id="deleteBtn_'+list[i].id+'">删除</button>' : '') +'</td>' +
            '</tr>');

            $('#editBtn_' + list[i].id).click(function () {
                selectUser = getUserFromId(this.id.split('_')[1]);
                showUserEdit('edit');
            });
            $('#deleteBtn_' + list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteRole);
                });
            });
            $('#statusBtn_' + list[i].id).click(function () {
                //console.log($(this).attr('class'));
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
    var initPage = function (total) {
        if (pageNum > 1) {
            return;
        }
        $.jqPaginator('#pg', {
            totalCounts: Number(total) == 0 ? 1 : Number(total),
            pageSize: pageSize,
            visiblePages: 3,
            currentPage: pageNum,
            first: '<li class="first"><a href="javascript:;"><<</a></li>',
            prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
            next: '<li class="next"><a href="javascript:;">下一页</a></li>',
            last: '<li class="last"><a href="javascript:;">>></a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            onPageChange: function (num, type) {
//	            alert(type + '：' + num);
                if (type == 'change') {
                    pageNum = num;
                    getUserList();
                }
                $('#totalPg').text('当前第' + pageNum + '页 共' + Math.ceil(total / pageSize) + '页（每页' + pageSize + '条 共：' + total + '条）');
            }
        });
    };
    var getUserFromId = function (id) {
        for (var i = 0; i < userList.length; i++) {
            if (id == userList[i].id) {
                return userList[i];
            }
        }
    };


    var showUserEdit = function (type) {
        $.get($components.configQuery, function (re) {
            $('#popPanel').html(re);
            $('#editModal').modal('show');
            if (type == 'edit') {
                $('#EditModalLabel').html('编辑配置');
                $('input[name="name"]').val(selectUser.name);
                $('input[name="value"]').val(selectUser.value);
                //$('input[name="roleName"]').val(selectUser.roles.roleName);
            };
            $('#userGroupBtn').click(function () {
                $.get($components.addRole, function (re) {
                    $('#popPanel1').html(re);
                    $('#userGroupModal').modal('show');
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