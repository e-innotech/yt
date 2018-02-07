$(document).ready(function(){
    //声明一个变量代表第几页
    var page=1;
    var totalnum;
    function getNavList() {
        //传的权限 页数 一页显示几条数据
        newsList(3, page,10, function callback(data){
            console.log(26,data);
            totalnum=data.total;
            var NavList=data.list;
            for (var i = 0; i < NavList.length; i++){
                $('#navList').append(
                    '<li>' +
                    '<a href="#">' +
                    '<div>' +
                    '<h3>'+NavList[i].newsTitle+'</h3>' +
                    '<div class="list_info">' +
                    '<span>发布时间：</span>' +
                    '<span class="time">'+NavList[i].createDate+'</span>' +
                    '</div>' +
                    '</div>' +
                    '</a>' +
                    '</li>')
            }
        })
    };
//点击上一页时
    $('.prev').click(function (){
        if(page==1){
            page=1;
            $(this).attr("disabled","true")
        }else{
            $("#navList").html('');
            page--;
            getNavList();
        }
        $('.next').attr("disabled",false);
    });
    //点击下一页时
    $('.next').click(function (){
        if(page>=Math.ceil(totalnum/10)){
            $(this).attr("disabled","true");
            return
        }else{
            $("#navList").html('');
            page++;
            getNavList();
        }
        $('.next').attr("disabled",false);
    });
})

