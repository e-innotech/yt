$(function () {
    pageNum = 1;
    pageSize = 20;


    var newsTitle = '';
    var startDate = '';
    var endDate = '';
    var source = '';

    var newsList = [];
    var selectNews;
    var ctrl_add = '';
    var ctrl_word2Add = '';
    var ctrl_upate = '';
    var ctrl_launch_add = '';


    pageNums = 1;
    pageSizes = 0;

    var siteName = '';

    var websitesList = [];

    var newsLaunchConfig = [];


    var getNewsList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(newsTitle!=''){
            data.newsTitle = newsTitle;
        };
        if(source!=''){
            data.source = source;
        };
        if(startDate!='' && endDate!=''){
            data.startDate = startDate;
            data.endDate = endDate;
        };
        AjaxFunc($query.news,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getNewsList);
            }else{
                alert(re.msg);
            }
        });
    };
    var addNews = function () {
        var data = $('#newsForm').serializeObject();
        data.content = newsContent;
        if(data.newsTitle == ''){
            alert('标题不能为空');
            return;
        }
        if(data.content == ''){
            alert('内容不能为空');
            return;
        }
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#newsEditModal').modal('hide');
                getNewsList();
            }
        });
    };
    var wordAddNews = function () {
        var data = $("#newsWordForm");
            var options = {
                url:$apiUrl+ctrl_word2Add, //上传文件的路径
                type:'post',
                xhrFields: {
                    withCredentials: true
                },
                success:function(){
                    $('#newsEditModal').modal('hide');
                    getNewsList();
                }
            };
            data.ajaxSubmit(options);
    };
    var editNews = function () {
        var data = $('#newsForm').serializeObject();
        data.content = newsContent;
        data.id = selectNews.id;
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#newsEditModal').modal('hide');
                getNewsList();
            }
        });
    };

    var addNewsLaunch = function () {
        var data = {newsId:selectNews.id,newsLaunchConfig:$('#newsLaunchConfig').val()};
        AjaxFunc($apiUrl+ctrl_launch_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsList();
                $('#newsLaunchEditModal').modal('hide');
                $('#newsLaunchConfig').val('');
            }
        })
    }

    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('/news/add')!=-1){
                ctrl_add = nodeData.buttons[i].uri;
            }
            if(nodeData.buttons[i].uri.indexOf('/news/update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            }
            if(nodeData.buttons[i].uri.indexOf('/news/launch/add')!=-1){
                ctrl_launch_add = nodeData.buttons[i].uri;
            }
            if(nodeData.buttons[i].uri.indexOf('/news/word2Add')!=-1){
                ctrl_word2Add = nodeData.buttons[i].uri;
            }

        };
        if(ctrl_add != '') {
            $('#addNewsBtn').show();
            $('#addNewsBtn').click(function () {
                showNewsEdit('add');
            });
        };


        $('#searchBtn').click(function () {
            newsTitle = $('#newsTitleTxt').val();
            source = $('#sourceTxt').val();
            startDate = $('#startDate').val();
            endDate = $('#endDate').val();
            getNewsList();
        });
        getNewsList();
    };


    var getNewsFromId = function (id) {
        for(var i=0;i<newsList.length;i++){
            if(id == newsList[i].id){
                return newsList[i];
            }
        }
    }
    var initTable = function(list) {
        newsList = list;
        $('#newsT').empty();
        for(var i=0;i<list.length;i++){
            $('#newsT').append('<tr>' +
                '<td>'+list[i].newsTitle+'</td>' +
                '<td>'+list[i].source+'</td>'+
                '<td><a id="content_'+list[i].id+'">查看</a></td>'+
                '<td>'+list[i].submitUserName+'</td>'+
                '<td>'+list[i].createDate+'</td>'+
                '<td>'+getCtrlEdit(list[i].id,list[i].isEdit)+'</td>'+
            '</tr>');

            $('#content_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsContentPreview();
            });
            $('#editBtn_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsEdit('edit');
            });

            $('#launchBtn_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsLaunchEdit(this.id.split('_')[1]);
            });
        }
    };

    var getCtrlEdit = function(id){
        var re = '';
        if(ctrl_launch_add!=''){
            re += '<button id="launchBtn_'+id+'">投放</button>';
            re += '<button id="editBtn_'+id+'">编辑</button>';
        }
        return re;
    }
    var showNewsEdit = function (type) {
        if(type == 'edit'){
            newsContent = selectNews.content;
        }else{
            newsContent = '';
        }
        $.get($components.newsEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsEditModal').modal('show');
            if(type=='edit'){
                $('#editTypeBox').css('display','none');//
                $('#newsForm').css('display','block');//
                $('#newsWordForm').css('display','none');//
                $('#newsEditModalLabel').html('编辑稿件');
                $('input[name="newsTitle"]').val(selectNews.newsTitle);
                $('input[name="source"]').val(selectNews.source);
                //$('input[name="editorPath"]').val(selectNews.content);
                $('input[name="topImagePath"]').val(selectNews.topImagePath);
            };
            $('#newsEditModal').on('save',function () {
                if(type=='edit'){
                    editNews();
                    return;
                }
                if($("#myselect").val()==1){
                    addNews();
                }else if($("#myselect").val()==2){
                    wordAddNews();
                }
            });
        });
    };


    var showNewsContentPreview = function () {
        newsContent = selectNews.content;
        $.get($components.newsContentPreview,function (re) {

            $('#popPanel').html(re);
            $('#newsContentPreviewModal').modal('show');
        })
    }









    var getWebsitesList = function () {

        var data = {pageNum: pageNums, pageSize: pageSizes, newsId:selectNews.id};
        if (siteName != '') {
            data.siteName = siteName;
        }
        AjaxFunc($query.newsQuery, 'get', data, function (re) {
            if (re.success) {
                initTables(re.data.list);
                //initPages('pgNewsLaunch', $('#totalPgNewsLaunch'), re.data.total, getWebsitesList);
            } else {
                alert(re.msg);
            }
        });

    };


    var showNewsLaunchEdit = function(){
        $.get($components.newsLaunchEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsLaunchEditModal').modal('show');
            initializes();
            $('#newsLaunchEditModal').on('save',function () {//投放时保存按钮

                addNewsLaunch();

            });
        })
    };
    var initTables = function (list) {
        websitesList = list;
        $('#newsLaunchEditT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#newsLaunchEditT').append('<tr>' +
            '<td>' + list[i].siteName + '</td>' +
            '<td style="white-space:pre-wrap">' + getChannels(list[i].websiteId, list[i].channelLaunch) + '</td>' +
            '</tr>');
            for (var j = 0; j < list[i].channelLaunch.length; j++) {
                $('#wcCB_' + list[i].websiteId + '_' + list[i].channelLaunch[j].channelId).change(function () {
                    //$.each($('input:checkbox:checked'),function(){
                    //
                    //});
                    var ids = this.id.split('_');
                    updateSelectLaunch(ids[1],ids[2],this.checked);
                });
            }
        };
    }



    var getChannels = function (websiteId, list) {
        var re = '';
        for (var i = 0; i < list.length; i++) {
            if (list[i].channelCheck == 1) {
                re += '<label style="margin-left: 5px;margin-top: 5px;color: #ff0000;">' + '<input type="checkbox"  id="wcCB_' + websiteId + '_' + list[i].channelId + '">' + list[i].channelName + '</label>';
            } else {
                re += '<label style="margin-left: 5px;margin-top: 5px;">' + '<input type="checkbox" id="wcCB_' + websiteId + '_' + list[i].channelId + '">' + list[i].channelName + '</label>';

            }

        }
        return re;
    };

    var initializes = function () {


        $('#searchNewsLaunchBtn').click(function () {
            siteName = $('#siteNameNewsLaunchTxt').val();
            getWebsitesList();
        });
        $('#saveBtn').click(function () {

            if (newsLaunchConfig.length > 0) {

                // 禁用按钮防止重复提交
                $('#saveBtn').attr("disabled", "true");
                $('#newsLaunchConfig').val(JSON.stringify(newsLaunchConfig));
                //console.log('newsLaunchConfig222w',JSON.stringify(newsLaunchConfig[1]))
                $('#newsLaunchEditModal').trigger('save');

            } else {
                alert('请选择栏目投放');
            }
        });
        getWebsitesList();
    };


    var updateSelectLaunch = function (wid,cid,bol) {
        // console.log(wid,cid,bol);
        if(bol){
            for (var i = 0; i < newsLaunchConfig.length; i++) {
                if (wid == newsLaunchConfig[i].websiteId && jQuery.inArray(cid,newsLaunchConfig[i].channelId) == -1) {
                    newsLaunchConfig[i].channelId.push(cid);
                    // console.log('newsLaunchConfig=='+JSON.stringify(newsLaunchConfig));
                    return;
                }
            }
            newsLaunchConfig.push({websiteId: wid, channelId: [cid]});
        }else{
            for (var j = 0; j < newsLaunchConfig.length; j++) {
                var index = jQuery.inArray(cid,newsLaunchConfig[j].channelId);
                if(index!=-1){
                    newsLaunchConfig[j].channelId.splice(index,1);
                    // console.log('newsLaunchConfig=='+JSON.stringify(newsLaunchConfig));
                    return;
                }
            }
        }
        // console.log('newsLaunchConfig=='+JSON.stringify(newsLaunchConfig));
    }
    initialize();




})