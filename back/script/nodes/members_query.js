$(function () {


    var uname = '';
    var isGag = '';
    var isUse = '';
    var userList = [];
    var selectUser;

    var ctrl_upate = '';

    var getUserList = function () {
        var data = {pageNum: pageNum, pageSize: pageSize};
        if (uname != '') {
            data.uname = uname;
        };
        if (isUse != '') {
            data.isUse = isUse;
        };
;
        if (isGag != '') {
            data.isGag = isGag;
        };

        AjaxFunc($query.members, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                initPage('pg',$('#totalPg'),re.data.total,getUserList);
            } else {
                alert(re.msg);
            }
        });
    };


    /*状态*/
    var statusUser = function (id, status) {
        var data = {id: id, isUse: status};
        AjaxFunc($apiUrl + ctrl_upate, 'post', data, function (re) {
            if (re.success) {
                getUserList();
            }
            alert(re.msg);
        });
    };
    /*禁言*/
    var getisGag = function (id, gag) {
        var data = {id: id, isGag: gag};
        AjaxFunc($apiUrl + ctrl_upate, 'post', data, function (re) {
            if (re.success) {
                getUserList();
            }
            alert(re.msg);
        });
    };



    var initialize = function () {
        for (var i = 0; i < nodeData.buttons.length; i++) {

            if (nodeData.buttons[i].uri.indexOf('update') != -1) {
                ctrl_upate = nodeData.buttons[i].uri;
            };
        }
        $('#searchBtn').click(function () {
            uname = $('#userTxt').val();
            getUserList();
        });
        getUserList();
    };
    var initTable = function (list) {
        userList = list;
        $('#userT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#userT').append('<tr>' +
            '<td>' + list[i].uname + '</td>' +
            '<td>' + list[i].regDate + '</td>' +
            '<td><p id="statusBtn_' + list[i].id + '" class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '"><span></span></p></td>' +
            '<td><p id="GagBtn_' + list[i].id + '" class="' + (list[i].isGag == 0 ? 'isGag' : 'isGag active') + '"><span></span></p></td>' +
            '</tr>');


            $('#statusBtn_' + list[i].id).click(function () {
                //console.log($(this).attr('class'));
                var isUse = 0;
                if ($(this).attr('class') == 'anniu') {
                    isUse = 1;
                } else {
                    isUse = 0;
                };
                statusUser(this.id.split('_')[1], isUse)
            });
            $('#GagBtn_' + list[i].id).click(function () {
                //console.log($(this).attr('class'));
                var isGag = 0;
                if ($(this).attr('class') == 'isGag') {
                    isGag = 1;
                } else {
                    isGag = 0;
                }
                ;
                getisGag(this.id.split('_')[1], isGag)
            });
        };
    };

    initialize();
})