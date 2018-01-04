$(function () {
    console.log('nodeData::::'+nodeData.uri);
    var groupName = '';
    var userList = [];
    var ctrl_add = 0;
    var ctrl_find = 0;
    var ctrl_delete = 0;

    var getUserList = function(){
        var data = {pageSize:pageSize,pageNum:pageNum};
        if(groupName!=''){
            data.groupName = groupName;
        }
        $.ajax({
            type: "get",//请求方式
            url: $apiUrl+nodeData.uri,//请求路径
            async: false,
            dataType: "json", //数据格式
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
                ctrl_add = 1;
            };
            if(nodeData.buttons[i].uri.indexOf('find')){
                ctrl_find = 1;
            };
            if(nodeData.buttons[i].uri.indexOf('delete')){
                ctrl_delete = 1;
            };
        }
        if(ctrl_add == 1) {$('#addUserBtn').show()};
        getUserList();
        $('#addUserBtn').click(function(){            //增加按钮的事件
            $.get($components.userGroup,function (result) {
                $('#popPanel').html(result);
                $('#userGroup_add').modal('show');
                $('#userGroup_addBtn').click(function () {
                    var add = JSON.stringify({'userName':$('#userGroup_userName').val(),'passWord':$('#userGroup_passWord').val()});
                    console.log(99,$apiUrl+nodeData)
                    $.ajax({
                        type: 'POST',
                        url:'http://123.59.156.27:8080/userGroup/add',
                        contentType:'application/json',//必须
                        data: add,
                        dataType: 'json',
                        xhrFields: {//必须
                            withCredentials: true
                        },
                        success: function(data) {
                            alert(data.msg);
                            if(data.success){
                                $('#userGroup_add').modal('hide');
                            }
                        }
                    });
                });
            })
        });
    }
    var initTable = function(list){//初始化表格
        $('#userGroup_query').empty();//进来之前清空body
        userList = list;
        for(var i=0;i<list.length;i++){
            str = "<tr>" +
            "<td>" + list[i].id + "</td>" +
            "<td>" + list[i].groupName + "</td>" +
            "<td>" + list[i].desc + "</td>" +
            '<td><p class="' + (list[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +  //+ resultdata[i].isUse +
            "<td>" + list[i].user_group_id + "</td>" +
            '<td>'+(ctrl_find==1?'<button id="findBtn_'+list[i].id+'">修改</button>':'')+(ctrl_delete==1?'<button id="deleteBtn_'+list[i].id+'">删除</button>':'')+'</td>'+
            "</tr>";
            $('#updataBtn_'+list[i].id).click(function () {
                $.get($components.pwdReset,function (result) { //修改按钮的事件
                    $('#popPanel').html(result);
                    $('#userGroup_updata').modal('show');
                    $('#userGroup_updataBtn').click(function () {
                        var add = JSON.stringify({'userName':$('#userGroup_updata_userName').val(),'passWord':$('#userGroup_updata_passWord').val()});
                        console.log(99,$apiUrl+nodeData)
                        $.ajax({
                            type: 'PUT',
                            url:'http://123.59.156.27:8080/userGroup/update',
                            contentType:'application/json',//必须
                            data: add,
                            dataType: 'json',
                            xhrFields: {//必须
                                withCredentials: true
                            },
                            success: function(data) {
                                alert(data.msg);
                                if(data.success){
                                    $('#userGroup_updata').modal('hide');
                                }
                            }
                        });
                    });
                })
            });
            $('#deleteBtn_'+list[i].id).click(function () {
                //删除按钮的功能
            });
            $('#userGroup_query').append(str);//
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
