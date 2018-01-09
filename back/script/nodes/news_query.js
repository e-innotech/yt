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
    var ctrl_launch_add = '';
    var ctrl_upate = '';

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
                // initPage(re.data.total);
                initPage('pg',$('#totalPg'),re.data.total,getNewsList);
            }else{
                alert(re.msg);
            }
        });
    };
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('news/add')!=-1){
                ctrl_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('launch/add')!=-1){
                ctrl_launch_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };

        };
        if(ctrl_add != '') {
            $('#addNewsBtn').show();
            $('#addNewsBtn').click(function () {
                showNewsEdit('add');
            });
        };
        $('#datetimepicker_begin').datetimepicker({
            format:'yyyy-mm-dd',
            language:'zh-CN',
            autoclose:true,
            todayBtn:true,
            todayHighlight:true,
            minView:'month'
        });
        $('#datetimepicker_end').datetimepicker({
            format:'yyyy-mm-dd',
            language:'zh-CN',
            autoclose:true,
            todayBtn:true,
            todayHighlight:true,
            minView:'month'
        });
        $('#searchBtn').click(function () {
            newsTitle = $('#newsTitleTxt').val();
            source = $('#sourceTxt').val();
            startDate = $('#datetimepicker_begin').val();
            endDate = $('#datetimepicker_end').val();
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
                '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_launch_add!=''?'<button id="launchBtn_'+list[i].id+'">投放</button>':'')+'</td>'+
            '</tr>');

            $('#editBtn_'+list[i].id).click(function () {
                selectNews = getNewsFromId(this.id.split('_')[1]);
                showNewsEdit('edit');
            });
            // $('#deleteBtn_'+list[i].id).click(function () {
            //     var id = this.id.split('_')[1];
            //     $.get($components.confirm,function (re) {
            //         $('#popPanel1').html(re);
            //         $('#confirmModal').modal('show');
            //         confirm.initialize(id,deleteRole);
            //     });
            // });
        }

    };
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
    initialize();
})