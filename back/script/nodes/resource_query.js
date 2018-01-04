$(function () {

    var resourceName = '';
    var resourceList = [];

    var ctrl_add = 0;
    var ctrl_upate = 0;
    var ctrl_delete = 0;
    const RW = ['只读','读写'];
    const MENU = ['否','是'];

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
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('add')){
                ctrl_add = 1;
            };
            if(nodeData.buttons[i].uri.indexOf('update')){
                ctrl_upate = 1;
            };
            if(nodeData.buttons[i].uri.indexOf('delete')){
                ctrl_delete = 1;
            };
        }
        if(ctrl_add == 1) $('#addResourceBtn').show();
        getResourceList();
    };
    var initTable = function(list) {
        resourceList = list;
        $('#resourceT').empty();
        for(var i=0;i<list.length;i++){
            $('#resourceT').append('<tr>' +
                '<td>'+list[i].id+'</td>' +
                '<td>'+list[i].parentId+'</td>'+
                '<td>'+list[i].resourceName+'</td>'+
                '<td>'+list[i].uri+'</td>'+
                '<td>'+RW[list[i].rw]+'</td>'+
                '<td>'+MENU[list[i].isMenu]+'</td>'+
                '<td>'+(ctrl_upate==1?'<button id="editBtn_'+list[i].id+'">编辑</button>':'')+(ctrl_delete==1?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
                '</tr>');

            $('#eidtBtn_'+list[i].id).click(function () {

            });
            $('#deleteBtn_'+list[i].id).click(function () {

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
                    getResourceList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    };
    initialize();
})