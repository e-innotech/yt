$(function () {

    pageNum = 1;
    pageSize = 20;

    var newsTitle = '';
    var startDate = '';
    var endDate = '';

    var newsLaunchList = [];
    var selectNewsLaunch;

    var ctrl_aduit = '';
    var ctrl_upate = '';

    var getNewsLaunchList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(newsTitle!=''){
            data.newsTitle = newsTitle;
        };
        if(startDate!='' && endDate!=''){
            data.startDate = startDate;
            data.endDate = endDate;
        };
        AjaxFunc($query.newsLaunch,'get',data,function (re) {
           if(re.success){
               initTable(re.data.list);
               initPage('pg',$('#totalPg'),re.data.total,getNewsLaunchList);
           } else{
               alert(re.msg);
           }
        });
    };
    var aduitNewsLaunch = function () {
        var data = $('#newsLaunchAduitForm').serializeObject();
        data.id = selectNewsLaunch.id;
        AjaxFunc($apiUrl+ctrl_aduit,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                $('#newsLaunchAduitModal').modal('hide');
                getNewsLaunchList();
            }
        })
    }
    var editNewsLaunch = function () {
        var data = {id:selectNewsLaunch.id,newsLaunchConfig:$('#newsLaunchConfig').val()};
        AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsLaunchList();
                $('#newsLaunchConfig').val('');
            }
        })
    }
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('aduit')!=-1){
                ctrl_aduit = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };
        };
        //$('#startDate').datetimepicker({
        //    format:'yyyy-mm-dd',
        //    language:'zh-CN',
        //    autoclose:true,
        //    todayBtn:true,
        //    todayHighlight:true,
        //    minView:'month'
        //});
        //$('#endDate').datetimepicker({
        //    format:'yyyy-mm-dd',
        //    language:'zh-CN',
        //    autoclose:true,
        //    todayBtn:true,
        //    todayHighlight:true,
        //    minView:'month'
        //});
        $('#searchBtn').click(function () {
            newsTitle = $('#newsTitleTxt').val();
            startDate = $('#startDate').val();
            endDate = $('#endDate').val();
            getNewsLaunchList();
        });
        getNewsLaunchList();
    };
    var initTable = function (list) {
        newsLaunchList = list;
        $('#newsLaunchT').empty();
        for(var i=0;i<list.length;i++){
            $('#newsLaunchT').append('<tr>' +
                '<td>'+list[i].news.newsTitle+'</td>'+
                '<td>'+list[i].news.source+'</td>'+
                '<td><a id="content_'+list[i].id+'">查看</a></td>'+
                '<td>'+getWC(list[i].webChannelConfig)+'</td>'+
                '<td>'+list[i].createDate+'</td>'+
                '<td>'+ADUIT[list[i].status]+'</td>'+
                '<td>'+getCtrl(list[i].id,list[i].status)+'</td>'+
                '</tr>');

            $('#content_'+list[i].id).click(function () {
                selectNewsLaunch = getNewsLaunchFromId(this.id.split('_')[1]);
                showNewsContentPreview();
            });
            $('#editBtn_'+list[i].id).click(function () {
                selectNewsLaunch = getNewsLaunchFromId(this.id.split('_')[1]);
                showNewsLaunchEdit();
            });
            $('#aduitBtn_'+list[i].id).click(function () {
                selectNewsLaunch = getNewsLaunchFromId(this.id.split('_')[1]);
                showNewsLaunchAduit();
            });
        }
    };
    var getNewsLaunchFromId = function (id) {
        for(var i=0;i<newsLaunchList.length;i++){
            if(id == newsLaunchList[i].id){
                return newsLaunchList[i];
            }
        }
    };
    var getWC = function (list) {
        console.log('稿件投放_list',list)

           var re=[''];
           for(var i=0;i<list.length;i++){
               re += list[i].website.siteName+'【';
               var c = [];
               for(var j=0;j<list[i].channels.length;j++){
                   c.push(list[i].channels[j].channelName);
               }
               re += c.toString()+'】';
           };
           return re;
    };
    var getCtrl = function (id,status) {
        var re = '';
        if(ctrl_upate!='' && status == 2){
            re += '<button id="editBtn_'+id+'">编辑</button>';
        };
        if(ctrl_aduit!='' && status == 0){
            re += '<button id="aduitBtn_'+id+'">审核</button>';
        };
        return re;
    }
    var showNewsLaunchEdit = function(){
        $.get($components.newsLaunchEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsLaunchEditModal').modal('show');
            $('#newsLaunchEditModal').on('hide.bs.modal',function () {
                if($('#newsLaunchConfig').val()!='') {
                    editNewsLaunch();
                }
            });
        })
    };
    var showNewsLaunchAduit = function () {
        $.get($components.newsLaunchAduit,function (re) {
            $('#popPanel').html(re);
            $('#newsLaunchAduitModal').modal('show');
            $('#saveBtn').click(function () {
                aduitNewsLaunch();
            });
        });
    }
    var showNewsContentPreview = function () {
        newsContent = selectNewsLaunch.news.content;
        $.get($components.newsContentPreview,function (re) {
            $('#popPanel').html(re);
            $('#newsContentPreviewModal').modal('show');
        })
    }
    initialize();
});