$(function () {
    pageNum = 1;
    pageSize = 15;

    var siteName = '';
    var id = '';
    var isUse = '';
    var route = '';
    var roleList = [];
    var selectRole;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getWebsitesQueryList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(id!=''){
            data.id = id;
        };
        $.ajax({
            type: "get",//请求方式
            url: $query.websites,//请求路径
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
        if($('input[name="siteName"]').val() == ''){
            alert('网站名不能为空');
            return;
        };
        var data = $('#websitesForm').serializeObject();
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
                    getWebsitesQueryList();
                    $('#websites_query_add').modal('hide');
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
                    getWebsitesQueryList();
                    $('#websites_query_add').modal('hide');
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
                    getWebsitesQueryList();
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
            $('#addWebsitesQueryBtn').show();
            $('#addWebsitesQueryBtn').click(function () {
                showRoleEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            id = $('#idVue').val();
            getWebsitesQueryList();
        });
        getWebsitesQueryList();
    };
    var initTable = function(list) {
        roleList = list;
        $('#websites_query').empty();
        for(var i=0;i<list.length;i++){
            $('#websites_query').append('<tr>' +
            '<td>'+list[i].id+'</td>' +
            '<td>'+list[i].siteName+'</td>'+
            '<td>'+list[i].route+'</td>'+
            '<td>'+list[i].createDate+'</td>'+
            '<td><p class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +
            '<td>'+list[i].templteConfig+'</td>'+
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">修改</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
            '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectRole = getRoleFromId(this.id.split('_')[1]);
                showRoleEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.websiteQuery,function (re) {
                    $('#popPanel1').html(re);
                    $('#websites_query_add').modal('show');
                    websiteQuery.initialize(id,deleteRole);
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
                    getWebsitesQueryList();
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
        $.get($components.websiteQuery,function (re) {
            $('#popPanel').html(re);
            $('#websites_query_add').modal('show');

            $('#websites_query_addBtn').click(function () {
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