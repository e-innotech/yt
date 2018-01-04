$(function () {

    var resourceName = '';
    var resourceList = [];
    var selectResource;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    const RW = ['只读','读写'];
    const MENU = ['否','是'];

    var getResourceList = function(){
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(resourceName!=''){
            data.resourceName = resourceName;
        };
        $.ajax({
            type: "get",//请求方式
            url: $apiUrl+nodeData.uri,//请求路径
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
    var addResource = function () {
        var data = $('#resourceForm').serializeArray();
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
                    getResourceList();
                    $('#resourceEditModal').modal('hide');
                }
                alert(re.msg);

            }
        });

    };
    var editResource = function () {
        var data = $('#resourceForm').serializeArray();
    };
    var deleteResource = function (id) {

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
            $('#addResourceBtn').show();
            $('#addResourceBtn').click(function () {
                showResoureceEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            resourceName = $('#resourceNameTxt').val();
            getResourceList();
        });
        getResourceList();
    };
    var initTable = function(list) {
        resourceList = list;
        $('#resourceT').empty();
        for(var i=0;i<list.length;i++){
            $('#resourceT').append('<tr>' +
                '<td>'+list[i].id+'</td>' +
                '<td>'+list[i].resourceName+'</td>'+
                '<td>'+list[i].uri+'</td>'+
                '<td>'+RW[list[i].rw]+'</td>'+
                '<td>'+MENU[list[i].isMenu]+'</td>'+
                '<td>'+list[i].pname+'</td>'+
                '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
                '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectResource = getResourceFromId(this.id.split('_')[1]);
                showResoureceEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {

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
                    getResourceList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    };
    var getResourceFromId = function (id) {
        for(var i=0;i<resourceList.length;i++){
            if(id == resourceList[i].id){
                return resourceList[i];
            }
        }
    }
    var showResoureceEdit = function (type) {
        $.get($components.resourceEdit,function (re) {
            $('#popPanel').html(re);
            $('#resourceEditModal').modal('show');
            if(type=='edit'){
                $('#resourceEditModalLabel').html('编辑资源');
                $('input[name="resourceName"]').val(selectResource.resourceName);
                $('input[name="uri"]').val(selectResource.uri);
                $('input[name="rw"]').val(RW[selectResource.rw]);
                $('input[name="isMenu"]').val(MENU[selectResource.isMenu]);
                $('input[name="pname"]').val(selectResource.pname);
                $('input[name="parentId"]').val(selectResource.parentId);
            };
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editResource();
                    return;
                };
                addResource();
            });
        });
    };
    initialize();
})