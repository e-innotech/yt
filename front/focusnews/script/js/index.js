/**
 * Created by admin on 2018/1/11.
 */
//轮播图请求的数据
$(document).ready(function(){
    $.ajax({
        url:"../data/slideshow.json",
        dataType:"json",
        async:true,
        type:"get",
        success:function(slideshowData){
            var slide=slideshowData.data.slider;
            console.log(111,slide)
            for(var i=0;i<slide.length;i++){
                var creatLi=$("<li class='swiper-slide'></li>");
                var createa=$("<a href=''></a>");
                creatLi.append(createa)
                var createImg=$("<img class='picture'/>");
                createImg.attr("src",slide[i].staticUrl);
                createa.append(createImg);
                var creatediv=$("<div class='bgcolor'></div>");
                createa.append(creatediv);
                var creatp=$("<p></p>");
                creatp.text(slide[i].pic_des)
                creatediv.append(creatp)

                $("#slide").append(creatLi);

            }
        }
    })
});