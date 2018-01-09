$(function () {
    pageNum = 1;
    pageSize = 20;

    var userName = ''
    var resourceName = ''
    var startDate = ''
    var endDate = ''
    var action = ''
    var userList = [];
    var selectUser;


    var getUserList = function () {
        var data = {pageNum: pageNum, pageSize: pageSize};
        if (userName != '') {
            data.userName = userName;
        };
        if (resourceName != '') {
            data.resourceName = resourceName;
        };
        if (action != '') {
            data.action = action;
        };
        if(startDate!='' && endDate!=''){
            data.startDate = startDate;
            data.endDate = endDate;
        };
        AjaxFunc($query.log, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getUserList);
            } else {
                alert(re.msg);
            }
        });
    };






    var statusUser = function (id, status) {
        var data = {id: id, isUse: status};
        AjaxFunc($apiUrl + ctrl_upate, 'post', data, function (re) {
            if (re.success) {
                getUserList();
            }
            alert(re.msg);
        });
    };
    var initialize = function () {
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('ctrl_aduit')!=-1){
                ctrl_aduit = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('update')!=-1){
                ctrl_upate = nodeData.buttons[i].uri;
            };
        };

        $('#startDate').datetimepicker({
            format:'yyyy-mm-dd',
            language:'zh-CN',
            autoclose:true,
            todayBtn:true,
            todayHighlight:true,
            minView:'month'
        });
        $('#endDate').datetimepicker({
            format:'yyyy-mm-dd',
            language:'zh-CN',
            autoclose:true,
            todayBtn:true,
            todayHighlight:true,
            minView:'month'
        });
        $('#searchBtn').click(function () {
            userName = $('#userNameTxt').val();
            resourceName = $('#resourceNameTxt').val();
            startDate = $('#startDate').val();
            endDate = $('#endDate').val();
            getUserList();
        });
        getUserList();
    };
    var initTable = function (list) {
        userList = list;
        $('#userT').empty();
        for (var i = 0; i < list.length; i++) {
            console.log(1111,list)
            $('#userT').append('<tr>' +
            '<td>' + list[i].userName + '</td>' +
            '<td>' + list[i].resourceName + '</td>' +
            '<td>' + list[i].createdate + '</td>' +
            '<td>' + list[i].action + '</td>' +
            '</tr>');
        };
    };


    var isUserGroup = function (userGroup) {
        if (userGroup) {
            return userGroup.groupName;
        }
        return '';
    }

    initialize();
})