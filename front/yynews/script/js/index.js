$(document).ready(function () {

    //轮播图请求的数据
    $.ajax({
        url:  $yynews.index +"/3/1?pageNum=1&pageSize=20",
        dataType: "json",
        async: true,
        type: "get",
        success: function (slideshowData) {
            var slide = slideshowData.data.list;
            console.log(111, slide);
            for (var i = 0; i < slide.length; i++) {
                $('#slide').append('<li class="swiper-slide">' +
                '<a href="http://123.59.156.27:8080/web/detail/query/3/1/1">' +
                '<img class="picture" src="'+ slide[i].topImagePath +'"/>' +
                '<div class="bgcolor">' +
                '<p> '+ slide[i].newsTitle +'</p>' +
                '</div>' +
                '</a>' +
                '</li>');
                //
                //var creatLi=$("<li class='swiper-slide'></li>");
                //var createa=$("<a href=''></a>");
                //creatLi.append(createa)
                //var createImg=$("<img class='picture'/>");
                //createImg.attr("src",slide[i].staticUrl);
                //createa.append(createImg);
                //var creatediv=$("<div class='bgcolor'></div>");
                //createa.append(creatediv);
                //var creatp=$("<p></p>");
                //creatp.text(slide[i].pic_des);
                //creatediv.append(creatp);
                //
                //$("#slide").append(creatLi);

            }
        }
    })

// 以下为中间部分新闻列表
    $.ajax({
        url:  $yynews.index +"/3/3?pageNum=1&pageSize=20",
        dataType: "json",
        async: true,
        type: "get",
        success: function (newlistData) {
            console.log(666666666,newlistData)
            //console.log(22,newlistData.data.list)
            var newlist = newlistData.data.list;
            //  console.log(111,newlist);
            for (var i = 0; i < newlist.length; i++) {
                // 创建一个放新闻的容器
                var list = $("<div class='list' id='list'></div>");
                //创建图片
                var creatImgbig = $("<img />");
                var creatImga = $('<a href='+'"detail.html?nav=' +newlist[i].channelId +'&id='+newlist[i].id+'"'+'></a>');
                creatImgbig.attr("src", newlist[i].topImagePath);
                creatImga.append(creatImgbig);
                creatImga.appendTo(list);

                //创建右侧列表
                var createlistrbox = $("<div class='list-rbox'></div>");

                //创建右侧列表的h2标签
                var createH2 = $("<h2 class='subhead'></h2>");

                var createtaga = $('<a href='+'"detail.html?nav=' +newlist[i].channelId +'&id='+newlist[i].id+'"'+'></a>');
                createtaga.text(newlist[i].newsTitle);
                createH2.append(createtaga);
                //右侧列表
                createH2.appendTo(createlistrbox);
                //右侧中间的用户信息和时间
                var creatcenter = $("<div class='center'></div>");
                var createleft = $("<div class='center-l'></div>");
                var createright = $("<div class='center-r'></div>");

                //创建文章来源
                var creatsource = $("<span></span>");

                //创建头像
                var createportrait = $("<img class='portrait' />");
                //头像
                createportrait.attr("src", newlist[i].portrait);
                //来源
                creatsource.text(newlist[i].source);
                creatsource.append(createportrait);

                //创建时间
                var time = $("<span class='time'></span>");
                //获取时间 时间先写死
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
                list.append(creatcenter);

                creatcenter.appendTo(createlistrbox);
                //创建文章内容
                var creatnewsdetail = $("<p class='newsdetail'></p>");
                creatnewsdetail.text(removeHTMLTag(newlist[i].subContent));
                creatnewsdetail.appendTo(createlistrbox);

                list.append(createlistrbox);
                $(".listbox").append(list);
            }
        }
    });



    //轮播图右边请求的数据
    $.ajax({
        url: $yynews.index +"/3/2?pageNum=1&pageSize=20",
        dataType: "json",
        async: true,
        type: "get",
        success: function (rightData) {
            var banner_r = rightData.data.list;
            console.log(11111, banner_r)
            for (var i = 0; i <2; i++) {
                $(".content-left-r").append('<div class="top">' +
                '<a href="http://123.59.156.27:8080/web/detail/query/3/1/1">' +
                '<img src="' + banner_r[i].topImagePath + '"/>' +
                '<div class="bgcolortwo">' +
                '<p>' + banner_r[i].newsTitle + '</p>' +
                '</div>' +
                '</a>' +
                '</div>')

            }
        }
    })

//左侧广告
    $.ajax({
        url: $yynews.ad +'/3/0',
        dataType: "json",
        async: true,
        type: "get",
        success: function (adList) {
            for (var n = 0; n < 1; n++) {
                var AdList = adList.data;
                console.log(989898,adList)
                $(".advertising").append('<img src="'+ AdList[0].source +'">' +
                '<img src="'+ AdList[1].source +'">');
                $(".content-right-top").append('<img src="'+ AdList[2].source +'" class="right-banner"/>');

            }

        }
    })

});







