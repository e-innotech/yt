$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    var initialize = function () {

        editor.customConfig.uploadFileName = 'upload_file';
        editor.customConfig.uploadImgParams = {fileDirectory: 'news'};

        editor.customConfig.uploadImgMaxLength = 5;
        editor.customConfig.uploadImgServer = $uploadUrl;

        editor.create();

        editor.txt.html(newsContent);


        $('input[name="upload_file"]').change(function () {
            var files = $('input[name="upload_file"]').prop('files');
            var data = new FormData();
            data.append('upload_file', files[0]);
            data.append('fileDirectory', 'news/top');

            AjaxUpload($uploadUrl, data, function (re) {
                alert(re.msg);
                if (re.success) {
                    $('input[name="topImagePath"]').val(re.data[0]);
                }
            });
        });

        $('input[name="upload_file1"]').change(function () {
            var files = $('input[name="upload_file1"]').prop('files');
            var data = new FormData();
            data.append('upload_file', files[0]);
            data.append('fileDirectory', 'news/top');

            AjaxUpload($uploadUrl, data, function (re) {
                alert(re.msg);
                if (re.success) {
                    $('input[name="topImagePath1"]').val(re.data[0]);
                }
            });
        });
        $('#saveBtn').click(function () {
            newsContent = editor.txt.html();
            $('#newsEditModal').trigger('save');
            //禁用按钮防止重复提交
            $('#saveBtn').attr("disabled","true");
        });

        //上传类型
        $('#newsForm').css('display','none')
        $("#myselect").change(function(){
            var opt=$("#myselect").val();
            if(opt==2){
                $('#newsWordForm').css('display','block');
            }else{
                $('#newsWordForm').css('display','none');
            }
            if(opt==1){
                $('#newsForm').css('display','block');
            }else{
                $('#newsForm').css('display','none');
            }

        });
    };
    initialize();



});