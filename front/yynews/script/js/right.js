$(document).ready(function(){

    //以下为独家标题部分
    homeList(4,1,7,function callback(list){
        $(".imgnewlist>img").attr("src",list[0].topImagePath);
        $(".imgnewlist .textone").text(list[0].newsTitle);
        $(".imgnewlist .texttwo").text(removeHTMLTag(removeHTMLTag(list[0].subContent)));
        for(var i=1;i<list.length;i++){
            $(".rightnewlist").append("<li><a href="+'"detail.html?id='+list[i].publishId+'"'+">"+ list[i].newsTitle+"</a></li>")
        }
    });



    //以下为热门文章部分
    homeList(5,1,2,function callback(list){
        for (var n = 0; n < list.length; n++) {
            //$(".content-right-bottom").html('');

            //创建一个容器
            var textpicBox = $("<div class='textpicBox'></div>");
            //把容器加进右下边的盒子中

            var creatImg = $("<img class='bigimg'/>");
            creatImg.attr("src", list[n].topImagePath);
            textpicBox.append(creatImg);

            var creatImgtop = $("<img class='smallimg'/>");
            creatImgtop.attr("src", "../images/top1.png");
            textpicBox.append(creatImgtop);

            var bg = $("<div class='bg'></div>");
            var creattagp = $("<a href="+'"detail.html?id='+list[n].publishId+'"'+" style='color: #fff;'><p></p></a>")
            creattagp.text(list[n].newsTitle);
            bg.append(creattagp)
            textpicBox.append(bg);
            $(".content-right-bottom").append(textpicBox);


        }
    });

//广告
    adList(0,function callback(list){
        console.log(2222,list);
        $(".advertising").append('<img src="'+ list[0].source +'">' +
        '<img class="imgbottom" src="'+ list[1].source +'">');
        //console.log(1111,list)
        $(".content-right-top").append('<img src="'+ list[0].source +'" class="right-banner"/>');

    })


    //更多页面调用
    var page = 1;
    var getOriginalList = function (data) {
        window.sessionStorage.setItem("Original_data", JSON.stringify(data));
        location.href = 'moer.html';
    };
    //更多
    $("#Original").click(function () {
        //var newsTitle = $('input[name="search"]').val();
        //if(newsTitle==''){
        //    alert("请输入内容");
        //    return;
        //}
        //参数 网站id 新闻标题 当前页 显示页
        homeList(4,1,7,getOriginalList);
    });
    $('.more').click(function () {
        page++;

    });

});


