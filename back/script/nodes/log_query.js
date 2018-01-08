
$(function () {
    pageNum = 1;
    pageSize = 15;

    var userName = '';
    var id = '';
    var resourceName = '';
    var action = '';
    var roleList = [];
    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(userName!=''){
            data.name = userName;
        };
        $.ajax({
            type: "get",//请求方式
            url: $query.log,//请求路径
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
    var addBtn = function () {
        if($('input[name="siteName"]').val() == ''){
            alert('网站名不能为空');
            return;
        };
        var data = $('#websitesAddForm').serializeObject();
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
                    $('#websites_query_add').modal('hide');
                }
                alert(re.msg);

            }
        });

    };
    var editBtn = function () {
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
    var deleteBtn = function (id) {
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
            $('#addUserBtn').show();
            $('#addUserBtn').click(function () {
                showAdd('add');
            });
        };
        $('#searchBtn').click(function () {
            id = $('#idVue').val();
            getList();
        });
        getList();
    };
    var initTable = function(list) {
        roleList = list;
        $('#log_query').empty();
        for(var i=0;i<list.length;i++){
            $('#log_query').append('<tr>' +
            '<td>'+list[i].usersId+'</td>'+
            '<td>'+list[i].usersId+'</td>'+
            '<td>'+list[i].action+'</td>'+
            '<td>'+list[i].createdate+'</td>'+
            '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectRole = getId(this.id.split('_')[1]);
                showEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteBtn);
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
    var getId = function (id) {
        for(var i=0;i<roleList.length;i++){
            if(id == roleList[i].id){
                return roleList[i];
            }
        }
    };
    var showAdd = function (type) {
        $.get($components.configQuery,function (re) {
            $('#popPanel').html(re);
            $('#addModal').modal('show');
            $('#addBtn').click(function () {
                if(type == 'edit'){
                    editBtn();
                    return;
                };
                addBtn();
            });

        });
    };
    var showEdit = function (type) {
        $.get($components.configQuery,function (re) {
            $('#popPanel').html(re);
            $('#upateModal').modal('show');
            if(type=='edit'){
                $('input[name="name"]').val(selectRole.name);
                $('input[name="value"]').val(selectRole.value);
            };
            $('#upateBtn').click(function () {
                if(type == 'edit'){
                    editBtn();
                    return;
                };
                addBtn();
            });

        });
    };
    initialize();
})