$(function () {


    var siteName = '';

    var websitesList = [];
    var selectWebsites;

    var selectChannelIds = [];

    var channelList = [];

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getWebsitesList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isUse:$('#isUseSelect').val()};
        if(siteName!=''){
            data.siteName = siteName;
        }
        AjaxFunc($query.websites,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getWebsitesList);
            }else{
                alert(re.msg);
            }
        });

    };

    var getChannelList = function () {
        var data = {pageNum:1,pageSize:0,isUse:1};
        AjaxFunc($query.channel,'get',data,function (re) {
            if(re.success){
                renderChannelEdit(re.data.list);
            }else{
                alert(re.msg);
            }
        });
    };
    var addWebsites = function () {
        if($('input[name="siteName"]').val() == ''){
            alert('网站名不能为空');
            return;
        };
        var data = $('#websitesForm').serializeObject();
        data.webTemplates = [{templateType:0,templatePath:$('#template_0').val()},
            {templateType:1,templatePath:$('#template_1').val()},
            {templateType:2,templatePath:$('#template_2').val()}];
        data.channelIds = selectChannelIds.slice();
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#websitesEditModal').modal('hide');
                getWebsitesList();
            };
        });
    };
    var editWebsites = function () {
        if($('input[name="siteName"]').val() == ''){
            alert('网站名不能为空');
            return;
        };
        var data = $('#websitesForm').serializeObject();
        data.webTemplates = [{templateType:0,templatePath:$('#template_0').val()},
            {templateType:1,templatePath:$('#template_1').val()},
            {templateType:2,templatePath:$('#template_2').val()}];
        data.channelIds = selectChannelIds.slice();
        data.id = selectWebsites.id;
        data.channels = selectWebsites.channels;
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#websitesEditModal').modal('hide');
                getWebsitesList();
            };
        });
    };

    var deleteWebsites = function (id) {
        var data = {id:id};
        AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#confirmModal').modal('hide');
                getWebsitesList();
            };
        });
    };
    var isUseWebsites = function (id,isUse) {
        var data = {id:id,isUse:isUse};
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getWebsitesList();
            }
        });
    };
    var initialize = function (list) {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('add')!=-1){
                ctrl_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('delete')!=-1){
                ctrl_delete = nodeData.buttons[i].uri;
            };
        }
        if(ctrl_add != '') {
            $('#addWebsitesBtn').show();
            $('#addWebsitesBtn').click(function () {
                selectWebsites = [];
                showWebsitesEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            siteName = $('#siteNameTxt').val();
            getWebsitesList();
        });
        getWebsitesList();
    };
    var initTable = function(list) {
        websitesList = list;
        $('#websitesT').empty();
        for(var i=0;i<list.length;i++){
            $('#websitesT').append('<tr>' +
            '<td>'+list[i].siteName+'</td>'+
            '<td>'+list[i].domain+'</td>'+
            '<td>'+list[i].zone+'</td>'+
            '<td>'+list[i].route+'</td>'+
            '<td>'+getChannelNames(list[i].channels)+'</td>'+
            '<td>'+list[i].createDate+'</td>'+
            '<td><p id="isUseBtn_'+list[i].id+'" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '"><span> </span></p></td>' +
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
            '</tr>');


            $('#isUseBtn_'+list[i].id).click(function () {
                if(ctrl_upate!=''){
                    isUseWebsites(this.id.split('_')[1],($(this).attr('class')=='anniu'?1:0));
                };
            });
            $('#editBtn_'+list[i].id).click(function () {
                selectWebsites = getWebsitesFromId(this.id.split('_')[1]);
                showWebsitesEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteWebsites);
                });
            });
        }
    };

    var renderChannelEdit = function (list) {
        channelList = list;
        selectChannelIds = [];
        for(var i=0;i<list.length;i++){
            $('#channelIds').append('<label class="btn btn-default checkboxL" style="margin-left: 10px; margin-top: 10px;" ><input type="checkbox" class="channelCB" id="channelCB_'+list[i].id+'">' + list[i].channelName + '</label>');


            if(selectWebsites) {
                for (var j = 0; j < selectWebsites.channels.length; j++) {
                    //list[i].id是所有的栏目的id  1-13    //selectWebsites.channels[j].id是选中的id
                    // console.log(selectWebsites.channels[j].id)
                    if (list[i].id == selectWebsites.channels[j].id) {
                        selectChannelIds.push(selectWebsites.channels[j].id);
                        $('#channelCB_' + list[i].id).prop('checked', true);
                    }
                }
            }
            $('#channelCB_' + list[i].id).change(function () {
                updateChannelIds(this.id.split('_')[1],this.checked);//点击checked
            });
        };
        renderChannelSort();
    };
    var renderChannelSort = function () {
        $('#channelSort').empty();
        if(selectChannelIds.length>0) {
            $('#channelSort').append('<li class="list-group-item-info"><h4 class="list-group-item-heading">频道排序</h4></li>');
            for (var i = 0; i < selectChannelIds.length; i++) {
                channel = getChannelFromId(selectChannelIds[i]);
                $('#channelSort').append('<li class="list-group-item">' + channel.channelName +
                    (i==0?'':'<button class="badge" id="upBtn_'+channel.id+'">↑</button>') +
                    '</li>');
                $('#upBtn_'+channel.id).click(function () {
                    var id = this.id.split('_')[1];
                    moveChannelIds(id);
                });
            }
        }
    };

    var moveChannelIds = function (id) {
        for (var i = 1; i < selectChannelIds.length; i++) {
            if (id == selectChannelIds[i]) {
                var temp = selectChannelIds[i];
                selectChannelIds[i] = selectChannelIds[i-1];
                selectChannelIds[i-1] = temp;
                renderChannelSort();
                return;
            }
        }
    }
    var updateChannelIds = function (id,bol) {
        if(!bol) {
            for (var i = 0; i < selectChannelIds.length; i++) {
                if (id == selectChannelIds[i]) {
                    selectChannelIds.splice(i, 1);
                    renderChannelSort();
                    return;
                }
            }
        }
        selectChannelIds.push(id);
        renderChannelSort();
    };
    var getChannelFromId = function (id) {
        for(var i=0;i<channelList.length;i++){
            if(id == channelList[i].id){
                return channelList[i];
            }
        }
    }
    var getChannelNames = function (list) {
        var re = [];
        for(var i=0;i<list.length;i++){
            re.push(list[i].channelName);
        }
        return re.toString();
    };
    var getWebsitesFromId = function (id) {
        for(var i=0;i<websitesList.length;i++){
            if(id == websitesList[i].id){
                return websitesList[i];
            }
        }
    };
    var showWebsitesEdit = function (type) {
        $.get($components.websitesEdit,function (re) {
            $('#popPanel').html(re);
            $('#websitesEditModal').modal('show');
            getChannelList();
            if(type == 'edit'){
                $('#websitesEditModalLabel').html('编辑网站');
                $('input[name="siteName"]').val(selectWebsites.siteName);
                $('input[name="domain"]').val(selectWebsites.domain);
                $('input[name="route"]').val(selectWebsites.route);
                $('select').val(selectWebsites.websiteBucketZone);
                $('input[name="websiteBucketName"]').val(selectWebsites.websiteBucketName);
                $('input[name="homeWeightMax"]').val(selectWebsites.homeWeightMax);
                for(var i=0;i<selectWebsites.webTemplates.length;i++){
                    $('#template_'+selectWebsites.webTemplates[i].templateType).val(selectWebsites.webTemplates[i].templatePath);
                }
            }
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editWebsites();
                    return;
                }
                addWebsites();

            });
        });
    };
    initialize();


})