// var textarea = document.getElementById('textarea');
// var commitBtn = document.getElementById('Commit');
// var commentReview = document.getElementById('commentReview');
// commitBtn.on("click",function () {
// 	var textValue = textarea.value;
// 	 textarea.value = "";
// })


$(document).ready(function () {
	var publishId = getIdFromUrl();
	var page = 1;
	var commentQuery = function () {
		commentDetailList(publishId, page, 5, initCommentList);
	};
	commentQuery();
	var user = JSON.parse(sessionStorage.getItem('user'));
	newsDetail(publishId, function callback(data) {
		$("title").html(data.newsTitle);
		$('#content_left').append('<div class="news-top">' +
		'<h2>' + data.newsTitle + '</h2>' +
		'<div class="dateinfo">' +
		'<div class="times">' +
		'发布时间:' +
		'<span>' + data.createDate + '</span>' +
		'</div>' +
		'<div class="info">' +
		'<p class="review">' +
		'<img src="../images/ping.png"/>' +
		'<span>' + data.comment + '</span>' +
		'</p>' +
		'<p class="read">' +
		'<img src="../images/yan.jpg"/>' +
		'<span>' + data.collect + '</span>' +
		'</p>' +
		'</div>' +
		'</div>' +
		'</div>');

		$('#content_center').append('<div class="news-top">' + data.content + '</div>');
		if (user != null) {
			$('.infoName').append('<img src="' + user.infos.icon + '">' + '<span>' + user.uname + '</span>');
		}
		$('.commentAdd').append('<textarea id="textarea_str" placeholder="评论字数限50个以内" onkeyup="countCode(this,50)"></textarea>' +
		'<input type="button"  id="commitBtn" alt="' + data.publishId + '" value="提交评论">');

	});
	$("#commitBtn").click(function () {
		var content = $('#textarea_str').val();
		commentAdd(this.alt, content, function callback(data) {
			if (data.success) {
				$('#textarea_str').val('');
				alert("评论成功！");
				commentQuery();
			}
		});
	});
	$('.more').click(function () {
		page++;
		commentQuery();
	});
	function initCommentList(data) {
		$('#commentCon-box').html('');
		for (var i = 0; i < data.list.length; i++) {
			$('.small:first').css('display', 'block');
			$('#commentCon-box').append('<div class="commentCon">' +
			'<div class="commentCon-l">' +
			'<img class="big" src="' + data.list[i].membersPic + '" alt="图像">' +
			'<img class="small" src="../images/sha.png" alt="沙发">' +
			'<p class="call">' + data.list[i].nickName + '</p>' +
			'<p class="date">' + data.list[i].createDate + '</p>' +
			'</div>' +
			'<div class="commentCon-r">' +
			'<p>' +
			'<span>' + (i + 1) + '楼</span>' +
			'</p>' +
			'<div class="commentbox">' + data.list[i].content + '</div>' +
			'</div>' +
			'</div>');
		}

	//var getCommentList = function() {
	//	commentList(publishId, pag, 5,function callback(data){
	//		for(var i=0;i<data.list.length;i++){
	//			$('.commentCon-box').append('<div class="commentCon">' +
	//		'<div class="commentCon-l">' +
	//		'<img src="" alt="沙发">' +
	//		'<img src="" alt="图像">' +
	//		'<p class="call">'+  +'</p>' +
	//		'<p class="date">'+  +'</p>' +
	//		'</div>' +
	//		'<div class="commentCon-r">' +
	//		'<p>' +
	//		'<span>'+ (i+1) +'楼</span>' +
	//		'</p>' +
	//		'<div class="commentbox">'+data.list[i]. +'</div>' +
	//		'</div>' +
	//		'</div>')
	//		}
	//	});
	//}
	$('#commitBtn').click(function(){

	});






//	var commentReview = document.getElementById('commentCon-box');
//	$('#commitBtn').onclick = function () {
//		if (sessionStorage.getItem("synUser") !== null) {
//			$.ajax({
//				url: $members.commentadd,
//				//上送数据 网站id 会员ID 新闻ID 输入框的内容
//				data: {"websiteId": 3, "membersId": 1, "newsId": 0, "content": encodeURI($('#textValue'))},
//				dataType: "json",
//				async: true,
//				type: "get",
//				success: function (datas) {
//					console.log(666, datas);
////            var banner_r = rightData.data.list;
////            console.log(11111, banner_r)
//
//				}
//			})
//
//		} else {
//			alert("请先登录后在进行评论")
//
//		}
//	}
//
//
//
//
//
//
//
//	var a={
//		"membersId": 13
//	}
//
//
//	//写品论
//	console.log(111001010101,Request["nav"])
//	console.log(98989898989898,Request["id"])
//	$.ajax({
//		url:$members.commentadd + "/3/" + Request["id"] +"?pageNum=1&pageSize=10",
//		data:{content:$('#textarea').val()},
//		dataType: "json",
//		async: true,
//		type: "get",
//		success: function (detailData) {
//			console.log(89797124979,detailData)
//			var slide = detailData.data;
//
//			$('.commentCon-box').append('<div class="commentCon">' +
//			'<div class="commentCon-l">' +
//			'<img src="" alt="沙发">' +
//			'<img src="" alt="图像">' +
//			'<p class="call">'+  +'</p>' +
//			'<p class="date">'+  +'</p>' +
//			'</div>' +
//			'<div class="commentCon-r">' +
//			'<p>' +
//			'<span>'+ (i+1) +'楼</span>' +
//			'</p>' +
//			'<div class="commentbox">'+ +'</div>' +
//			'</div>' +
//			'</div>')
//		}
//	})
//
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