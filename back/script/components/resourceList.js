$(function () {

    var resourceList = [];

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
        }
    };
    var renderButton = function (obj,id,name,bol) {
        var btn = '<button class="btn btn-default" style="margin-left: 10px;" id="edit_'+id+'">'+name+'</button>';
        if(bol){
            obj.append(btn);
        }else {
            obj.before(btn);
        }
        $('#edit_'+id).click(function () {
            selectResource = getResourceFromId(id);
        });
    };
    var initialize = function () {
        if(resourceListType != 'parent'){
            $('#resourceListFooter').show();
            $('#resourceListBtn').click(function () {

            });
        }
        getResourceList();
    };

    initialize();
})