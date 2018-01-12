$(function () {
    pageNum = 1;
    pageSize = 10;

    var siteName = ''

    var webTemplateList = [];
    var selectWebTemplate;


    var getWebTemplateList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,templateType:$('#templateTypeSelect').val()};
        if(siteName!=''){
            data.siteName = siteName;
        };
        AjaxFunc($query.websitesTemplate,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pgWeb',$('#totalPgWeb'),re.data.total,getWebTemplateList);
            }else{
                alert(re.msg);
            }
        });
    };
    var initialize =function () {
        $('#searchWebBtn').click(function () {
            siteName = $('#siteNameWebTxt').val();
            getWebTemplateList();
        });
        getWebTemplateList();
    };
    var initTable = function (list) {
        webTemplateList = list;
        $('#webTemplateListT').empty();
        for(var i=0;i<list.length;i++){
            $('#webTemplateListT').append('<tr id="wt_'+list[i].id+'">' +
                '<td>'+list[i].websites.siteName+'</td>'+
                '<td>'+TEMPLATE[list[i].templateType]+'</td>'+
                '</tr>');
            $('#wt_'+list[i].id).click(function () {
                selectWebTemplate = getWebTemplateFromId(this.id.split('_')[1]);
                $('input[name="webTemplateId"]').val(selectWebTemplate.id);
                $('#webTemplateInfo').val(selectWebTemplate.websites.siteName+':'+TEMPLATE[selectWebTemplate.templateType]);

                $('#webTemplateListModal').modal('hide');
            });

        }
    };
    var getWebTemplateFromId = function (id) {
        for(var i=0;i<webTemplateList.length;i++){
            if(id == webTemplateList[i].id){
                return webTemplateList[i];
            }
        }
    };
    initialize();
});