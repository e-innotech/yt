// var textarea = document.getElementById('textarea');
// var commitBtn = document.getElementById('Commit');
// var commentReview = document.getElementById('commentReview');
// commitBtn.on("click",function () {
// 	var textValue = textarea.value;
// 	 textarea.value = "";
// })




//点赞时换图片路径，并且下面的字隐藏
$(".zan #greatpic").on("click",function(){
	$(this).attr("src","../images/great.png");
	$(".zan p").css("display","none");

})

//点击收藏时，如上
$(".cang img").on("click",function(){
	$(this).attr("src","../images/collect.png");
	$(".cang p").css("display","none")
})