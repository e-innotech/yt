$(function () {

    var resourceList = [];
    var selectResourceList = [];


    var getResourceList = function(){
        AjaxFunc($query.resource,'get',null,function (re) {
            if(re.success){
                initResource(re.data);
            }else{
                alert(re.msg);
            }
        });
    };
    var initResource = function(list){
        resourceList = list;
        $('#resourceListBox').empty();
        for(var i=0;i<resourceList.length;i++){
            $('#resourceListBox').append('<div style="border: 1px solid #000000"><div style="padding-left: 30px;padding-top: 5px;padding-bottom: 5px;" id="levelList1_'+resourceList[i].id+'"></div></div>');
            renderButton($('#levelList1_'+resourceList[i].id),resourceList[i].id,resourceList[i].resourceName,false);
            for(var j=0;j<resourceList[i].level2.length;j++){
                if(resourceListType == 'parent'){
                    renderButton($('#levelList1_'+resourceList[i].id),resourceList[i].level2[j].id,resourceList[i].level2[j].resourceName,true);
                }else{
                    $('#levelList1_'+resourceList[i].id).append('<div><div style="padding-left: 30px;padding-top: 5px;padding-bottom: 5px;" id="levelList2_'+resourceList[i].level2[j].id+'"></div></div>');
                    renderButton($('#levelList2_'+resourceList[i].level2[j].id),resourceList[i].level2[j].id,resourceList[i].level2[j].resourceName,false);
                    for (var k = 0; k < resourceList[i].level2[j].level3.length; k++) {
                        renderButton($('#levelList2_' + resourceList[i].level2[j].id), resourceList[i].level2[j].level3[k].id, resourceList[i].level2[j].level3[k].resourceName, true);
                    }
                }
            }
        };
    };
    var renderButton = function (obj,id,name,bol) {
        if(resourceListType == 'parent') {
            var btn = '<button class="btn btn-default" style="margin-left: 10px;" id="editList_' + id + '">' + name + '</button>';
        }else{
            var btn = '<label class="btn btn-default" style="margin-left: 10px;" ><input type="checkbox" id="check_'+id+'" >' + name + '</label>';
        }
        if(bol){
            obj.append(btn);
        }else {
            obj.before(btn);
        }
        for(var i = 0;i<resourceListSelectIds.length;i++){
            if(id == resourceListSelectIds[i]){
                $('#check_'+id).prop('checked','checked');
            }
        };
        $('#editList_'+id).click(function () {
            var resource = getResourceFromId(id);
            $('input[name="pname"]').val(resource.resourceName);
            $('input[name="parentId"]').val(id);
            $('#resourceListModal').modal('hide');
        });
        $('#check_'+id).change(function () {
            updateSelectIds(id);
        });
    };
    var updateSelectIds = function(id){
        for(var i = 0;i<selectResourceList.length;i++){
            if(id == selectResourceList[i]){
                selectResourceList.splice(i,1);
                return;
            }
        };
        selectResourceList.push(id);
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
    var getResourceNames = function () {
        var re = '无';
        var names = [];
        for(var i=0;i<resourceListSelectIds.length;i++){
            names.push(getResourceFromId(resourceListSelectIds[i]).resourceName);
        }
        if(names.length>0){
            re = names.toString();
        }
        return re;
    };
    var initialize = function () {
        if(resourceListType != 'parent'){
            $('#resourceListFooter').show();
            selectResourceList = resourceListSelectIds.slice();
            $('#resourceListBtn').click(function () {
                resourceListSelectIds = selectResourceList.slice();
                $('textarea[name="resourceNames"]').val(getResourceNames());
                $('#resourceListModal').modal('hide');
            });
        };
        getResourceList();
    };

    initialize();
})