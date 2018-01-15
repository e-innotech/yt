$(function () {
    pageNum = 1;
    pageSize = 20;

    var roleName = ''
    var roleList = [];
    var selectRole;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getRoleList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(roleName!=''){
            data.roleName = roleName;
        };
        AjaxFunc($query.role,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getRoleList);
            }else{
                alert(re.msg);
            }
        });
    };
    var addRole = function () {
        if($('input[name="roleName"]').val() == ''){
            alert('角色名不能为空');
            return;
        };
        var data = $('#roleForm').serializeObject();
        data.resourceIds = resourceListSelectIds;

        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            if(re.success){
                getRoleList();
                $('#roleEditModal').modal('hide');
            }
            alert(re.msg);
        });
    };
    var editRole = function () {
        if($('input[name="roleName"]').val() == ''){
            alert('角色名不能为空');
            return;
        };
        var data = $('#roleForm').serializeObject();
        data.resourceIds = resourceListSelectIds;
        data.id = selectRole.id;
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            if(re.success){
                getRoleList();
                $('#roleEditModal').modal('hide');
            }
            alert(re.msg);
        });
    };
    var deleteRole = function (id) {
        var data = {id:id};
        AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
            if(re.success){
                getRoleList();
            }
            alert(re.msg);
        });
    }
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('add')!=-1){
                ctrl_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('delete')!=-1){
                ctrl_delete = nodeData.buttons[i].uri;
            };
        }
        if(ctrl_add != '') {
            $('#addRoleBtn').show();
            $('#addRoleBtn').click(function () {
                showRoleEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            roleName = $('#roleNameTxt').val();
            getRoleList();
        });
        getRoleList();
    };
    var initTable = function(list) {
        roleList = list;
        $('#roleT').empty();
        for(var i=0;i<list.length;i++){
            $('#roleT').append('<tr>' +
                '<td>'+list[i].roleName+'</td>'+
                '<td>'+list[i].remark+'</td>'+
                '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
                '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectRole = getRoleFromId(this.id.split('_')[1]);
                showRoleEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteRole);
                });
                // deleteRole(this.id.split('_')[1]);
            });
        };

    };
    var getRoleFromId = function (id) {
        for(var i=0;i<roleList.length;i++){
            if(id == roleList[i].id){
                return roleList[i];
            }
        }
    };
    var getResourceNames = function () {
        var re = '无';
        var names = [];
        for(var i=0;i<selectRole.resource.length;i++){
            names.push(selectRole.resource[i].resourceName);
        }
        if(names.length>0){
            re = names.toString();
        }
        return re;
    };
    var getResourceIds = function () {
        var ids = [];
        for(var i=0;i<selectRole.resource.length;i++){
            ids.push(selectRole.resource[i].id);
        }
        return ids;
    };
    var showRoleEdit = function (type) {
        $.get($components.roleEdit,function (re) {
            $('#popPanel').html(re);
            $('#roleEditModal').modal('show');
            if(type=='edit'){
                $('#roleEditModalLabel').html('编辑资源');
                $('input[name="roleName"]').val(selectRole.roleName);
                $('input[name="remark"]').val(selectRole.remark);
                $('textarea[name="resourceNames"]').val(getResourceNames());
                resourceListSelectIds = getResourceIds();
            };
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editRole();
                    return;
                };
                addRole();
            });
            $('textarea[name="resourceNames"]').click(function () {
                $.get($components.resourceList,function (re) {
                    resourceListType = 'connect';
                    $('#popPanel1').html(re);
                    $('#resourceListModal').modal('show');

                });
            });
        });
    };
    initialize();
})