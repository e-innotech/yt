$(function () {
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
        }
        if(source!=''){
            data.source = source;
        }
        if(startDate!='' && endDate!=''){
            data.startDate = startDate;
            data.endDate = endDate;
        }
        AjaxFunc($query.news,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getNewsList);
            }else{
                alert(re.msg);
            }
        });
    };

    var pushArray = function(wid,cid,newsLaunchConfigNew){
        for(var i = 0; i < newsLaunchConfigNew.length; i++){
            if(newsLaunchConfigNew[i] && newsLaunchConfigNew[i].websiteId && newsLaunchConfigNew[i].websiteId == wid){
                newsLaunchConfigNew[i].channelId.push(cid);
                return;
            }
        }
        newsLaunchConfigNew.push({websiteId: wid, channelId: [cid]});

    };
    var addNews = function () {
        newsLaunchConfigNew = [];
        var data = $('#newsForm').serializeObject();
        data.content = newsContent;
        // 如果点击单图模式则需要检查div 是否有img
        if(data.cover == '1'){
            if(data.topImagePath == '') {
                alert("您是要自定义头图，请选择头图！");
                return;
            }
        }
        if(data.source == ''){
            alert('来源不能为空');
            return;
        }
        if(data.newsTitle == ''){
            alert('标题不能为空');
            return;
        }
        if(data.content == ''){
            alert('内容不能为空');
            return;
        }
        // 投放关系校验
        var launch = '';
        $("input[type='checkbox']:checked").each(function () {
            var arr = $(this).attr("id").split("_");
            pushArray(arr[1],arr[2],newsLaunchConfigNew);
        });
        if(newsLaunchConfigNew.length == 0){
            alert('请选择栏目投放');
            return;
        }
        launch = JSON.stringify(newsLaunchConfigNew);
        data.newsLaunchConfigNew = launch;
        data.flage = 'add';
        // 校验通过之后禁止再次点击
        $('#newsEditModal').find('#saveBtn').attr("disabled", "true");
        $('#newsEditModal').modal('hide');
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsList();
            }
        });
    };

    var wordAddNews = function () {
        var data = $("#newsWordForm").serializeObject();
        var from = $("#newsWordForm");
        // 数据校验
        if(data.source == ''){
            alert('来源不能为空');
            return;
        }
        if(!validate_doc('upload_word')){
            return;
        }

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
        from.ajaxSubmit(options);
    };

    //限制上传文件的类型和大小
    var validate_doc = function(id){
        var file = document.getElementById(id).files;
        if(file.length == 0) {
            alert('上传的word文件不能为空');
            return false;
        }
        if(!/.(doc|docx)$/.test(file[0].name)){
            alert("文件类型必须是.doc,docx中的一种");
            return false;
        }
        if((file[0].size)>(10*1024*1024)){
            alert("请上传小于10M的文档");
            return false;
        }
        return;
    }
// 编辑稿件+投放关系
    var editNews = function () {
        newsLaunchConfigNew = [];
        var data = $('#newsForm').serializeObject();
        data.content = newsContent;
        data.id = selectNews.id;

        // 如果点击单图模式则需要检查div 是否有img
        if(data.cover == '1'){
            if(data.topImagePath == '') {
                alert("您是要自定义头图，请选择头图！");
                return;
            }
        }
        if(data.cover == '0'){
            data.topImagePath = '';
        }
        if(data.source == ''){
            alert('来源不能为空');
            return;
        }
        if(data.newsTitle == ''){
            alert('标题不能为空');
            return;
        }
        if(data.content == ''){
            alert('内容不能为空');
            return;
        }
        // 投放关系校验
        var launch = '';
         $('#newsWebchannel input[type="checkbox"]:checked').each(function () {
            var arr = $(this).attr("id").split("_");
            pushArray(arr[1],arr[2],newsLaunchConfigNew);
        });

        launch = JSON.stringify(newsLaunchConfigNew);
        if($('#editLaunchConfig').prop("checked")){
            data.editLaunchConfigFlag = 1;
            if(newsLaunchConfigNew.length == 0){
                alert('请选择栏目投放');
                return;
            }
        } else {
            data.editLaunchConfigFlag = 0;
        }

        data.newsLaunchConfigNew = launch;
        data.flage = 'update';
        // 校验通过之后禁止再次点击
        $('#newsEditModal').find('#saveBtn').attr("disabled", "true");
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#newsEditModal').modal('hide');
                getNewsList();
            }
        });
    };
// 点击投放
    var addNewsLaunch = function () {
        var data = {newsId:selectNews.id,newsLaunchConfig:$('#newsLaunchConfig').val()};
        AjaxFunc($apiUrl+ctrl_launch_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#newsLaunchEditModal').modal('hide');
                getNewsList();
            }
        })
    };

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
        }
        if(ctrl_add != '') {
            $('#addNewsBtn').show();
            $('#addNewsBtn').click(function () {
                showNewsEdit('add');
            });
        }
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
    };

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
           // re += '<button id="launchBtn_'+id+'">投放</button>';
            re += '<button id="editBtn_'+id+'">编辑</button>';
        }
        return re;
    };

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
                if(selectNews.topImagePath){
                    $(":radio[name='cover'][value='1']").prop("checked", "checked");
                    // 设置头图img
                    $("#coverImg").html('');
                    var selectImg = $('<img src="'+selectNews.topImagePath+'" onload="reDrawImage(this);">');
                    $("#coverImg").append(selectImg);
                    $("#selectDiv").css("display","block");
                }
                $('#showLaunchTitle').prepend('<input type="checkbox" id="editLaunchConfig">');
                $('#showLaunchTitle span').text("勾选可编辑投放栏目").css('color','red').css('font-size','20px');
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

            // 绑定头图选择

                $("input[name=cover]").click(function(){
                    var discount = $(this).val();
                    if(discount=="0" || discount == "-1"){
                        $("#selectDiv").css("display","none");
                        $("#coverImg").html('');
                        $("#show_topImg_Modal").modal('hide');
                    }
                    if(discount=="1"){
                        $("#selectDiv").css("display","block");
                    }
                });

                $("#select_topImg").click(function(){
                    // 首先判断编辑器中是否有图片
                    if($("#editor img").length == 0) {
                        alert("编辑器中没有找到图片！");
                        return;
                    }
                    $("#show_top_img").html('');
                    $.get($components.newsEdit_topImg,function (re) {
                        $('#popPanel1').html(re);
                        $("#editor img").each(function(){
                            var child_div = $('<div class="show_topImg_item"></div>');
                            var img = $('<img src="'+$(this).attr("src")+'" onload="reDrawImage(this);">');
                            child_div.append(img);
                            $("#show_top_img").append(child_div);
                        });
                        $('#show_topImg_Modal').modal('show');
                        $(".show_topImg_item").click(function(){

                            $(this).addClass("show_topImg_item_selected").siblings().removeClass("show_topImg_item_selected");
                            var url = $(".show_topImg_item_selected").children("img").attr("src");
                            // 点击确定按钮
                            $("#topImgBtn").click(function(){
                                $("#topImg").val(url);
                                $("#coverImg").html('');
                                var selectImg = $('<img src="'+url+'" onload="reDrawImage(this);">');
                                $("#coverImg").append(selectImg);
                                $('#show_topImg_Modal').modal('hide');
                            });

                        });
                    });
                });
            // 初始化网站栏目数据
            AjaxFunc($query.newsQueryWebChannel, 'get', null,function (re) {
                if (re.success) {
                    initWebChannel(re.data);
                    //initPages('pgNewsLaunch', $('#totalPgNewsLaunch'), re.data.total, getWebsitesList);
                } else {
                    alert(re.msg);
                }
            });

            if(type=='edit'){
                // 显示投放关系
                showLaunchRelationShip();
                $('#newsWebchannel input[type="checkbox"]').attr("disabled",true);
            }
            $('#editLaunchConfig').change(function () {
                if($(this).prop("checked")){
                    $('#newsWebchannel input[type="checkbox"]').attr("disabled",false);
                } else {
                    $('#newsWebchannel input[type="checkbox"]').attr("disabled",true);
                }
            });
        });
    };
// 调用接口查投放数据
    var showLaunchRelationShip = function () {
        var data = {newsId:selectNews.id};
        AjaxFunc($query.newsQueryWebChannelRelationShip, 'get', data, function (re) {
            if (re.success) {
                initEditLaunchTables(re.data);
                //initPages('pgNewsLaunch', $('#totalPgNewsLaunch'), re.data.total, getWebsitesList);
            } else {
                alert(re.msg);
            }
        });
    }
// 显示稿件的投放关系
    var initEditLaunchTables = function (list) {
        for (var i = 0; i < list.length; i++) {
            for(var j = 0; j < list[i].channelid.length; j++){
                $('#wcCB_'+list[i].websiteId+'_'+list[i].channelid[j]).attr('checked','checked');
                $('#wcCB_'+list[i].websiteId+'_'+list[i].channelid[j]).parent().css('color','red');
            }
        }
    };

    var showNewsContentPreview = function () {
        newsContent = selectNews.content;
        $.get($components.newsContentPreview,function (re) {

            $('#popPanel').html(re);
            $('#newsContentPreviewModal').modal('show');
        })
    };

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
            $('#newsLaunchConfig').val('');
            initializes();
            $('#newsLaunchEditModal').on('save',function () {//投放时保存按钮
                addNewsLaunch();
            });
        })
    };

    var initWebChannel = function (list) {
        $('#newsWebchannel').empty();
        for (var i = 0; i < list.length; i++) {
            $('#newsWebchannel').append('<tr>' +
                '<td>' + list[i].siteName + '</td>' +
                '<td style="white-space:pre-wrap">' + getChannels(list[i].websiteId, list[i].channelLaunch) + '</td>' +
                '</tr>');
        }
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
                    var ids = this.id.split('_');
                    updateSelectLaunch(ids[1], ids[2], this.checked);
                });
            }
        }
    };

    var getChannels = function (websiteId, list) {
        var re = '';
        for (var i = 0; i < list.length; i++) {
            if (list[i].channelCheck == 1) {
                re += '<label style="margin-left: 5px;margin-top: 5px;color: #ff0000;">' + '<input type="checkbox" checked id="wcCB_' + websiteId + '_' + list[i].channelId + '">' + list[i].channelName + '</label>';
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
                $('#newsLaunchEditModal').trigger('save');
                //parent.location.reload();
            } else {
                alert('请选择栏目投放');
            }
        });
        siteName='';
        getWebsitesList();
    };

    var updateSelectLaunch = function (wid,cid,bol) {
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
    };

    initialize();
});