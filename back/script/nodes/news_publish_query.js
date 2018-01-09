$(function () {
    pageNum = 1;
    pageSize = 20;

    var newsPublishList = [];

    var websiteName = '';
    var channelName = '';



    var getNewsPublishList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isline:$('#islineSelect').val(),ishome:$('#ishomeSelect').val()};
        if(websiteName!=''){
            data.websiteName = websiteName;
        }
        if(channelName!=''){
            data.channelName = channelName;
        }
        AjaxFunc($query.newsPublish,'get',data,function (re) {
            if(re.success){
               initTable(re.data.list);
               initPage('pg',$('#totalPg'),re.data.total,getNewsPublishList);
            }else {
                alert(re.msg);
            }
        });
    };
    var initialize = function () {
        getNewsPublishList();
    };
    var initTable = function (list) {
        newsPublishList = list;
        $('#newsPublishT').empty();
        for(var i=0;i<list.length;i++){
            $('#newsPublishT').append('<tr>' +
                '<td>'+list[i].news.newsTitle+'</td>'+
                '<td>'+list[i].news.content+'</td>'+
                '<td>'+list[i].websites.siteName+'</td>'+
                '<td>'+list[i].channel.channelName+'</td>'+
                '<td>'+OFFONLINE[list[i].isline]+HOME[list[i].ishome]+'</td>'+
                '</tr>');
        };
    };
    initialize();
});