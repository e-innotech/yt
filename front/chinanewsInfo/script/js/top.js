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
//点击搜索按钮时
    $("#search").click(function () {
        alert(111);
        var newsTitle = $('input[name="search"]').val();
        console.log(7, newsTitle);
        if (newsTitle == '') {
            alert("请输入内容");
            return;
        }
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(1, newsTitle, 1, 5, getSearchList);
    });

})