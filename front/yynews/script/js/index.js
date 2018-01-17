$(document).ready(function () {
    var page = 1;
    $(".nav").on("click","li",function(){
        $(this).addClass("active").siblings().removeClass("active")
    })

    $('.more').click(function(){
        page++;
        getHomeBoxList();
    });
    getHomeBoxList();


    //轮播图请求的数据

    homeList(1,1,3,function callback(list){
        for (var i = 0; i < list.length; i++) {
            $('#slide').append('<li class="swiper-slide">' +
            '<a href="detail.html?id='+list[i].publishId+'">' +
            '<img class="picture" src="' + list[i].topImagePath + '"/>' +
            '<div class="bgcolor">' +
            '<p> ' + list[i].newsTitle + '</p>' +
            '</div>' +
            '</a>' +
            '</li>');
        }
    });


//
//// 以下为中间部分新闻列表
    function getHomeBoxList(){

        //传的网站ID 页数 一页显示几条数据
        homeList(3,page,7,function callback(newlist){
            for (var i = 0; i < newlist.length; i++) {
                    // 创建一个放新闻的容器
                    var list = $("<div class='list' id='list'></div>");
                    //创建图片
                    var creatImgbig = $("<img />");
                    var creatImga = $('<a href="detail.html?id='+newlist[i].publishId+'"'+'></a>');
                    creatImgbig.attr("src", newlist[i].topImagePath);
                    creatImga.append(creatImgbig);
                    creatImga.appendTo(list);

                    //创建右侧列表
                    var createlistrbox = $("<div class='list-rbox'></div>");

                    //创建右侧列表的h2标签
                    var createH2 = $("<h2 class='subhead'></h2>");

                    var createtaga = $('<a href="detail.html?id='+newlist[i].publishId+'"'+'></a>');
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
                    createportrait.attr("src", '../images/article.png');
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
                    var collectp = $("<p class='collect' ></p>");
                    var collectImg = $("<img onclick='collectAdd("+newlist[i].publishId+")'/>");
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
        })
    }
//
//
//
//    //轮播图右边请求的数据
    homeList(2,1,2,function callback(list){
        for (var i = 0; i <list.length; i++) {
                $(".content-left-r").append('<div class="top">' +
                '<a href="detail.html?id='+list[i].publishId+'">' +
                '<img src="' + list[i].topImagePath + '"/>' +
                '<div class="bgcolortwo">' +
                '<p>' + list[i].newsTitle + '</p>' +
                '</div>' +
                '</a>' +
                '</div>')

            }
    });



});







