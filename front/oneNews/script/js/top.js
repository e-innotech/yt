$(document).ready(function() {

//搜索页面调用
    var searchData;
    var getSearchList = function (data) {
        searchData=data;
        window.sessionStorage.setItem("global_search_data", JSON.stringify(data));
        location.href = 'search.html';
    };
    //搜索
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        if(newsTitle==''){
            alert("请输入内容");
            return;
        }
        //参数 网站id 新闻标题 当前页 显示页
        globalQuery(2,newsTitle,1, 10, getSearchList);
    });

});