$(function () {
   pageNum = 1;
   pageSize =20;

   var memberUName = '';
   var newsTitle = '';
   var content = '';

   var startDate = '';
   var endDate = '';


   var ctrl_delete = '';

   var getCommentList = function () {
       var data = {pageNum:pageNum,pageSize:pageSize};
       if(newsTitle!=''){
           data.newsTitle = newsTitle;
       };
       if(memberUName!=''){
           data.memberUName = memberUName;
       };
       if(content!=''){
           data.content = content;
       };
       if(startDate!='' && endDate!=''){
           data.startDate = startDate;
           data.endDate = endDate;
       };
       AjaxFunc($query.membersComment,'get',data,function (re) {
           if(re.success){
               initTable(re.data.list);
               initPage(re.data.total);
           }else{
               alert(re.msg);
           }
       });
   };
   var deleteComment = function (id) {
       var data = {id:id};
       AjaxFunc($apiUrl+ctrl_delete,'get',data,function (re) {
          if(re.success){
              $('#confirmModal').modal('hide');
              getCommentList();
          };
          alert(re.msg);
       });
   };
   var initialize = function () {
       for(var i=0;i<nodeData.buttons.length;i++){
           if(nodeData.buttons[i].uri.indexOf('delete')!=-1){
               ctrl_delete = nodeData.buttons[i].uri;
           };
       };
       $('#startDate').datetimepicker({
           format:'yyyy-mm-dd',
           language:'zh-CN',
           autoclose:true,
           todayBtn:true,
           todayHighlight:true,
           minView:'month'
       });
       $('#endDate').datetimepicker({
           format:'yyyy-mm-dd',
           language:'zh-CN',
           autoclose:true,
           todayBtn:true,
           todayHighlight:true,
           minView:'month'
       });
       $('#searchBtn').click(function () {
           newsTitle = $('#newsTitleTxt').val();
           memberUName = $('#memberUNameTxt').val();
           content = $('#contentTxt').val();
           startDate = $('#startDate').val();
           endDate = $('#endDate').val();
           getCommentList();
       });
       getCommentList();
   };
    var initTable = function(list) {
        $('#commentT').empty();
        for(var i=0;i<list.length;i++){
            $('#commentT').append('<tr>' +
                '<td>'+list[i].news.newsTitle+'</td>' +
                '<td>'+list[i].content+'</td>'+
                '<td>'+list[i].members.uname +'</td>'+
                '<td>'+list[i].createDate+'</td>'+
                '<td>'+(ctrl_delete!=''?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
                '</tr>');

            $('#deleteBtn_'+list[i].id).click(function () {
                var id = this.id.split('_')[1];
                $.get($components.confirm,function (re) {
                    $('#popPanel1').html(re);
                    $('#confirmModal').modal('show');
                    confirm.initialize(id,deleteComment);
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
                    getCommentList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    };
   initialize();
});