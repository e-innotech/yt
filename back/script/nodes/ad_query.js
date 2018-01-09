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
       if($('input[name="adName"]').val()==''||$('input[name="adPositionsId"]').val()==''||$('input[name="beginDate"]').val()==''||$('input[name="endDate"]').val()==''){
           alert('参数不能为空');
           return;
       }
       var data = $('#adForm').serializeObject();
       AjaxFunc($apiUrl+ctrl_add,'post',data,function (re) {
          if(re.success){
              $('#adEditModal').modal('hide');
              getAdList();
          }
          alert(re.msg);
       });
   };
   var editAd = function () {
       var data = $('#adForm').serializeObject();
       data.id = selectAd.id;
       AjaxFunc($apiUrl+ctrl_upate,'post',data,function (re) {
           if(re.success){
               $('#adEditModal').modal('hide');
               getAdList();
           }
           alert(re.msg);

       });
   };
   var offOnLineAd = function (id,status) {
        var data = {id:id,status:status};
        AjaxFunc($apiUrl+ctrl_offLine,'post',data,function (re) {
            if(re.success){
                getAdList();
            }
            alert(re.msg);
        })
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
                '<td>'+getCtrl(list[i].id,list[i].status)+'</td>'+
                '</tr>');
            
            $('#editBtn_'+list[i].id).click(function () {
                selectAd = getAdFromId(this.id.split('_')[1]);
                showAdEdit('edit');
            });
            $('#onLineBtn_'+list[i].id).click(function () {
                offOnLineAd(this.id.split('_')[1],1);
            });
            $('#offLineBtn_'+list[i].id).click(function () {
                offOnLineAd(this.id.split('_')[1],0);
            });
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
   var getAdFromId = function (id) {
        for(var i=0;i<adList.length;i++){
            if(id == adList[i].id){
                return adList[i];
            }
        }
   };
   var getCtrl = function (id,status) {
        if(status == 1){
            return (ctrl_offLine!=''?'<button id="offLineBtn_'+id+'">↓</button>':'');
        };
        return (ctrl_upate!=''?'<button id="editBtn_'+id+'">编辑</button>':'')+(ctrl_offLine!=''?'<button id="onLineBtn_'+id+'">↑</button>':'');

   };
   var showAdEdit = function (type) {
       $.get($components.adEdit,function (re) {
           $('#popPanel').html(re);
           $('#adEditModal').modal('show');
           if(type=='edit'){
               $('#adEditModalLabel').html('编辑广告');
                   $('input[name="adName"]').val(selectAd.adName);
                   $('#adPositionsInfo').val(selectAd.adPositions.adName+"："+selectAd.adPositions.adWidth+"X"+selectAd.adPositions.adHeight);
                   $('input[name="adPositionsId"]').val(selectAd.adPositions.id);
                   $('input[name="source"]').val(selectAd.source);
                   $('input[name="remark"]').val(selectAd.remark);
                   $('input[name="beginDate"]').val(selectAd.beginDate);
                   $('input[name="endDate"]').val(selectAd.endDate);
           };
           $('#adPositionsInfo').click(function () {
               $.get($components.adPositionsList,function (re) {
                   $('#popPanel1').html(re);
                   $('#adPositionsListModal').modal('show');
               });
           });
           $('input[type="file"]').change(function(){
               var files = $('#upload_file').prop('files');
               var data = new FormData();
               data.append('upload_file',files[0]);
               data.append('fileDirectory','ad/');
               AjaxUpload($uploadUrl,data,function (re) {
                  // console.log(re);
                   alert(re.msg);
                   if(re.success){
                       $('input[name="source"]').val(re.data);
                   }
               });
           });
           $('input[name="beginDate"]').datetimepicker({
               format:'yyyy-mm-dd',
               language:'zh-CN',
               autoclose:true,
               todayBtn:true,
               todayHighlight:true,
               minView:'month'
           });
           $('input[name="endDate"]').datetimepicker({
               format:'yyyy-mm-dd',
               language:'zh-CN',
               autoclose:true,
               todayBtn:true,
               todayHighlight:true,
               minView:'month'
           });
           
           $('#saveBtn').click(function () {
               if(type == 'edit'){
                   editAd();
                   return;
               };
               addAd();
           });
       });
   };

   initialize();
});