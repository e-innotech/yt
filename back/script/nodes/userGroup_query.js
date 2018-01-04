$(function () {
    console.log('nodeData::::'+nodeData.uri);

    var groupName = '';
    var userList = [];
    var permission = [];

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
                }else{
                    alert(re.msg);
                }
                //ergodic(data.list.);
            }
        })
    }
    var initialize = function(){
        getUserList();
        for(var i=0;i<nodeData.buttons.length;i++){
            if(nodeData.buttons[i].uri.indexOf('add')!=-1){
                $('#addUserBtn').show();
            }
        }
    }
    var initTable = function(list){
        $('#userGroup_query').empty();
        userList = list;
        for(var i=0;i<list.length;i++){
            str = "<tr>" +
            "<td>" + Data[i].id + "</td>" +
            "<td>" + Data[i].groupName + "</td>" +
            "<td>" + Data[i].desc + "</td>" +
            '<td><p class="' + (Data[i].isUse == 0 ? 'anniu' : 'anniu active') + '" style="margin: 0 auto;" onclick="anniu(this)"><span> </span></p></td>' +  //+ resultdata[i].isUse +
            "<td>" + Data[i].user_group_id + "</td>" +
            "<td>" +
            "<input type='button' value='删除' onclick='delanniu(this)'/>" +
            "<input type='button' value='修改' onclick='reviselist(this)'/>" +
            "</td></tr>";
            $('#userGroup_query').append(str);//
        }
    }
    var initPage = function(total){
    }
    initialize();








});

