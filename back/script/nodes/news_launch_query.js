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
                '<td>'+list[i].news.content+'</td>'+
                '<td>'+list[i].news.content+'</td>'+
                '<td>'+list[i].createDate+'</td>'+

                '</tr>');
        }
    };
    var showNewsLaunchEdit = function (type) {
        $.get($components.newsLaunchEdit,function (re) {
            $('#popPanel').html(re);
            $('#newsLaunchEditModal').modal('show');

            if(type == 'edit'){

            };
            $('#newsTitle').click(function () {
                // $.get();
            });
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editNewsLaunch();
                    return;
                }
                addNewsLaunch();
            });
        });
    }
    initialize();
});