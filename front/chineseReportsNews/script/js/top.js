$(document).ready(function() {
    //点击搜索时，获取输入框的值，
    $("#search").click(function () {
        var newsTitle = $('input[name="search"]').val();
        if(newsTitle==''){
            alert("请输入内容");
            return;
        }
        window.sessionStorage.setItem("global_newsTitle", newsTitle);
        location.href = 'search.html';
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