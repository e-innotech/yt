$(document).ready(function(){

    //以下为独家标题部分
    //homeList(4,1,7,function callback(list){
    //    $(".imgnewlist>img").attr("src",list[0].topImagePath);
    //    $(".imgnewlist .textone").text(list[0].newsTitle);
    //    $(".imgnewlist .texttwo").text(removeHTMLTag(removeHTMLTag(list[0].subContent)));
    //    for(var i=1;i<list.length;i++){
    //        $(".rightnewlist").append("<li><a href="+'"detail.html?id='+list[i].publishId+'"'+">"+ list[i].newsTitle+"</a></li>")
    //    }
    //});
    homeList(4, 1, 7, function callback(list) {
        var creattaga = $("<a href=" + '"detail.html?id=' + list[0].publishId + '"' + "></a>");
        creattaga.append('<div class="imgnewlist">' +
        '<img src=' + list[0].topImagePath + '>' +
        '<p class="textone">' + list[0].newsTitle + '</p>' +
        '<p class="texttwo">' + removeHTMLTag(removeHTMLTag(list[0].subContent)) + '</p>' +
        '</div>');
        $(".content-right-center").append(creattaga);
        for (var i = 1; i < list.length; i++) {
            $(".rightnewlist").append("<li><a href=" + '"detail.html?id=' + list[i].publishId + '"' + ">" + list[i].newsTitle + "</a></li>")
        }
        $(".content-right-center").append($(".rightnewlist"))
    });



    //以下为热门文章部分
    homeList(5,1,2,function callback(list){
        for (var n = 0; n < list.length; n++) {
            //$(".content-right-bottom").html('');

            //创建一个容器



               var textpicBox = $("<div class='textpicBox'></div>");
               //把容器加进右下边的盒子中

               var creata = $("<a href="+'"detail.html?id='+list[n].publishId+'"'+" style='color: #fff;'></a>");


               var creatImg = $("<img class='bigimg'/>");
               creatImg.attr("src", list[n].topImagePath);
               textpicBox.append(creata);
               creata.append(creatImg)

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
    var moreData;
    var getOriginalList = function (data) {
        moreData=data;
        window.sessionStorage.setItem("Original_data", JSON.stringify(data));
        location.href = 'more.html';
    };
    //更多
    $("#moreData").click(function () {
        homeList(4,1,10,getOriginalList);
    });


});


