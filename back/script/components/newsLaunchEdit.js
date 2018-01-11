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
                    updateSelectLaunch(ids[0],ids[1],this.checked);
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
        for (var i = 0; i < newsLaunchConfig.length; i++) {
            if (wid == newsLaunchConfig[i].websiteId) {
                for (var j = 0; j < newsLaunchConfig[i].channelId.length; j++) {
                    if (cid == newsLaunchConfig[i].channelId[j]) {
                        newsLaunchConfig[i].channelId.splice(j, 1);
                        return;
                    }
                }
                if(bol){

                }
            }else{
                newsLaunchConfig.push({websiteId:wid,channelId:[cid]});
            }
        }
        if(bol) {
            newsLaunchConfig.push({websiteId: wid, channelId: [cid]});
        }
    }
    initialize();
})