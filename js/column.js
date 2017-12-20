/**
 * Created by admin on 2017/12/20.
 */
$(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        async:true,
        url:"column.json",
        success:function(columndata){
            for(var i=0;i<columndata.length;i++){
                str='<tr><td>'+columndata[i].id+'</td>'+
                '<td>'+columndata[i].navigationName+'</td>' +
                '<td>'+columndata[i].desc+'</td>' +
                '<td><p class="'+(columndata[i].isUse==0?'anniu':'anniu active')+'"onclick="anniu(this)"><span> </span></p></td>' +
                '<td><input type="button" value=修改 /></td>'+
                '</tr>';
                $("tbody").append(str);
            }
        }
    })

})



//栏目的查找
function columnfind(){
    $.ajax({
        type:"get",
        dataType:"json",
        async:true,
        url:"column.json",
        success:function(columndata){
            console.log(columndata)
            var columnfind=$("#columnfind").val();
            for(var i=0;i<columndata.length;i++){
                if(columndata[i].navigationName==columnfind){
                    alert("找到了")
                    return false
                }
            }
            alert("没找到")
        }
    })
}