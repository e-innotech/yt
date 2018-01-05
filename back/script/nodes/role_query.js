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
        $.ajax({
            type: "get",//请求方式
            url: $query.role,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            data:data,
            success: function (re) {
                if(re.success){
                    initTable(re.data.list);
                    initPage(re.data.total);
                }else{
                    alert(re.msg);
                }
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
        $.ajax({
            type: "post",//请求方式
            url: $apiUrl+ctrl_add,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            contentType:'application/json',
            data:JSON.stringify(data),
            success: function (re) {
                if(re.success){
                    getRoleList();
                    $('#roleEditModal').modal('hide');
                }
                alert(re.msg);

            }
        });

    };
    var editRole = function () {
        var data = $('#roleForm').serializeObject();
        data.resourceIds = resourceListSelectIds;
        data.id = selectRole.id;
        $.ajax({
            type: "put",//请求方式
            url: $apiUrl+ctrl_upate,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            contentType:'application/json',
            data:JSON.stringify(data),
            success: function (re) {
                if(re.success){
                    getRoleList();
                    $('#roleEditModal').modal('hide');
                }
                alert(re.msg);
            }
        });
    };
    var deleteRole = function (id) {
        $.ajax({
            type: "put",//请求方式
            url: $apiUrl+ctrl_delete,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            data:{id:id},
            success: function (re) {
                if(re.success){
                    getRoleList();
                }
                alert(re.msg);
            }
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
                '<td>'+list[i].id+'</td>' +
                '<td>'+list[i].roleName+'</td>'+
                '<td>'+list[i].comment+'</td>'+
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
        }

    };
    var initPage = function (total) {
        if(pageNum>1){
            return;
        }
        $.jqPaginator('#pg', {
            totalCounts:Number(total)==0?1:Number(total),
            pageSize:pageSize,
            visiblePages: 3,
            currentPage: pageNum,
            first: '<li class="first"><a href="javascript:;"><<</a></li>',
            prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
            next: '<li class="next"><a href="javascript:;">下一页</a></li>',
            last: '<li class="last"><a href="javascript:;">>></a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            onPageChange: function (num, type) {
//	            alert(type + '：' + num);
                if(type == 'change'){
                    pageNum = num;
                    getRoleList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
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
                $('input[name="comment"]').val(selectRole.comment);
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