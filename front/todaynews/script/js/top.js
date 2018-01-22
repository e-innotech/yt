/**
 * Created by admin on 2018/1/22.
 */
$(function() {
//搜索页面调用
    var searchData;
    var getSearchList = function (data) {
        searchData = data;
        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
        location.href = 'search.html';
    };
//搜索
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        console.log(7, newsTitle);
        if (newsTitle == '') {
            alert("请输入内容");
            return;
        }
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(3, newsTitle, 1, 5, getSearchList);
    });


    //添加样式
    //$(".nav a").each(function(){
    //    $this = $(this);
    //    if($this[0].href==String(window.location)){
    //        styles($this);
    //        //console.log(12,$this);
    //       // $this.addClass("active");  //hover表示被选中效果的类名
    //        //$("title").html($this.context.innerText);
    //    }
    //})
//
//function styles($this){
//    $($this).css("color","#f8fbfa");
//    $($this.parent()).css("background","#e73929");
//    console.log(3333);
//}







})