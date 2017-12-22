/**
 * Created by admin on 2017/12/22.
 */
$.ajax({
    type:"get",
    url:"pinglun.json",
    dataType:"json",
    success:function(commentData){
        for(var i=0;i<commentData.length;i++){
            str="<tr><td>"+commentData[i].memberId+"</td>"+
            "<td>"+commentData[i].memberName+"</td>"+
            "<td>"+commentData[i].news_title+"</td>"+
            "<td>"+commentData[i].content+"</td>"+
            "<td>"+commentData[i].createDate+"</td>"+
            "<td><input type='button' value='删除' onclick='pinglundel(this)'/></td></tr>"
            $("tbody").append(str)
        }
    }
})

//评论中的查找
window.onload=function(){
    var oTab=document.getElementById("Tab");
    var btn=document.getElementById("findbtn");
//        console.log(111,select)
    btn.onclick=function(){
        var select=$("select").val();
        if(select=="会员名"){
            console.log(1333)
//                    console.log(333,oTab.tBodies[0].rows[0].cells[0].innerHTML)
            for(var i=0;i<oTab.tBodies[0].rows.length;i++){
                var str1=oTab.tBodies[0].rows[i].cells[0].innerHTML.toUpperCase();
                var str2=document.getElementById("findinput").value.toUpperCase();
                if(str1==str2){
                    $("#findinput").val("")
                    oTab.tBodies[0].rows[i].style.background='red';
                }else{
                    oTab.tBodies[0].rows[i].style.background='';
                }
            }
        }else if(select =="会员id"){
            console.log(444)
            for(var i=0;i<oTab.tBodies[0].rows.length;i++){
                var str1=oTab.tBodies[0].rows[i].cells[1].innerHTML.toUpperCase();
                var str2=document.getElementById("findinput").value.toUpperCase();
                if(str1==str2){
                    $("#findinput").val("")
                    oTab.tBodies[0].rows[i].style.background='red';
//                            return false
                }else{
                    oTab.tBodies[0].rows[i].style.background='';
                }
            }
        }else{
            for(var i=0;i<oTab.tBodies[0].rows.length;i++){
                var str1=oTab.tBodies[0].rows[i].cells[2].innerHTML.toUpperCase();
                var str2=document.getElementById("findinput").value.toUpperCase();
                if(str1==str2){
                    $("#findinput").val("")
                    oTab.tBodies[0].rows[i].style.background='red';
                }else{
                    oTab.tBodies[0].rows[i].style.background='';
                }
            }
        }
    }
    console.log(111,oTab.tBodies[0].rows);
}

//评论中的删除
function pinglundel(obj){
  // console.log($(obj).closest("tr").find("td")[0])
    var del=$(obj).closest("tr").find("td")[0];
    var objid=$(del).html()
    console.log(objid)
    $.ajax({
        type:"get",
        url:"pinglun.json",
        data:{objId:objid},
        success:function(pinglundata){
            console.log(111,pinglundata);

        }

    })
}