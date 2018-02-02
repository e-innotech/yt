$(document).ready(function() {
//搜索页面调用
    var searchData;
    var page=1
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
        globalQuery(1,newsTitle,page, 5, getSearchList);
    });
    //添加样式
    var urlstr = location.href;
    //alert((urlstr + '/').indexOf($(this).attr('href')));
    var urlstatus=false;
    $(".nav a").each(function () {
        if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
            $(this).addClass('active'); urlstatus = true;
        } else {
            $(this).removeClass('active');
        }
    });
    if (!urlstatus) {$(".nav a").eq(0).addClass('active'); }
});