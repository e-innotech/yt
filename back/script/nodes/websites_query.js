$(function () {
    pageNum = 1;
    pageSize = 20;

    var siteName = '';

    var websitesList = [];
    var selectWebsites;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getWebsitesList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isUse:$('#isUseSelect').val()};
        if(siteName!=''){
            data.siteName = siteName;
        }
        AjaxFunc($query.websites,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getWebsitesList);
            }else{
                alert(re.msg);
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
    var deleteWebsites = function (id) {
        var data = {id:id};
        AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#confirmModal').modal('hide');
                getWebsitesList();
            };
        });
    };
    var isUseWebsites = function (id,isUse) {
        var data = {id:id,isUse:isUse};
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getWebsitesList();
            }
        });
    };
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
            $('#addWebsitesBtn').show();
            $('#addWebsitesBtn').click(function () {
                showWebsitesEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            siteName = $('#siteNameTxt').val();
            getWebsitesList();
        });
        getWebsitesList();
    };
    var initTable = function(list) {
        websitesList = list;
        $('#websitesT').empty();
        for(var i=0;i<list.length;i++){
            $('#websitesT').append('<tr>' +
            '<td>'+list[i].siteName+'</td>'+
            '<td>'+list[i].domain+'</td>'+
            '<td>'+list[i].route+'</td>'+
            '<td>'+list[i].createDate+'</td>'+
            '<td>'+list[i].createDate+'</td>'+
            '<td><p id="isUseBtn_'+list[i].id+'" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '"><span> </span></p></td>' +
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
            '</tr>');


            $('#isUseBtn_'+list[i].id).click(function () {
                if(ctrl_upate!=''){
                    isUseWebsites(this.id.split('_')[1],($(this).attr('class')=='aniu'?0:1));
                };
            });
            $('#editBtn_'+list[i].id).click(function () {
                selectWebsites = getWebsitesFromId(this.id.split('_')[1]);
                showWebsitesEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteWebsites);
                });
            });
        }

    };
    var getWebsitesFromId = function (id) {
        for(var i=0;i<websitesList.length;i++){
            if(id == websitesList[i].id){
                return websitesList[i];
            }
        }
    };
    var showWebsitesEdit = function (type) {
        $.get($components.websitesEdit,function (re) {
            $('#popPanel').html(re);
            $('#websitesEditModal').modal('show');
            if(type == 'edit'){
                $('#websitesEditModalLabel').html('编辑网站');
            }
        });
    };
    initialize();
})