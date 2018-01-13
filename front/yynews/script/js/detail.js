// var textarea = document.getElementById('textarea');
// var commitBtn = document.getElementById('Commit');
// var commentReview = document.getElementById('commentReview');
// commitBtn.on("click",function () {
// 	var textValue = textarea.value;
// 	 textarea.value = "";
// })


$(document).ready(function () {

	//新闻详情
	$.ajax({
		url: "../data/detail.json",
		dataType: "json",
		async: true,
		type: "get",
		success: function (detailData) {
			var slide = detailData.data.list;
			for (var i = 0; i < slide.length; i++) {
				$('#content').append('<div class="news-top">' +
				'<h2>'+ slide[i].news_title +'</h2>' +
					'<div class="dateinfo">' +
					'<div class="time">'+
					'发布时间:' +
					'<span>'+ slide[i].create_date +'</span>' +
					'</div>' +
					'<div class="info">' +
					'<p class="review">' +
					'<img src="../../images/ping.png"/>' +
					'<span>' + slide[i].comment +'</span>' +
					'</p>' +
					'<p class="read">' +
					'<img src="../../images/yan.jpg"/>' +
					'<span>' + slide[i].read +'</span>' +
					'</p>' +
					'</div>' +
					'</div>' +
					'</div>');

			}
		}
	})


//点赞时换图片路径，并且下面的字隐藏
	$(".zan #greatpic").on("click", function () {
		$(this).attr("src", "../images/great.png");
		$(".zan p").css("display", "none");

	})

//点击收藏时，如上
	$(".cang img").on("click", function () {
		$(this).attr("src", "../images/collect.png");
		$(".cang p").css("display", "none")
	})

});