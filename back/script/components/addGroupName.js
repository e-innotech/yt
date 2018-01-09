
$(function () {


    var userGroup = '';
    var getList = [];
    var selectUser;

    var getUserList = function () {
        var data = {pageNum:pageNum,pageSize:pageSize};
        if(userGroup!=''){
            data.userGroup = userGroup;
        };
        AjaxFunc($query.userGroup,'get',data,function (re) {
            if(re.success){
                initTable(re.data.list);
            }else{
                alert(re.msg);
            }
        });
    };
    var initialize = function () {getUserList()}

    var initTable = function (list) {
        getList = list;
        $('#addGroupNameT').empty();
        for(var i=0;i<list.length;i++){
            console.log(111111,list[i])
            $('#addGroupNameT').append('<button id="btn_'+list[i].id+'" style="margin: 5px 10px">'+  isUserGroup(list[i].groupName) +'</button>');
            $('#btn_'+list[i].id).click(function () {
                selectUser = getFromId(this.id.split('_')[1]);
                $('#userGroupBtn').val(selectUser.groupName);
                //console.log(selectUser.id)userGroupId:$('input[name="groupName"]').val()
                $('input[name="userGroupId"]').val(selectUser.id);
                //console.log(77777,$('input[name="groupName"]').val())
                $('#userGroupModal').modal('hide');
            });
        }
    };
    var isUserGroup = function(groupName){
        if(groupName){
            return groupName;
        }
        return '';
    }

    var getFromId = function (id) {
        for(var i=0;i<getList.length;i++){
            if(id == getList[i].id){
                return getList[i];
            }
        }
    };
    initialize();
});

