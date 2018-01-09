$(function () {
    pageNum = 1;
    pageSize = 20;

    var channelName = '';
    var channelList = [];
    var selectChannel;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

    var getChannelList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize,isUse:$('#isUseSelect').val()};
        if(channelName!=''){
            data.channelName = channelName;
        };
        AjaxFunc($query.channel,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getChannelList);
            }else{
                alert(re.msg);
            }
        });

    };
    var isUseChanel = function (id,isUse) {
        var data = {id:id,isUse:isUse};
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getChannelList();
            }
        });
    };
    var addChannel = function () {
        if($('input[name="channelName"]').val() == ''){
            alert('栏目名不能为空');
            return;
        };
        var data = $('#channelForm').serializeObject();
        AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#channelEditModal').modal('hide');
                getChannelList();
            }
        });
    };
    var editChannel = function () {
        var data = $('#channelForm').serializeObject();
        data.id = selectChannel.id;
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#channelEditModal').modal('hide');
                getChannelList();
            }
        });
    };
    var deleteChannel = function (id) {
        var data = {id:id};
        AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
            alert(re.msg);
            if(re.success){
                getChannelList();
            }
        });
    }
    var initialize = function () {
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
            $('#addChannelBtn').show();
            $('#addChannelBtn').click(function () {
                showChannelEdit('add');
            });
        };
        $('#searchBtn').click(function () {
            channelName = $('#channelNameTxt').val();
            getChannelList();
        });
        getChannelList();
    };
    var initTable = function(list) {
        channelList = list;
        $('#channelT').empty();
        for(var i=0;i<list.length;i++){
            $('#channelT').append('<tr>' +
            '<td>'+list[i].channelName+'</td>'+
            '<td>'+list[i].remark+'</td>'+
            '<td><p id="isUseBtn_'+list[i].id+'" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" ><span> </span></p></td>' +
            '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
            '</tr>');


            $('#isUseBtn_'+list[i].id).click(function () {
                if(ctrl_upate!=''){
                    isUseChanel(this.id.split('_')[1],($(this).attr('class')=='aniu'?0:1));
                };
            });
            $('#editBtn_'+list[i].id).click(function () {
                selectChannel = getChannelFromId(this.id.split('_')[1]);
                showChannelEdit('edit');
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteChannel);
                });
            });
        }

    };
    var getChannelFromId = function (id) {
        for(var i=0;i<channelList.length;i++){
            if(id == channelList[i].id){
                return channelList[i];
            }
        }
    };
    var showChannelEdit = function (type) {
        $.get($components.channelEdit,function (re) {
            $('#popPanel').html(re);
            $('#channelEditModal').modal('show');
            if(type == 'edit'){
                $('input[name="channelName"]').val(selectChannel.channelName);
                $('input[name="remark"]').val(selectChannel.remark);
            };
            $('#saveBtn').click(function () {
                if(type=='edit'){
                    editChannel();
                    return;
                };
                addChannel();
            });
        });

    };
    initialize();
})