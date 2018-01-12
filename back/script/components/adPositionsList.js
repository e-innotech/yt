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
                initPage('pgAdPositionsList',$('#totalPgAdPositionsList'),re.data.total,getAdPositionsList);
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
    var getAdPositionsFromId = function (id) {
        for(var i=0;i<adPositionsList.length;i++){
            if(id == adPositionsList[i].id){
                return adPositionsList[i];
            }
        }
    };
    initialize();
});