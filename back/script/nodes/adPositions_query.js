$(function () {
   pageNum = 1;
   pageSize = 20;

   var adName = '';
   var adPositionsList = [];
   var selectAdPositions;

    var ctrl_add = '';
    var ctrl_upate = '';
    var ctrl_delete = '';

   var getAdPositionsList = function () {
       var data = {pageNum:pageNum,pageSize:pageSize};
       if(adName!=''){
           data.adName = adName;
       };
       AjaxFunc($query.adPositions,'get',data,function (re) {
           if(re.success){
               initTable(re.data.list);
               initPage(re.data.total);
           }else{
               alert(re.msg);
           }
       });
   };
   var addAdPositions = function () {
       if($('input[name="adName"]').val() == ''){
           alert('广告位名不能为空');
           return;
       };
       var data = $('#adPositionsForm').serializeObject();
       AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
           if(re.success){
               getAdPositionsList();
               $('#adPositionsEditModal').modal('hide');
           }
           alert(re.msg);
       });
   };
   var editAdPositions = function () {
       var data = $('#adPositionsForm').serializeObject();
       data.id = selectAdPositions.id;
       AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
           if(re.success){
               getAdPositionsList();
               $('#adPositionsEditModal').modal('hide');
           }
           alert(re.msg);
       });
   };
   var deleteAdPositions = function (id) {
       var data = {id:id};
       AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
           if(re.success){
               getAdPositionsList();
           }
           alert(re.msg);
       });
   };
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
           $('#addAdPositionsBtn').show();
           $('#addAdPositionsBtn').click(function () {
               showAdPositionsEdit('add');
           });
       };
       $('#searchBtn').click(function () {
           adName = $('#adNameTxt').val();
           getAdPositionsList();
       });
       getAdPositionsList();
   };
   var initTable = function (list) {
       adPositionsList = list;
       $('#adPositionsT').empty();
       for(var i=0;i<list.length;i++){
           $('#adPositionsT').append('<tr>' +
               '<td>'+list[i].adName+'</td>'+
               '<td>'+list[i].adWidth+'</td>'+
               '<td>'+list[i].adHeight+'</td>'+
               '<td>'+list[i].remark+'</td>'+
               '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
               '</tr>');

           $('#editBtn_'+list[i].id).click(function () {
               selectAdPositions = getAdPositionsFromId(this.id.split('_')[1]);
               showAdPositionsEdit('edit');
           });
           $('#deleteBtn_'+list[i].id).click(function () {
               var id = this.id.split('_')[1];
               $.get($components.confirm,function (re) {
                   $('#popPanel1').html(re);
                   $('#confirmModal').modal('show');
                   confirm.initialize(id,deleteAdPositions);
               });
           });
       }
   };
   var initPage = function (total) {
       if(pageNum>1){
           return;
       }
       $.jqPaginator('#pg', {
           totalCounts:Number(total)==0?1:Number(total),
           pageSize:pageSize,
           visiblePages: 3,
           currentPage: pageNum,
           first: '<li class="first"><a href="javascript:;"><<</a></li>',
           prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
           next: '<li class="next"><a href="javascript:;">下一页</a></li>',
           last: '<li class="last"><a href="javascript:;">>></a></li>',
           page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
           onPageChange: function (num, type) {
//	            alert(type + '：' + num);
               if(type == 'change'){
                   pageNum = num;
                   getAdPositionsList();
               };
               $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
           }
       });
   };
    var getAdPositionsFromId = function (id) {
        for(var i=0;i<adPositionsList.length;i++){
            if(id == adPositionsList[i].id){
                return adPositionsList[i];
            }
        }
    };
    var showAdPositionsEdit = function (type) {
        $.get($components.adPositionsEdit,function (re) {
            $('#popPanel').html(re);
            $('#adPositionsEditModal').modal('show');
            if(type=='edit'){
                $('#radPositionsEditModalLabel').html('编辑广告位');
                $('input[name="adName"]').val(selectAdPositions.adName);
                $('input[name="adWidth"]').val(selectAdPositions.adWidth);
                $('input[name="adHeight"]').val(selectAdPositions.adHeight);
                $('textarea[name="remark"]').val(selectAdPositions.remark);
            };
            $('#saveBtn').click(function () {
                if(type == 'edit'){
                    editAdPositions();
                    return;
                };
                addAdPositions();
            });
        });
    };
   initialize();
});