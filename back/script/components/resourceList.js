$(function () {
    pageNum = 1;
    pageSize = 20;
    var resourceName = '';

    var getResourceList = function(){
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(resourceName!=''){
            data.resourceName = resourceName;
        };
        $.ajax({
            type: "get",//请求方式
            url: $apiUrl+nodeData.uri,//请求路径
            async: false,
            dataType: "json", //数据格式
            xhrFields: {
                withCredentials: true
            },
            data:data,
            success: function (re) {
                if(re.success){
                    initTable(re.data.list);
                    initPage(re.data.total);
                }else{
                    alert(re.msg);
                }
            }
        });

    };
    var initialize = function () {
        $('#searchResourceBtn').click(function () {
            resourceName = $('#resourceListNameTxt').val();
            getResourceList();
        });
        getResourceList();
    };
    var initTable = function(list) {
        $('#resourceListT').empty();
        for(var i=0;i<list.length;i++){
            $('#resourceListT').append('<tr id="'+list[i].id+'">' +
                '<td>'+list[i].resourceName+'</td>'+
                '</tr>');


        };
        $('#resourceListT tr').click(function(){
            var id = this.id;
            var resouce;
            for(var j=0;j<list.length;j++){
                if(id == list[j].id){
                    resouce = list[j];
                }
            }
            $('input[name="pname"]').val(resouce.resourceName);
            $('input[name="parentId"]').val(resouce.id);
            $('#resourceListModal').modal('hide');
        });


    };
    var initPage = function (total) {
        if(pageNum>1){
            return;
        }
        $.jqPaginator('#pgResouce', {
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
                    getResourceList();
                }
                $('#totalPgResource').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    };
    initialize();
})