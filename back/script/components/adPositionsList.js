$(function () {

    pageNum = 1;
    pageSize = 10;


    var adName = '';
    var adPositionsList = [];
    var selectAdPositions;

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

    var initialize = function () {
        $('#searchAdPositionsListBtn').click(function () {
            adName = $('#adPositionsListNameTxt').val();
            getAdPositionsList();
        });
        getAdPositionsList();
    };
    var initTable = function (list) {
        adPositionsList = list;
        $('#adPositionsListT').empty();
        for(var i=0;i<list.length;i++){
            $('#adPositionsListT').append('<tr id="ap_'+list[i].id+'">' +
                '<td>'+list[i].adName+'</td>'+
                '<td>'+list[i].adWidth+'</td>'+
                '<td>'+list[i].adHeight+'</td>'+
                '<td>'+list[i].remark+'</td>'+
                '</tr>');
            $('#ap_'+list[i].id).click(function () {
                selectAdPositions = getAdPositionsFromId(this.id.split('_')[1]);
                $('#adPositionsInfo').val(selectAdPositions.adName+"："+selectAdPositions.adWidth+"X"+selectAdPositions.adHeight);
                $('input[name="adPositionsId"]').val(selectAdPositions.id);
                $('#adPositionsListModal').modal('hide');
            });

        }
    };
    var initPage = function (total) {
        if(pageNum>1){
            return;
        }
        $.jqPaginator('#pgAdPositionsList', {
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
                $('#totalPgAdPositionsList').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
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
    initialize();
});