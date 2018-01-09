$(function () {
    pageNum = 1;
    pageSize = 20;

    var newsPublishList = [];

    var websiteName = '';
    var channelName = '';


    var ctrl_offLine = '';
    var ctrl_home = '';

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
    var offOnLineNewsPublish = function (id,lineStatus) {
        var data = {id:id,lineStatus:lineStatus};
        AjaxFunc($apiUrl+ctrl_offLine,'get',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsPublishList();
            }
        })
    };
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('offLine')!=-1){
                ctrl_offLine = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('home')!=-1){
                ctrl_home = nodeData.buttons[i].uri;
            };
        }
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
                '<td>'+getCtrl(list[i])+'</td>'+
                '</tr>');

            $('#onLineBtn_'+list[i].id).click(function () {
                offOnLineNewsPublish(this.id.split('_')[1],1);
            });
            $('#offLineBtn_'+list[i].id).click(function () {
                offOnLineNewsPublish(this.id.split('_')[1],0);
            });
            $('#honeBtn_'+list[i].id).click(function () {

            });
        };
    };
    var getCtrl = function (obj) {
        var re = '';
        if(ctrl_offLine!=''){
            if(obj.isline == 1){
                re += '<button id="offLineBtn_'+obj.id+'">↓</button>';
            }else{
                re += '<button id="onLineBtn_'+obj.id+'">↑</button>';
            };
        };
        if(ctrl_home!=''){
            if(obj.ishome == 0){
                re += '<button id="honeBtn_'+obj.id+'">置首页</button>';
            }
        };
        return re;
    };
    initialize();
});