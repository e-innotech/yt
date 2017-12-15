/**
 * Created by admin on 2017/12/15.
 */
$(function(){

    $(".del>.btn1").click(function () {


        //console.log(666, deltd)
        //$.ajax({
        //    type: 'get',
        //    url: 'http://192.168.20.195:8080/user/' + deltd,
        //    dataType: "json",//数据格式
        //    //data: JSON.stringify(search),
        //    success: function () {
        //        $(".alertbox").hide();
        //        window.location.reload();
        //        //$(".add").hide();
        //    }
        //})
        //$(e.target).closest("tr").remove();
        //$(".alertbox").hide();
    })
    $(".del>.btn2").click(function () {
        $(".alertbox").hide();
        $(".del").hide();
    })
})