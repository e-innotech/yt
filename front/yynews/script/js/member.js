




//选项卡
$(".bottom ul").on("click", "li",function(){
	// console.log(1111)
   $(this).addClass("actives").siblings().removeClass("actives");
   $(".content-right .content-rightinside").eq($(this).index()).show().siblings().hide()
})





var initPage = function (total) {
    if(pageNum>1){
        return;
    }
    $.jqPaginator('#pg', {
        totalCounts:Number(total)==0?1:Number(total),
        pageSize:pageSize,
        visiblePages: 3,
        currentPage: pageNum,
        prev: '<p class="prev"><a href="javascript:;">上一页</a></p>',
        next: '<p class="next"><a href="javascript:;">下一页</a></p>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
//	            alert(type + '：' + num);
            if(type == 'change'){
                pageNum = num;
                //getList();
            }
        }
    });
};