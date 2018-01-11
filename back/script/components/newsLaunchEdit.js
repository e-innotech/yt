$(function () {
    pageNum = 1;
    pageSize = 10;

    var siteName = '';

    var websitesList = [];

    var newsLaunchConfig = [];

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
        $('#saveBtn').click(function () {

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
            for(var j=0;j<list[i].channels.length;j++){
                $('#wcCB_'+list[i].id+'_'+list[i].channels[j].id).change(function () {
                    var ids = this.id.split('_');
                    updateSelectLaunch(ids[1],ids[2],this.checked);
                });
            }
        };
    }
    var getChannels = function (id,list) {
        var re = '';
        for(var i=0;i<list.length;i++){
            re += '<label style="margin-left: 5px;margin-top: 5px;"><input type="checkbox" id="wcCB_'+id+'_'+list[i].id+'">'+list[i].channelName+'</label>';
        }
        return re;
    };
    var updateSelectLaunch = function (wid,cid,bol) {
        console.log(wid,cid,bol);
        if(bol){
            for (var i = 0; i < newsLaunchConfig.length; i++) {
                if (wid == newsLaunchConfig[i].websiteId && jQuery.inArray(cid,newsLaunchConfig[i].channelId) == -1) {
                    newsLaunchConfig[i].channelId.push(cid);
                    return;
                }
            }
            newsLaunchConfig.push({websiteId: wid, channelId: [cid]});
        }else{
            for (var j = 0; j < newsLaunchConfig.length; j++) {
                var index = jQuery.inArray(cid,newsLaunchConfig[j].channelId);
                if(index!=-1){
                    newsLaunchConfig[j].channelId.splice(index,1);
                    return;
                }
            }
        }
        console.log('newsLaunchConfig=='+JSON.stringify(newsLaunchConfig));
    }
    initialize();
})