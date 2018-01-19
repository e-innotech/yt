/**
 * Created by Administrator on 2018/1/14.
 */
$(document).ready(function () {
    var page = 1;
    var commentData='';
    var channelId = getIdFromUrl();

    console.log('channelId='+channelId);
    var getNewsList = function(){
        newsList(channelId,page,6,function callback(newlist){
            commentData=newlist;
            for (var i = 0; i < newlist.length; i++) {
                //创建列表
                var createlistbox = $("<div class='list' id='list'></div>");

                //创建列表的h2标签
                var createH2 = $("<h2 class='subhead'></h2>");

                var createtaga = $('<a href="detail.html?id='+newlist[i].publishId+'"'+'></a>');
                createtaga.text(newlist[i].newsTitle);
                createH2.append(createtaga);
                //列表
                createlistbox.append(createH2);

                //中间的用户信息和时间
                var creatcenter = $("<div class='center'></div>");
                var createleft = $("<div class='center-l'></div>");
                var createright = $("<div class='center-r'></div>");
                //
                //创建文章来源
                var creatsource = $("<span></span>");

                //创建头像
                var createportrait = $("<img class='portrait' />");
                //头像
                createportrait.attr("src","../images/article.png");
                ////来源
                creatsource.text(newlist[i].source);
                creatsource.append(createportrait);
                ////创建时间
                var time = $("<span class='time'></span>");
                //获取时间
                time.text(newlist[i].createDate);
                createleft.append(creatsource);
                createleft.append(time);


                //中间右侧的点赞 收藏 和评论
                //点赞
                var creatp = $("<p></p>");
                var creatImg = $("<img />");
                creatImg.attr("src", "../images/zan.png");
                var creatspan = $("<span></span>");
                //给点赞的span赋值
                creatspan.text(0);
                creatp.append(creatImg);
                creatp.append(creatspan);
                //添加进center-right盒子中
                createright.append(creatp);

                //收藏
                var collectp = $("<p class='collect'></p>");
                var collectImg = $("<img />");
                collectImg.attr("src", "../images/cang.png");
                var collectspan = $("<span></span>");
                //给收藏的span赋值
                collectspan.text(0);
                collectp.append(collectImg);
                collectp.append(collectspan);
                createright.append(collectp);

                //评论
                var commentp = $("<p></p>");
                var commentImg = $("<img />");
                commentImg.attr("src", "../images/ping.png");
                var commentspan = $("<span></span>");
                //给评论的span赋值
                commentspan.text(0);
                commentp.append(commentImg);
                commentp.append(commentspan);
                createright.append(commentp);

                creatcenter.append(createleft);
                creatcenter.append(createright);
                createlistbox.append(creatcenter);

                creatcenter.appendTo(createlistbox)
                ////创建文章内容
                var creatnewsdetail = $("<p ></p>");
                creatnewsdetail.text(removeHTMLTag(newlist[i].subContent));
                creatnewsdetail.appendTo(createlistbox);

                $(".listbox").append(createlistbox);
            }
        })
    };

    $('.more').click(function(){
        if(commentData.length< 6){
            $('.more').html('没有更多了');
        }else{
            page++;
            getNewsList();
        }
    });

    adList(1,function callback(list){
         $(".advertiseing-left").append('<img src="'+ list[0].source +'"/>');
        $(".advertiseing-right").append('<img src="'+ list[1].source +'"/>')

    });
    getNewsList();

    //有一定滚动时显示这个top
    $(window).scroll(function(){
        console.log(11111111111)
        //获取距离浏览器顶部距离并赋值th
        var th = $(window).scrollTop();
        //用if判断，距离顶部大于300时给一个警告弹窗
        if(th>600){
            $("#toptop").show();
        }else{
            $("#toptop").hide();
        }
    });
})