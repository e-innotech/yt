

//进网站加载个人资料
window.onload=function(){
	$('#top').load('top.html');
	$("#content-rightinside").load("peopleinfo.html");
    $('.footer-bg').load('bottom.html');
	$("#personData").addClass("actives");
}	


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
        first: '<li class="first"><a href="javascript:;"><<</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        last: '<li class="last"><a href="javascript:;">>></a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
//	            alert(type + '：' + num);
            if(type == 'change'){
                pageNum = num;
                getList();
            }
            $('#totalPg').text('当前第'+pageNum+'页 共'+Math.ceil(total/pageSize)+'页（每页'+pageSize+'条 共：'+total+'条）');
        }
    });
};