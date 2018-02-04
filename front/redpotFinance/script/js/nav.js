
$(document).ready(function () {
    //有一定滚动时显示这个top
    $(window).scroll(function () {
        //获取距离浏览器顶部距离并赋值th
        var th = $(window).scrollTop();
        if (th > 600) {
            $("#backtop").show();
        } else {
            $("#backtop").hide();
        }
    });


        var page=1;
        function getNavList() {
            //传的权限 页数 一页显示几条数据
            newsList(3, page,10, function callback(newsList) {
                console.log(23,newsList.length);
                if(newsList.length>0){
                    for (var i = 0; i < newsList.length; i++) {
                    //<div class="news_list">
                    //    <a href="detail.html">
                    //    <h2>
                    //    独家 | 输给了空间阿里斯顿拉科技师范大咖啡里斯顿拉科技师范大里斯顿拉科技师范大
                    //    </h2>
                    //    <p>2017-03-17</p>
                    //    <div>
                    //    咖啡色卡萨丁卡仕达克里斯多夫客户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克色卡萨丁卡仕达克里斯多夫客户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克色卡萨丁卡仕达克里斯多夫客户卡好久水电费卡拉胶发多少奥卡福咖啡壶丁卡仕达克里斯多夫客户卡好久水电费卡拉丁卡仕达克里斯多夫客户卡好久水电费卡拉
                    //    </div>
                    //    </a>
                    //    </div>
                        $('.news_listbox').append('<div class="news_list">' +
                            '<a href="">' +
                            '<h2>' +newsList[i].newsTitle+' </h2>' +
                            '<p>' +newsList[i].createDate+'</p>' +
                            '<div>' +newsList[i].subContent+'</div>' +
                            '</a>' +
                            '</div>')
                    }
                }else{
                    $('.nextpages').attr("disabled",true);
                }

            })
        };

//点击上一页时
        $('.prevpages').click(function () {
            if(page==1){
                page=1;
            }else{
                $(".news_listbox").html('');
                page--;
                getNavList();
            }
            $('.nextpages').attr("disabled",false);

        });
//点击下一页时
        $('.nextpages').click(function () {
            $(".news_listbox").html('');
            page++;
            getNavList();
        });


    })


