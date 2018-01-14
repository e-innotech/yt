/**
 * Created by Administrator on 2018/1/14.
 */
$(document).ready(function () {
    // 以下为中间部分新闻列表
    $.ajax({
        url: "../data/channel.json",
        dataType: "json",
        async: true,
        type: "get",
        success: function (newlistData) {
            //console.log(22,newlistData.data.list)
            var newlist = newlistData.data.list;
              console.log(111,newlist);
            for (var i = 0; i < newlist.length; i++) {
                //创建列表
                var createlistbox = $("<div class='list' id='list'></div>");

                //创建列表的h2标签
                var createH2 = $("<h2 class='subhead'></h2>");
                var createtaga = $("<a href='https://www.baidu.com/'></a>");
                createtaga.text(newlist[i].news_title);
                createH2.append(createtaga);
                //列表
                createlistbox.append(createH2);
                $(".listbox").append(createlistbox)
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
                createportrait.attr("src", newlist[i].portrait);
                ////来源
                creatsource.text(newlist[i].source);
                creatsource.append(createportrait);
                ////创建时间
                var time = $("<span class='time'></span>");
                //获取时间
                time.text(newlist[i].create_date);
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
                creatnewsdetail.text(newlist[i].content);
                creatnewsdetail.appendTo(createlistbox);


            }
        }
    });
})