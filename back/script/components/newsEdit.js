$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    var initialize = function () {
        editor.customConfig.pasteFilterStyle = false;
        editor.customConfig.pasteIgnoreImg = false;
        editor.customConfig.uploadFileName = 'upload_file';
        editor.customConfig.uploadImgParams = {fileDirectory: 'news'};

        editor.customConfig.uploadImgMaxLength = 5;
        editor.customConfig.uploadImgServer = $uploadUrl;

        editor.create();

        editor.txt.html(newsContent);


        $('#formatText').click(function(){
            var content = editor.txt.html();
                content = content.replace(/\<\!\-\-(.*?)\-\-\>/gi,'');
                content = content.replace(/<[\s]*(script)[^>]*>.*?<[\s]*\/[\s]*(script)[\s]*>/gi,'');
                content = content.replace(/<[\s]*(style|title)[^>]*>.*?<[\s]*\/[\s]*(style|title)[\s]*>/gi,'');
                content = content.replace(/<[\s]*(meta|link|base)[^>]*>/gi,'');

                content = content.replace(/<[\s]*(object)[^>]*>.*?<[\s]*\/[\s]*(object)[\s]*>/gi,'');
                content = content.replace(/<[\s]*(embed|bgsound)[^>]*>/gi,'');
                content = content.replace(/(id|class|style|onclick|alt|title|width|height|_href|_src)\s*\=\'[^\>\']*?\'/gi,'');
                content = content.replace(/(id|class|style|onclick|alt|title|width|height|_href|_src)\s*\=\"[^\>\"]*?\"/gi,'');
                //content = content.replace(/(id|class|style|onclick|alt|title|width|height|_href|_src)\s*\=[^\>\s]+?/gi,'');
                content = content.replace(/[\s]+/gi,' ');
                content = content.replace(/[\s]+>/gi,'>');
                content = content.replace(/<([\/\s]*)(div)([^>]*)>/gi,'<$1p$3>');
                content = content.replace(/(<([\/\s]*)([^>]*)>)(\s|&nbsp;|\　|\ )+/gi,'$1');
                content = content.replace(/(\s*<[\s]*br[^>]*>\s*)+/gi,'<br/>');
                content = content.replace(/(^\s*<[\s]*br[^>]*>|<[\s]*br[^>]*>\s*$)/gi,'');
                content = content.replace(/(<[\/\s]*(p|hr|h1|h2|h3|h4|h5|h6)[^>]*>)\s*(<[\s]*br[^>]*>)/gi,'$1');
                content = content.replace(/(<[\s]*br[^>]*>)\s*(<[\/\s]*(p|hr|h1|h2|h3|h4|h5|h6)[^>]*>)/gi,'$2');
                contentre = content.replace(/<(!=iframe|embed|br|hr|tr|td)[^>]+>\s*<[\s]*\/[\s]*(!=iframe|embed|br|hr|tr|td)[^>]+>/gi,'');
                while(contentre != content){
                    content = contentre;
                    contentre = content.replace(/<(!=iframe|embed|br|hr|tr|td)[^>]+>\s*<[\s]*\/[\s]*(!=iframe|embed|br|hr|tr|td)[^>]+>/gi,'');
                }
                content = content.replace(/<([\/\s]*)(p)([^>]*)>(\s*<[^>]*(br)[^>]+>\s*)*<([\/\s]*)\/([\/\s]*)(p)([^>]*)>/gi,'');
                content = content.replace(/(<[\s]*p[^>]*>[\s]*)*(<[\s]*(embed|img|iframe)[^>]*>)([\s]*<[\s]*\/[\s]*p[^>]*>)*/gi,'<p style="text-align: center;">$2</p>');
                content = content.replace(/<([\/\s]*)(a)([^>]*)>/gi,'');
                //content = content.replace(/<([\/\s]*)(a)([^>]*)(!=href\=\"\/|href\=\'\/|href\=\/)([^>]*)>/gi,'');
            if($('#clearImg').is(':checked')){
                content = content.replace(/<([\/\s]*)(img)([^>]*)>/gi,'');
            }
            editor.txt.html(content)
        })



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
        $('#newsForm').css('display','none');

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