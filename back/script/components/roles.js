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
        for(var i=0;i<list.length;i++){
            $('#ListBox').append('<label class="btn btn-default" style="margin-left: 10px;" ><input type="checkbox" >' + list[i].roleName + '</label>');
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