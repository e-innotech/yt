$(document).ready(function(){
    //以下为独家标题部分
    $.ajax({
        url: $yynews.index +"/3/5?pageNum=1&pageSize=20",
        dataType:"json",
        async:true,
        type:"get",
        success:function(rightnewData){
            //console.log(44333333,rightnewData);
            var rightdata=rightnewData.data.list;
            $(".imgnewlist>img").attr("src",rightdata[1].topImagePath);

            $(".imgnewlist .textone").text(rightdata[1].newsTitle);
            $(".imgnewlist .texttwo").text(rightdata[1].subContent);
            var rightnewdata=rightnewData.data.rightnewlists;
            //console.log(33,rightnewdata);
            for(var i=0;i<rightdata.length;i++){
                //$(".header .nav").append('<li><a>'+listdata[i].channelName+'</a></li>')
                $(".rightnewlist").append('<li><a>'+rightdata[i].newsTitle+'</a></li>')
            }

        }
    })



    //以下为热门文章部分
    $.ajax({
        url: $yynews.index +"/3/4?pageNum=1&pageSize=20",
        dataType:"json",
        async:true,
        type:"get",
        success:function(rightnewData) {
            console.log(45454545, rightnewData);
            var Article=rightnewData.data.list;
            //console.log(111111,Article)
            //var topArticle = rightnewData.data.topArticle
            for (var n = 0; n < Article.length; n++) {
                //创建一个容器
                var textpicBox = $("<div class='textpicBox'></div>");
                //把容器加进右下边的盒子中
                $(".content-right-bottom").append(textpicBox);
                var creatImg = $("<img class='bigimg'/>");
                creatImg.attr("src", Article[n].topImagePath);
                textpicBox.append(creatImg);

                var creatImgtop = $("<img class='smallimg'/>");
                creatImgtop.attr("src", "../images/top1.png");
                textpicBox.append(creatImgtop);

                var bg = $("<div class='bg'></div>");
                var creattagp = $("<p></p>")
                creattagp.text(Article[n].newsTitle);
                bg.append(creattagp)
                textpicBox.append(bg);
                $(".content-right-bottom").append(textpicBox);

            }
        }
    })
});


