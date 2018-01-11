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
    var ctrl_upate = '';
    var ctrl_launch_add = '';

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
    var addNewsLaunch = function () {
        var data = {newsId:selectNews.id,newsLaunchConfig:$('#newsLaunchConfig').val()};
        AjaxFunc($apiUrl+ctrl_launch_add,'post',data,function (re) {
            alert(re.msg);
            if(re.success){
                getNewsList();
                $('#newsLaunchConfig').val('');
            }
        })
    }
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('/news/add')!=-1){
                ctrl_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('/news/launch/add')!=-1){
                ctrl_launch_add = nodeData.buttons[i].uri;
            };
        };
        if(ctrl_add != '') {
            $('#addNewsBtn').show();
            $('#addNewsBtn').click(function () {
                showNewsEdit('add');
            });
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
                '<td>'+list[i].content+'</td>'+
                '<td>'+list[i].createDate+'</td>'+
                '<td>'+getCtrlEdit(list[i].id,list[i].isEdit)+'</td>'+
            '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsEdit('edit');
            });

            $('#launchBtn_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsLaunchEdit();
            });
        }
    };
    var getCtrlEdit = function(id,isEdit){
        var re = '';
        if(ctrl_upate!='' && isEdit==0){
            re += '<button id="editBtn_'+id+'">编辑</button>';
        };
        if(ctrl_launch_add!=''){
            re += '<button id="launchBtn_'+id+'">投放</button>';
        }
        return re;
    }
    var showNewsEdit = function (type) {
        $.get($components.newsEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsEditModal').modal('show');
            if(type=='edit'){
                $('#newsEditModalLabel').html('编辑稿件');
            //     $('input[name="roleName"]').val(selectRole.roleName);
            //     $('input[name="remark"]').val(selectRole.remark);
            //     $('textarea[name="resourceNames"]').val(getResourceNames());
            //     resourceListSelectIds = getResourceIds();
            };
            $('input[type="file"]').change(function(){
                var files = $('#upload_file').prop('files');
                var data = new FormData();
                data.append('upload_file',files[0]);
                data.append('fileDirectory','news/top/');
                AjaxUpload($uploadUrl,data,function (re) {
                    // console.log(re);
                    alert(re.msg);
                    if(re.success){
                        $('input[name="topImagePath"]').val(re.data);
                    }
                });
            });
            // $('#saveBtn').click(function () {
            //     if(type == 'edit'){
            //         editRole();
            //         return;
            //     };
            //     addRole();
            // });
            // $('textarea[name="resourceNames"]').click(function () {
            //     $.get($components.resourceList,function (re) {
            //         resourceListType = 'connect';
            //         $('#popPanel1').html(re);
            //         $('#resourceListModal').modal('show');
            //
            //     });
            // });
        });
    };
    var showNewsLaunchEdit = function(){
        $.get($components.newsLaunchEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsLaunchEditModal').modal('show');
            $('#newsLaunchEditModal').on('hide.bs.modal',function () {
                if($('#newsLaunchConfig').val()!='') {
                    addNewsLaunch();
                }
            });
        })
    };
    initialize();
})