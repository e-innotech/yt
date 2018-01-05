$(function () {

    var resourceList = [];
    var selectResource;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';


    var getResourceList = function(){
        $.ajax({
            type: "get",//请求方式
            url: $query.resource,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            success: function (re) {
                if(re.success){
                    initResource(re.data);
                }else{
                    alert(re.msg);
                }
            }
        });

    };
    var addResource = function () {
        if($('input[name="resourceName"]').val() == ''){
            alert('资源名不能为空');
            return;
        };
        var data = $('#resourceForm').serializeObject();
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
    var editResource = function () {//编辑
        var data = $('#resourceForm').serializeObject();
        data.id = selectResource.id;
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
                    getResourceList();
                    $('#resourceEditModal').modal('hide');
                }
                alert(re.msg);
            }
        });
    };
    var deleteResource = function (id) {
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
                    getResourceList();
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
            $('#addResourceBtn').show();
            $('#addResourceBtn').click(function () {
                showResoureceEdit('add');
            });
        };
        getResourceList();
    };
    var initResource = function(list){
        resourceList = list;
        $('#resourceBox').empty();
        for(var i=0;i<resourceList.length;i++){
            $('#resourceBox').append('<div style="border: 1px solid #000000"><div style="padding-left: 30px;padding-top: 5px;padding-bottom: 5px;" id="level1_'+resourceList[i].id+'"></div></div>');
            renderButton($('#level1_'+resourceList[i].id),resourceList[i].id,resourceList[i].resourceName,false);
            for(var j=0;j<resourceList[i].level2.length;j++){
                $('#level1_'+resourceList[i].id).append('<div><div style="padding-left: 30px;padding-top: 5px;padding-bottom: 5px;" id="level2_'+resourceList[i].level2[j].id+'"></div></div>');
                renderButton($('#level2_'+resourceList[i].level2[j].id),resourceList[i].level2[j].id,resourceList[i].level2[j].resourceName,false);
                for(var k=0;k<resourceList[i].level2[j].level3.length;k++){
                    renderButton($('#level2_'+resourceList[i].level2[j].id),resourceList[i].level2[j].level3[k].id,resourceList[i].level2[j].level3[k].resourceName,true);
                }
            }
        }
    };
    var renderButton = function (obj,id,name,bol) {
        var btn = '<button class="btn btn-default" style="margin-left: 10px;" id="edit_'+id+'">'+name+'</button><button class="btn btn-default" id="delete_'+id+'">X</button>';
        if(bol){
            obj.append(btn);
        }else {
            obj.before(btn);
        }
        $('#edit_'+id).click(function () {
            selectResource = getResourceFromId(id);
            showResoureceEdit('edit');
        });
        $('#delete_'+id).click(function () {
            $.get($components.confirm,function (re) {
                $('#popPanel1').html(re);
                $('#confirmModal').modal('show');
                confirm.initialize(id,deleteResource);
            });
            // deleteResource(id);
        });
    };

    var getResourceFromId = function (id) {
        for(var i=0;i<resourceList.length;i++){
            if(id == resourceList[i].id){
                return resourceList[i];
            }
            for(var j=0;j<resourceList[i].level2.length;j++){
                if(id == resourceList[i].level2[j].id){
                    return resourceList[i].level2[j];
                }
                for(var k=0;k<resourceList[i].level2[j].level3.length;k++){
                    if(id == resourceList[i].level2[j].level3[k].id){
                        return resourceList[i].level2[j].level3[k];
                    }
                }
            }
        }
    };
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
            $('input[name="pname"]').click(function () {
                $.get($components.resourceList,function (re) {
                    resourceListType = 'parent';
                    $('#popPanel1').html(re);
                    $('#resourceListModal').modal('show');

                });
            });
        });
    };
    initialize();
})