$(function () {
    pageNum = 1;
    pageSize = 15;


    var channelName = '';
    var id = '';
    var remark = '';
    var List = [];
    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(channelName!=''){
            data.channelName = channelName;
        };
        $.ajax({
            type: "get",//请求方式
            url: $query.channel,//请求路径
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
        if($('input[name="channelName"]').val() == ''){
            alert('栏目名不能为空');
            return;
        };
        var data = $('#AddForm').serializeObject();
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
                    getList();
                    $('#addModal').modal('hide');
                }
                alert(re.msg);

            }
        });

    };
    var editRole = function () {
        var data = $('#UpateForm').serializeObject();
        data.resourceIds = resourceListSelectIds;
        data.id = selectRole.id;
        $.ajax({
            type: "post",//请求方式
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
                    getList();
                    $('#upateModal').modal('hide');
                }
                alert(re.msg);
            }
        });
    };
    var deleteRole = function (id) {
        $.ajax({
            type: "get",//请求方式
            url: $apiUrl+ctrl_delete,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            data:{id:id},
            success: function (re) {
                if(re.success){
                    getList();
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
            $('#addBtn').show();
            $('#addBtn').click(function () {
                showRoleAdd('add');
            });
        };
        $('#searchBtn').click(function () {
            channelName = $('#queryText').val();
            getList();
        });
        getList();
    };
    var initTable = function(list) {
        List = list;
        $('#channel').empty();
        for(var i=0;i<list.length;i++){
            $('#channel').append('<tr>' +
            '<td>'+list[i].id+'</td>' +
            '<td>'+list[i].channelName+'</td>'+
            '<td>'+list[i].remark+'</td>'+
            '<td><p class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">修改</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
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
                    getList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    };
    var getRoleFromId = function (id) {
        for(var i=0;i<List.length;i++){
            if(id == List[i].id){
                return List[i];
            }
        }
    };
    var showRoleAdd = function (type) {
        $.get($components.channelQuery,function (re) {
            $('#popPanel').html(re);
            $('#addModal').modal('show');
            $('#addBtn').click(function () {
                if(type == 'edit'){
                    editRole();
                    return;
                };
                addRole();
            });

        });
    };
    var showRoleEdit = function (type) {
        $.get($components.channelQuery,function (re) {
            $('#popPanel').html(re);
            $('#upateModal').modal('show');
            if(type=='edit'){
                $('input[name="channelName"]').val(selectRole.channelName);
                $('input[name="remark"]').val(selectRole.remark);
            };
            $('#upateBtn').click(function () {
                if(type == 'edit'){
                    editRole();
                    return;
                };
                addRole();
            });

        });
    };
    initialize();
})