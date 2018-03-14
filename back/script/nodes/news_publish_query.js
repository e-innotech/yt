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
    var ctrl_delete = '';

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
    var deleteNews = function () {
        var ids= new Array();
        ids.push(this.id);
        var data =ids;
        AjaxFunc($apiUrl+ctrl_delete,'post',data,function (re) {
            if(re.success){
                getNewsPublishList();
            }
            alert(re.msg);
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
            if(nodeData.buttons[i].uri.indexOf('delete')!=-1){
                ctrl_delete = nodeData.buttons[i].uri;
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
        $('#deleteBtn').empty();
        $('#deleteBtn').append(''+(ctrl_delete!=''?'<button id="deleteSubmit" class="btn btn-primary" type="submit">批量删除</button>':'')+'');
        $('#newsPublishT').empty();
        for(var i=0;i<list.length;i++){
            $('#newsPublishT').append('<tr>' +
                '<td style="text-align: center">'+ '<input type="checkbox" name="subBox" value="'+list[i].id+'">'+'</td>' +
                '<td>'+list[i].news.newsTitle+'</td>'+
                '<td>'+(list[i].news.topImagePath==''?'':'<img src="'+list[i].news.topImagePath+'" width="200">')+'</td>' +
                '<td><a target="_blank" href="'+list[i].staticUrl+'">查看文章</a></td>'+
                '<td><a id="content_'+list[i].id+'">查看详情</a></td>'+
                '<td>'+list[i].websites.siteName+'</td>'+
                '<td>'+list[i].channel.channelName+'</td>'+
                '<td>'+list[i].publishUser+'</td>'+
                '<td>'+OFFONLINE[list[i].isline]+HOME[list[i].ishome]+'</td>'+
                '<td style="text-align: center;">'+getCtrl(list[i])+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
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
                        homeNewsPublish(selectNewsPublish.id,1,$('select[name="homeWeight"]').val());
                        $('#homeCtrlModal').modal('hide');
                    });
                    //option='<option value='+selectNewsPublish.websites.siteName+'>'+selectNewsPublish.websites.siteName+'</ option>';
                    //$("#homeWeightSiteName").append(option);
                });
            });
            $('#downHomeBtn_'+list[i].id).click(function () {
                homeNewsPublish(this.id.split('_')[1],0,0);
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteNews);
                });
            });



        };
        var deleteSubmit = function(){
            var ids= new Array();
            var checked=$("input[type='checkbox']:checked");
            checked.each(function () {
                ids.push($(this).val());
            });
            var data = ids;
            AjaxFunc($apiUrl+ctrl_delete,'post',data,function (re) {
                if(re.success){
                    getNewsPublishList();
                }
                alert(re.msg);
            });
        }
        $('#deleteSubmit').click(function () {
            var ids= new Array();
            var checked=$("input[type='checkbox']:checked");
            checked.each(function () {
                ids.push(checked.val());
            });
            $.get($components.confirm,function (re) {
                $('#popPanel1').html(re);
                $('#confirmModal').modal('show');
                confirm.initialize(ids,deleteSubmit);
            });

        });
    };




    var getCtrl = function (obj) {
        var re = '';
        //if(ctrl_offLine!=''){
        //    if(obj.isline == 1){
        //        re += '<button id="offLineBtn_'+obj.id+'">↓</button>';
        //    }else{
        //        re += '<button id="onLineBtn_'+obj.id+'">↑</button>';
        //    };
        //};
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