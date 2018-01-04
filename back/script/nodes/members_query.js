$(function () {
    console.log('nodeData::::'+nodeData.uri);
    console.log(nodeData.uri)
    var uname = '';
    var ctrl_add = '';
    var ctrl_add = '';
    var ctrl_status = '';
    var userList = [];
    const RW = ['只读','读写'];
    const MENU = ['否','是'];

    var getMemberList = function(){
        var data = {pageSize:pageSize,pageNum:pageNum};
        if(uname!=''){
            data.uname = uname;
        }
        $.ajax({
            type: 'get',//请求方式
            url: 'http://123.59.156.27:8080/members/query',//请求路径
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
            if(nodeData.buttons[i].uri.indexOf('status')){
                ctrl_status = nodeData.buttons[i].uri;
            };
            if(ctrl_add != '') {
                $('#addMemberBtn').show();
            };
        }
        $('#addMemberBtn').click(function () {//增加按钮
            $.get($components.memberQuery,function (result) {
                $('#popPanel').html(result);
                $('#user_query_add').modal('show');
                $('#user_query_addBtn').click(function () {
                    var add = JSON.stringify({'userName':$('#user_query_add_userName').val(),'passWord':$('#user_query_add_passWord').val()});
                    $.ajax({
                        type: 'POST',
                        url:'http://123.59.156.27:8080/user/add',
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
        getMemberList();


    }
    var initTable = function(list){//初始化表格
        $('#member_query').empty();//进来之前清空body
        userList = list;
        for(var i=0;i<list.length;i++){
            str = "<tr>" +
            "<td>" + list[i].id + "</td>" +
            "<td>" + list[i].uname + "</td>" +
            "<td>" + list[i].pwd + "</td>" +
            "<td>" + list[i].regDate + "</td>" +
            '<td>'+MENU[list[i].isUse]+'</td>' +
            '<td>'+MENU[list[i].isGag]+'</td>' +
            '<td>'+(ctrl_upate==1?'<button id="updataBtn_'+list[i].id+'">修改会员信息</button>':'')+(ctrl_upate==1?'<button id="updataBtn_'+list[i].id+'">修改会员密码</button>':'')+'</td>'+
            "</tr>";
            $('#member_query').append(str);//
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
                    getMemberList();
                }
                $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
            }
        });
    }
    initialize();//初始化











});
