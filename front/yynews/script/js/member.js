$(function(){
    var mempage=1;
    //选项卡
    $(".bottom ul").on("click", "li",function(){
        //在同一个盒子中写个人资料和修改个人资料
        switch($(this).index()){
            case 0:
                switchInfo(false);
                break;
            case 1:
                coll();
                break;
            case 2:
                comm();
                break;
        };
        $(this).addClass("actives").siblings().removeClass("actives");
        $(".content-right .content-rightinside").eq($(this).index()).show().siblings().hide();

    });
    //点击确认修改时 让修改个人资料显示
    $('.material').click(function(){
        switchInfo(true);
    });

    $('.upload_pic').change(function(){
        var files = $('#upload_file').prop('files');
        var data = new FormData();
        data.append('upload_file',files[0]);
        data.append('fileDirectory','userIcon');
        uploadIcon(data,function callback(data){
            $('input[name="icon"]').val(data);
            $('.info').css('display','block');
            var image = new Image();
            // 设置src属性
            image.src = data;
            var max=200;
            image.onload = function(){
                var canvas = document.getElementById("cvs");
                var ctx = canvas.getContext("2d");
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(image, 0, 0, 88, 88);
            };
        });
    })
    $('#editBtn').click(function(){
        var data = serializeObject($('#userForm').serializeArray());
        console.log('data:'+data);
        membersEdit(data,function callback(data){
            sessionStorage.setItem('user',JSON.stringify(data));
            switchInfo(false);
        });

    });
    $('#pwdBtn').click(function(){
        var data = serializeObject($('#pwdForm').serializeArray());
        membersPwd(data)
    });

    //判断个人资料和修改个人资料那个显示，那个隐藏的
    var switchInfo = function(bol){
        $('#userInfo').css('display',bol?'none':'block');
        $('#editInfo').css('display',bol?'block':'none');
        renderUserInfo();
        renderEditInfo();
    }

    //render info
    var renderUserInfo = function() {
        var user = JSON.parse(sessionStorage.getItem('user'));
        console.log(91,user);
        var imgUrl = '../images/ren.png';
        if(user.infos){
            if(user.infos.icon) {
                imgUrl = user.infos.icon;
            }
        }
        $('#iconTop').attr('src',imgUrl);
        if(user.infos){
            $('#nickName').html(user.infos.nickName);
            $('.nickname').html(user.infos.nickName);
            $('.emails').html(user.infos.email);
            $('.address').html(user.infos.address);
            $('.sex').html(sex[user.infos.sex]);
        }

    }
    var renderEditInfo = function() {
        var user = JSON.parse(sessionStorage.getItem('user'));
        if(user.infos){
            if(user.infos.icon){
                $('input[name="icon"]').val(user.infos.icon);
            }

        }

        if(user.infos){
            $('input[name="nickName"]').val(user.infos.nickName);
            $('input[name="email"]').val(user.infos.email);
            $('input[name="address"]').val(user.infos.address);
            $('select[name="sex"]').val(user.infos.sex);
        }

    }
    renderUserInfo();
    renderEditInfo();

    //会员收藏列表

//渲染收藏的列表
    function coll(){
        collectList(mempage,9,function callback(data){
            $("#collect_total").html('('+data.total+')');
            $("#collectlistbox").html('');
            var list = data.list;
            console.log("qqqq",list)
            for(var i=0;i<list.length;i++){
                $("#collectlistbox").append('<div class="collectlist">' +
                '<div class="left">'+
                '<input type="checkbox"/>'+
                '</div>'+
                '<div class="center"><a href="detail.html?id='+list[i].publishId+'">'+list[i].news.newsTitle+'</a></div>'+
                '<div class="right"><img  src="../images/del.png" onclick="deleteCollect('+list[i].id+')"/></div>'+
                '</div>');
            }
        });
    }

    //收藏点击下一页时
    $("#collnextpage").click(function(){
        mempage++;
        coll();
        $(this).css("background","#999999");
        $("#collprevpage").css("background","#c9c9c9")

    });
    //收藏点击上一页时
    $("#collprevpage").click(function(){
        mempage--;
        if(mempage==0){
            mempage=1;
            return
        }
        coll();
        $(this).css("background","#999999");
        $("#collnextpage").css("background","#c9c9c9")

    })

   //评论列表
   function comm(){
       console.log(111111111111);
       memcommentList(mempage,5,function callback(data){
            $("#comment_total").html('('+data.total+')');
            $("#commentlistbox").html('');
            var list = data.list;
            //console.log(7778,list);
            //console.log(list[i])
            for(var i=0;i<list.length;i++){
                $("#commentlistbox").append('<div class="commentlist">' +
                '<div class="left">'+
                '<input type="checkbox"/>'+
                '</div>'+
                '<div class="center">'+
                    '<p>'+list[i].content+'</p>'+
                    '<span class="datas">'+list[i].createDate+"&nbsp;&nbsp;&nbsp;&nbsp;来自文章:"+'</span>'+
                    '<span class="articles">'+"&nbsp;"+ '<a href="detail.html?id='+list[i].publishId+'">'+list[i].newsTitle+'</a></span>'+
                '</div>'+
                '<div class="right"><img src="../images/del.png" onclick="deleteComment('+list[i].id+')"/></div>'+
                '</div>')
            }
        });
   }


    //评论点击下一页时
    $("#commnextpage").click(function(){
        mempage++;
        comm();
        $(this).css("background","#999999");
        $("#commprevpage").css("background","#c9c9c9")

    });

    //评论点击上一页时
    $("#commprevpage").click(function(){
        mempage--;
        if(mempage==0){
            mempage=1;
            return
        }
        comm();
        $(this).css("background","#999999");
        $("#commnextpage").css("background","#c9c9c9");

    })

})












