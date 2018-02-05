/**
 * Created by admin on 2018/1/22.
 */
//$(function() {
//    var searchData;
//    var getSearchList = function (data) {
//        console.log(121,data);
//        searchData=data;
//        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
//        location.href = 'search.html';
//    };
//    //搜索
//    $("#search").click(function () {
//        var newsTitle = $('input[name="search"]').val();
//        if(newsTitle==''){
//            alert("请输入内容");
//            return;
//        }
//        //参数 网站id 新闻标题 当前页 显示页
//        globalQuery(1,newsTitle,1, 10, getSearchList);
//    });
//})
$(function() {
    //搜索
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        if(newsTitle==''){
            alert("请输入内容");
            return;
        }
        window.sessionStorage.setItem("global_newsTitle", newsTitle);
        location.href = 'search.html';
    });
})