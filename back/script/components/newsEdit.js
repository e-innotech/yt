$(function () {
   var E = window.wangEditor;
   var editor = new E('#editor');
   var initialize = function () {

       editor.customConfig.uploadFileName = 'upload_file';
       editor.customConfig.uploadImgParams = {fileDirectory:'news'};
       editor.customConfig.uploadImgMaxLength = 5;
       editor.customConfig.uploadImgServer = $uploadUrl;
       editor.create();

       editor.txt.html(newsContent);


       $('input[type="file"]').change(function(){
           var files = $('#upload_file').prop('files');
           var data = new FormData();
           data.append('upload_file',files[0]);
           data.append('fileDirectory','news/top');
           AjaxUpload($uploadUrl,data,function (re) {
               // console.log(re);
               alert(re.msg);
               if(re.success){
                   $('input[name="topImagePath"]').val(re.data[0]);
               }
           });
       });

       $('#saveBtn').click(function () {
           newsContent = editor.txt.html();
           $('#newsEditModal').trigger('save');
       });


   };
   initialize();
});