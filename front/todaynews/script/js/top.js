/**
 * Created by admin on 2018/1/22.
 */
$(function() {
////搜索页面调用
//    var searchData;
//    var getSearchList = function (data) {
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
    //搜索页面调用
    var searchData;
    var getSearchList = function (data) {
        searchData = data;
        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
        var newsKey = $('input[name="search"]').val();
        window.sessionStorage.setItem("global_search_data_searchKey", newsKey);
        location.href = 'search.html';
    };
    //搜索
    $("#search").click(function () {
        var newsKey = $('input[name="search"]').val();
        if (newsKey == '') {
            window.sessionStorage.removeItem("global_search_data");
            location.href = 'search.html';
            return;
        }
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(websiteId, newsKey, 1, 10, getSearchList);
    });

})