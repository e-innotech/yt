$(function () {
    console.log('nodeData::::'+nodeData.uri);
console.log(nodeData.uri)
    var userName = '';
    var userList = [];
    var ctrl_add = '';
    var ctrl_find = '';
    var ctrl_delete = '';
    var ctrl_updata = '';

    var getUserList = function(){
        var data = {pageSize:pageSize,pageNum:pageNum};
        if(userName!=''){
            data.userName = userName;
        }
        $.ajax({
            type: 'get',//请求方式
            url: $query.user,//请求路径
            async: false,
            dataType: 'json', //数据格式
            xhrFields: {
                withCredentials: true
            },
            data:data,
            success: function (re) {
                if(re.success){
                    //renderList(re.data);
                    initTable(re.data.list);
                    initPage(re.data.total);
                    console.log(111,re.data.total)
                }else{
                    alert(re.msg);
                }
                //ergodic(data.list.);
            }
        })
    }
    var initialize = function(){

        for(var i=0;i<nodeData.buttons.length;i++){//渲染按钮等功能的
            if(nodeData.buttons[i].uri.indexOf('add')){
                ctrl_add = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('find')){
                ctrl_find = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('delete')){
                ctrl_delete = nodeData.buttons[i].uri;
            };
            if(nodeData.buttons[i].uri.indexOf('updata')){
                ctrl_updata = nodeData.buttons[i].uri;
            };
        }
        if(ctrl_add != '') {
            $('#addUserBtn').show();
        };
        $('#searchBtn').click(function () {//查询
            userName = $('#userNameText').val();
            getUserList();
        });
        $('#addUserBtn').click(function () {//增加按钮
            $.get($components.userQuery,function (result) {
                $('#popPanel').html(result);
                $('#user_query_add').modal('show');
                $('#user_query_addBtn').click(function () {
                    var add = JSON.stringify({'userName':$('#user_query_add_userName').val(),'passWord':$('#user_query_add_passWord').val()});
                    $.ajax({
                        type: 'post',
                        url:$apiUrl+ctrl_add,
                        contentType:'application/json',//必须
                        data: add,
                        dataType: 'json',
                        xhrFields: {//必须
                            withCredentials: true
                        },
                        success: function(data) {
                            alert(data.msg);
                            if(data.success){
                                $('#user_query_add').modal('hide');
                            }
                        }
                    });
                });
            })
        });
        getUserList();


    }
    var initTable = function(list){//初始化表格
        $('#user_query').empty();//进来之前清空body
        userList = list;
        for(var i=0;i<list.length;i++){
            $('#user_query').append('<tr>' +
            "<td>" + list[i].id + "</td>" +
            "<td>" + list[i].userName + "</td>" +
            "<td>" + list[i].passWord + "</td>" +
            '<td><p class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +
            "<td>" + list[i].createDate + "</td>" +
            "<td>" + list[i].user_group_id + "</td>" +
            '</tr>');

        }
    }
    var initPage = function(total){//初始化分页

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
                    getUserList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    }
    initialize();//初始化







});
