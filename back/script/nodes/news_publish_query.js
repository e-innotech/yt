$(function () {
    pageNum = 1;
    pageSize = 20;

    var newsPublishList = [];
    var selectNewsPublish;

    var newsTitle = '';
    var websiteName = '';
    var channelName = '';


    var ctrl_offLine = '';
    var ctrl_setHome = '';

    var getNewsPublishList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isline:$('#islineSelect').val(),ishome:$('#ishomeSelect').val()};
        if(newsTitle!=''){
            data.newsTitle = newsTitle;
        }
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
    var homeNewsPublish = function (id,isHome,homeWeight) {
        var data = {id:id,isHome:isHome,homeWeight:homeWeight};
        AjaxFunc($apiUrl+ctrl_setHome,'get',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsPublishList();
            }
        });
    }
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('offLine')!=-1){
                ctrl_offLine = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('setHome')!=-1){
                ctrl_setHome = nodeData.buttons[i].uri;
            };
        };

        $('#searchBtn').click(function () {
            newsTitle = $('#newsTitleTxt').val();
            websiteName = $('#websiteNameTxt').val();
            channelName = $('#channelNameTxt').val();
            getNewsPublishList();
        });



        getNewsPublishList();
    };
    var initTable = function (list) {
        newsPublishList = list;
        $('#newsPublishT').empty();
        for(var i=0;i<list.length;i++){
            $('#newsPublishT').append('<tr>' +
                '<td>'+list[i].news.newsTitle+'</td>'+
                '<td>'+(list[i].news.topImagePath==null?'':'<img src="'+'+list[i].news.topImagePath+'+'">')+'</td>'+
                '<td><a id="content_'+list[i].id+'">查看</a></td>'+
                '<td>'+list[i].websites.siteName+'</td>'+
                '<td>'+list[i].channel.channelName+'</td>'+
                '<td>'+OFFONLINE[list[i].isline]+HOME[list[i].ishome]+'</td>'+
                '<td>'+getCtrl(list[i])+'</td>'+
                '</tr>');

            $('#content_'+list[i].id).click(function () {
                selectNewsPublish = getNewsPublishFromId(this.id.split('_')[1]);
                showNewsContentPreview();
            });
            $('#onLineBtn_'+list[i].id).click(function () {
                offOnLineNewsPublish(this.id.split('_')[1],1);
            });
            $('#offLineBtn_'+list[i].id).click(function () {
                offOnLineNewsPublish(this.id.split('_')[1],0);
            });
            $('#upHomeBtn_'+list[i].id).click(function () {
                selectNewsPublish = getNewsPublishFromId(this.id.split('_')[1]);
                $.get($components.homeCtrl,function (re) {
                    $('#popPanel').html(re);
                    $('#homeCtrlModal').modal('show');
                    $('input[name="siteName"]').val(selectNewsPublish.websites.siteName);
                    $('#saveBtn').click(function () {
                        homeNewsPublish(selectNewsPublish.id,1,$('input[name="homeWeight"]').val());
                        $('#homeCtrlModal').modal('hide');
                    });
                });
            });
            $('#downHomeBtn_'+list[i].id).click(function () {
                homeNewsPublish(this.id.split('_')[1],0,0);
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
        if(ctrl_setHome!=''){
            if(obj.ishome == 0){
                if(obj.news.topImagePath) {
                    re += '<button id="upHomeBtn_' + obj.id + '">上首页</button>';
                }
            }else{
                re += '<button id="downHomeBtn_'+obj.id+'">下首页</button>';
            }
        };
        return re;
    };
    var getNewsPublishFromId = function (id) {
        for(var i=0;i<newsPublishList.length;i++){
            if(id == newsPublishList[i].id){
                return newsPublishList[i];
            }
        }
    };
    var showNewsContentPreview = function () {
        newsContent = selectNewsPublish.news.content;
        $.get($components.newsContentPreview,function (re) {
            $('#popPanel').html(re);
            $('#newsContentPreviewModal').modal('show');
        })
    }
    initialize();
});