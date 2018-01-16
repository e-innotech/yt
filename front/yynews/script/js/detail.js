// var textarea = document.getElementById('textarea');
// var commitBtn = document.getElementById('Commit');
// var commentReview = document.getElementById('commentReview');
// commitBtn.on("click",function () {
// 	var textValue = textarea.value;
// 	 textarea.value = "";
// })


$(document).ready(function () {
	config.init();
	console.log(123456,Request["id"]);
	//新闻详情
	$.ajax({

		url: $yynews.detail + Request["id"] ,
		dataType: "json",
		async: true,
		type: "get",
		success: function (detailData) {

			var slide = detailData.data;
			console.log(666666666,slide)

				$('#content_left').append('<div class="news-top">' +
				'<h2>'+ slide.newsTitle +'</h2>' +
					'<div class="dateinfo">' +
					'<div class="time">'+
					'发布时间:' +
					'<span>'+ slide.createDate +'</span>' +
					'</div>' +
					'<div class="info">' +
					'<p class="review">' +
					'<img src="../../images/ping.png"/>' +
					'<span>' +  +'</span>' +
					'</p>' +
					'<p class="read">' +
					'<img src="../../images/yan.jpg"/>' +
					'<span>' +  +'</span>' +
					'</p>' +
					'</div>' +
					'</div>' +
					'</div>');

			$('#content_center').append('<div class="news-top">' +slide.content+'</div>');
		}




	});


	var a={
		"membersId": 13
	}


	//写品论
	console.log(111001010101,Request["nav"])
	console.log(98989898989898,Request["id"])
	$.ajax({
		url:$members.commentadd + "/3/" + Request["id"] +"?pageNum=1&pageSize=10",
		data:{content:$('#textarea').val()},
		dataType: "json",
		async: true,
		type: "get",
		success: function (detailData) {
			console.log(89797124979,detailData)
			var slide = detailData.data;

			$('.commentCon-box').append('<div class="commentCon">' +
			'<div class="commentCon-l">' +
			'<img src="" alt="沙发">' +
			'<img src="" alt="图像">' +
			'<p class="call">'+  +'</p>' +
			'<p class="date">'+  +'</p>' +
			'</div>' +
			'<div class="commentCon-r">' +
			'<p>' +
			'<span>'+ (i+1) +'楼</span>' +
			'</p>' +
			'<div class="commentbox">'+ +'</div>' +
			'</div>' +
			'</div>')
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