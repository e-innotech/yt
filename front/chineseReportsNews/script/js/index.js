/**
 * Created by admin on 2018/1/11.
 */
//轮播图请求的数据
$(document).ready(function(){
    //$.ajax({
    //    url:"../data/slideshow.json",
    //    dataType:"json",
    //    async:true,
    //    type:"get",
    //    success:function(slideshowData){
    //        var slide=slideshowData.data.slider;
    //        console.log(111,slide)
    //        for(var i=0;i<slide.length;i++){
    //            var creatLi=$("<li class='swiper-slide'></li>");
    //            var createa=$("<a href=''></a>");
    //            creatLi.append(createa)
    //            var createImg=$("<img class='picture'/>");
    //            createImg.attr("src",slide[i].staticUrl);
    //            createa.append(createImg);
    //            var creatediv=$("<div class='bgcolor'></div>");
    //            createa.append(creatediv);
    //            var creatp=$("<p></p>");
    //            creatp.text(slide[i].pic_des)
    //            creatediv.append(creatp)
    //
    //            $("#slide").append(creatLi);
    //
    //        }
    //    }
    //})


//回到顶部
    'use strict';
    $.fn.Totop=function(obj){
        var othis=this;
        var win=$(window);
        var hb=$('html,body');
        var Option=$.extend(
            {        autohide:true,//设置元素是否一开始就隐藏，
                speed:500,//到顶部速度
                right:10,
                bottom:500,
                offsetTop:400,//当scrollTop等于这个值时，如果autohide:true元素显示
                position:true//是否采用fixed定位
            },obj);

        if(Option.autohide){
            othis.css('display','none')
        }else{
            othis.css('display','block')
        }
        if(Option.position){
            othis.css({
                'position':'fixed',
                'right':Option.right,
                'bottom':Option.bottom
            })
        }
        othis.click(function(){
            hb.animate({
                scrollTop:0
            },Option.speed)
        });
        win.scroll(function(){
            var scrollT=win.scrollTop();
            if(Option.autohide){
                if(scrollT>Option.offsetTop)
                {
                    othis.fadeIn(Option.speed)
                }else{
                    othis.fadeOut(Option.speed)
                }
            }
        })
    }
    $('.back_to_top').Totop({
        autohide:true,//设置元素是否一开始就隐藏，
        speed:500,//到顶部速度
        right:280,
        bottom:310,
        offsetTop:400,//当scrollTop等于这个值时，如果autohide:true元素显示
        position:true//是否采用fixed定位
    })



    /*分页*/
    $('.more').click(function(){

    })
});