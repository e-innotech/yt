/**
 * Created by admin on 2018/1/22.
 */

    $(function() {
        //搜索
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
    })
