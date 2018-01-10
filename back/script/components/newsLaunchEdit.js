$(function () {
    pageNum = 1;
    pageSize = 10;

    var siteName = '';

    var websitesList = [];


    var getWebsitesList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isUse:1};
        if(siteName!=''){
            data.siteName = siteName;
        }
        AjaxFunc($query.websites,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pgNewsLaunch',$('#totalPgNewsLaunch'),re.data.total,getWebsitesList);
            }else{
                alert(re.msg);
            }
        });

    };

    var initialize = function () {
        $('#searchNewsLaunchBtn').click(function () {
            siteName = $('#siteNameNewsLaunchTxt').val();
            getWebsitesList();
        });
        getWebsitesList();
    };
    var initTable = function(list) {
        websitesList = list;
        $('#newsLaunchT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#newsLaunchT').append('<tr>' +
                '<td>'+list[i].siteName+'</td>'+
                '<td>'+getChannels(list[i].id,list[i].channels)+'</td>'+
                '</tr>');
        };
    }
    var getChannels = function (id,list) {
        var re = '';
        for(var i=0;i<list.length;i++){
            re += '<label style="margin-left: 5px;margin-top: 5px;"><input type="checkbox" id="wcCB_'+id+'_'+list[i].id+'">'+list[i].channelName+'</label>';
        }
        return re;
    };
    initialize();
})