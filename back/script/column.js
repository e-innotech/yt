//栏目页面的渲染
var json_data ;
$(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        async:true,
        url:"column.json",
        success:function(columndata){
            json_data = columndata;
            renderList();
        }
    })
})
function renderList(){
    $("tbody").empty();
    for(var i=0;i<json_data.length;i++){
        str='<tr><td>'+json_data[i].id+'</td>'+
        '<td>'+json_data[i].navigationName+'</td>' +
        '<td>'+json_data[i].desc+'</td>' +
        '<td><p class="'+(json_data[i].isUse==0?'anniu':'anniu active')+'"onclick="anniu(this)"><span> </span></p></td>' +
        '<td><input type="button" value="修改" onclick="revisecolumn(this)" /><input type="button" value="编辑栏目" /></td>'+
        '</tr>';
        $("tbody").append(str);
    }
}

//栏目的增加
//点击增加按钮时出现弹出框
$(".addModal").click(function(){
    $("#myModal").css("display","block");
})
//点击确认按钮时
$(".modal-footer .btn1").click(function(){
     var newcolumn = $("#addcolumninput1").val();
        console.log(newcolumn)
        console.log(newcolumn)
    for(var i=0;i<json_data.length;i++){
        if(newcolumn == json_data[i].navigationName){
            alert('栏目存在');
            return;
        }
    }
    json_data.push({navigationName:newcolumn});
    console.log(json_data);
    renderList();
    $("#addcolumninput1").val("");
    $("#addcolumninput2").val("");
    $("#text1").html("");
    $("#myModal").css("display","none");
})

//点击关闭按钮时
$(".modal-footer .btn2").click(function(){
    $("#myModal").css("display","none");
})

//栏目文本框的验证
//当文本框获取焦点时
$("#addcolumninput1").focus(function(){
    $("#text1").html("请输入栏目名称")

})
//当文本框失去焦点时
$("#addcolumninput1").blur(function(){
    var columne=/^[\u4e00-\u9fa5]{3}$/;
    var column=$(this).val();
    if (column == '') {
        $("#text1").html("栏目不能为空！");
    } else if (!column.match(columne)) {
        $("#text1").html("栏目应输入三个汉字！");
    } else {
        $("#text1").html("正确！");
    }


})

//栏目的查找
function columnfind(){
    var columnfind=$("#columnfind").val();
    if(columnfind==''){
        $("#columnfinds").html("未输入任何字段")
    }else{
        $.ajax({
            type:"get",
            dataType:"json",
            async:true,
            data:{"column":columnfind},
            url:"column.json",
            success:function(columndata){
                console.log(columndata)
                var columnfind=$("#columnfind").val();
                for(var i=0;i<columndata.length;i++){
                    if(columndata[i].navigationName==columnfind){
                        alert("找到了");
                        $("#columnfinds").html("");
                        $("#columnfind").val("");
                        return false
                    }
                }
                alert("没找到");
                $("#columnfinds").html("");
            }
        })
    }
}

//栏目的修改
function revisecolumn(obj){
    //console.log(obj)
    $("#revisecolumn").css("display","block");
    var columnid=$(obj).parent().prev().prev().prev().prev().prev().html();
    var acolumnname=$(obj).parent().prev().prev().prev().prev();
    var bdescription=$(obj).parent().prev().prev().prev();
    //console.log(111,acolumnname,222)
    //console.log(2222222222223,bdescription)
    $("#revisecolumninput1").val(acolumnname.html());
    $("#description").val(bdescription.html());
    //点击确认按钮时
    $(".modal-footer .btntrue").click(function(){
        //把上送数据放到一个空对象中
        var revise={};
        revise["id"]=columnid;
        revise["columnname"]=$("#revisecolumninput1").val();
        revise["columndescription"]=$("#description").val();
        //console.log(222,revise)
        acolumnname.html($("#revisecolumninput1").val());
        bdescription.html($("#description").val());
        $("#revisecolumn").css("display","none");
    })
}
//点击关闭按钮时
$(".modal-footer .btnclose").click(function(){
    $("#revisecolumn").css("display","none");

})

