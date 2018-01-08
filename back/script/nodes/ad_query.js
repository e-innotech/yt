$(function () {
   pageNum = 1;
   pageSize = 20;

   var adName = '';
   var status = -1;
   var adType = 0;

   var adList = [];
   var selectAd;

   var ctrl_add = '';
   var ctrl_upate = '';
   var ctrl_offLine = '';

   var getAdList = function () {
      var data = {pageNum:pageNum,pageSize:pageSize,status:status};
      if(adName!=''){
         data.adName = adName;
      };


      AjaxFunc($query.ad,'get',data,function (re) {
         if(re.success){
            initTabel(re.data.list);
            initPage(re.data.total);
         }else{
            alert(re.msg);
         };
      });
   };
   var addAd = function () {

   };
   var eidtAd = function () {

   };


   var initialize = function () {
       for(var i=0;i<nodeData.buttons.length;i++){
           if(nodeData.buttons[i].uri.indexOf('add')!=-1){
               ctrl_add = nodeData.buttons[i].uri;
           };
           if(nodeData.buttons[i].uri.indexOf('offLine')!=-1){
               ctrl_offLine = nodeData.buttons[i].uri;
           };
           if(nodeData.buttons[i].uri.indexOf('update')!=-1){
               ctrl_upate = nodeData.buttons[i].uri;
           };
       };
       if(ctrl_add != '') {
           $('#addAdBtn').show();
           $('#addAdBtn').click(function () {
               showAdEdit('add');
           });
       };
       $('#searchBtn').click(function () {
           adName = $('#adNameTxt').val();
           status = $('#statusSelect').val();
           getAdList();
       });
       getAdList();
   };
   var initTabel = function (list) {
       adList = list;
       $('#adT').empty();
       for(var i=0;i<list.length;i++){
            $('#adT').append('<tr>' +
                '<td>'+list[i].adName+'</td>' +
                '<td>'+list[i].source+'</td>'+
                '<td>'+list[i].remark+'</td>'+
                '<td>'+list[i].beginDate+'</td>'+
                '<td>'+list[i].endDate+'</td>'+
                '<td>'+OFFONLINE[list[i].status]+'</td>'+
                // '<td>'+(ctrl_upate!=''?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_launch_add!=''?'<button id="launchBtn_'+list[i].id+'">投放</button>':'')+'</td>'+
                '</tr>');
       };
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
                   getAdList();
               }
               $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
           }
       });
   };

   var showAdEdit = function (type) {
       $.get($components.adEdit,function (re) {
           $('#popPanel').html(re);
           $('#adEditModal').modal('show');
           if(type=='edit'){
               $('#adEditModalLabel').html('编辑广告');
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
});