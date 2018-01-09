$(function () {
    pageNum = 1;
    pageSize = 15;

    var siteName = '';
    var id = '';
    var route = '';
    var roleList = [];
    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getList = function () {
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
                    initPage('pg',$('#totalPg'),re.data.total,getList);
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
        var data = $('#websitesUpateForm').serializeObject();
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
                    $('#websites_query_upate').modal('hide');
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
            $('#addWebsitesQueryBtn').show();
            $('#addWebsitesQueryBtn').click(function () {
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
        $('#websites_query').empty();
        for(var i=0;i<list.length;i++){
            $('#websites_query').append('<tr>' +
            '<td>'+list[i].siteName+'</td>'+
            '<td>'+list[i].route+'</td>'+
            '<td>'+list[i].createDate+'</td>'+
            '<td><p class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +
            '<td>'+list[i].templteConfig+'</td>'+
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
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
    var getId = function (id) {
        for(var i=0;i<roleList.length;i++){
            if(id == roleList[i].id){
                return roleList[i];
            }
        }
    };
    var showAdd = function (type) {
        $.get($components.websiteQuery,function (re) {
            $('#popPanel').html(re);
            $('#websites_query_add').modal('show');
            $('#websites_query_addBtn').click(function () {
                if(type == 'edit'){
                    editBtn();
                    return;
                };
                addBtn();
            });

        });
    };
    var showEdit = function (type) {
        $.get($components.websiteQuery,function (re) {
            $('#popPanel').html(re);
            $('#websites_query_upate').modal('show');
            if(type=='edit'){
                $('input[name="siteName"]').val(selectRole.siteName);
                $('input[name="route"]').val(selectRole.route);
            };
            $('#websites_query_upateBtn').click(function () {
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