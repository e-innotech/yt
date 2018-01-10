//以下为右侧中间部分的新闻列表
$(document).ready(function(){
    $.ajax({
        url:"../data/rightnew.json",
        dataType:"json",
        async:true,
        type:"get",
        success:function(rightnewData){
            //console.log(44,rightnewData);
            $(".imgnewlist>img").attr("src",rightnewData.data.pic);
            $(".imgnewlist .textone").text(rightnewData.data.secondarylist);
            $(".imgnewlist .texttwo").text(rightnewData.data.second_con);
            var rightnewdata=rightnewData.data.rightnewlists;
            console.log(33,rightnewdata);
            for(var i=0;i<rightnewdata.length;i++){
                //$(".header .nav").append('<li><a>'+listdata[i].channelName+'</a></li>')
                $(".rightnewlist").append('<li><a>'+rightnewdata[i].rightnewlist+'</a></li>')
            }

            console.log(44,rightnewData.data.topArticle);
            var topArticle=rightnewData.data.topArticle
            console.log(77,topArticle);
            console.log(88,topArticle[0].urls)
          for(var n=0;n<topArticle.length;n++){
                //创建一个容器
                var textpicBox=$("<div class='textpicBox'></div>");
                //把容器加进右下边的盒子中
               $(".content-right-bottom").append(textpicBox);
               var creatImg=$("<img class='bigimg'/>");
               creatImg.attr("src",topArticle[n].urls);
              textpicBox.append(creatImg);

              var creatImgtop=$("<img class='smallimg'/>");
              creatImgtop.attr("src","../images/top1.png");
              textpicBox.append(creatImgtop);

              var bg=$("<div class='bg'></div>");
              var creattagp=$("<p></p>")
              creattagp.text(topArticle[n].con_dec);
              bg.append(creattagp)
              textpicBox.append(bg);
              $(".content-right-bottom").append(textpicBox);

            }
        }
    })
});