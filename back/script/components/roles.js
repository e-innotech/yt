$(function () {

    var roleList = [];
    var selectList = [];
    var roleName = '';


    var getRoleList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(roleName!=''){
            data.roleName = roleName;
        };
        AjaxFunc($query.role,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
            }else{
                alert(re.msg);
            }
        });
    };
    var initTable = function(list) {
        roleList = list;
        $('#ListBox').empty();
        for(var i=0;i<list.length;i++){//多选<label class="btn btn-default" style="margin-left: 10px;" ><input type="checkbox" >' + list[i].roleName + '</label>
            $('#ListBox').append('<button id="btn_'+list[i].id+'" style="margin: 5px 10px">'+  list[i].roleName +'</button>');
            $('#btn_'+list[i].id).click(function () {
                selectAdPositions = getAdPositionsFromId(this.id.split('_')[1]);
                $('#adPositionsInfo').val(selectAdPositions.roleName);
                $('input[name="adPositionsId"]').val(selectAdPositions.id);
                $('#adPositionsListModal').modal('hide');
            });
        };

    };



    var initialize = function () {
        if (userGroupListType = 'parent') {
            $('#resourceListFooter').show();
            $('#resourceListBtn').click(function () {

                $('#resourceListModal').modal('hide');
            });
        }
        ;
        getRoleList();
    };



    initialize();
})