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
        if (startDate != '') {
            data.startDate = startDate;
        };
        if (endDate != '') {
            data.endDate = endDate;
        };
        if (action != '') {
            data.action = action;
        };
        AjaxFunc($query.log, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                initPage(re.data.total);
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
            startDate = $('#datetimepicker_begin').val();
            endDate = $('#datetimepicker_end').val();
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
            '<td>' + list[i].startDate + '</td>' +
            '<td>' + list[i].endDate + '</td>' +
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
    var initPage = function (total) {
        if (pageNum > 1) {
            return;
        }
        $.jqPaginator('#pg', {
            totalCounts: Number(total) == 0 ? 1 : Number(total),
            pageSize: pageSize,
            visiblePages: 3,
            currentPage: pageNum,
            first: '<li class="first"><a href="javascript:;"><<</a></li>',
            prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
            next: '<li class="next"><a href="javascript:;">下一页</a></li>',
            last: '<li class="last"><a href="javascript:;">>></a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            onPageChange: function (num, type) {
//	            alert(type + '：' + num);
                if (type == 'change') {
                    pageNum = num;
                    getUserList();
                }
                $('#totalPg').text('当前第' + pageNum + '页 共' + Math.ceil(total / pageSize) + '页（每页' + pageSize + '条 共：' + total + '条）');
            }
        });
    };
    initialize();
})