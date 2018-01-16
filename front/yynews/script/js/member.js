$(function(){
    //选项卡
    $(".bottom ul").on("click", "li",function(){
         console.log($(this).index());
        if($(this).index() == 0){
            switchInfo(false);
        }
        $(this).addClass("actives").siblings().removeClass("actives");
        $(".content-right .content-rightinside").eq($(this).index()).show().siblings().hide();

    });
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

    var switchInfo = function(bol){
        $('#userInfo').css('display',bol?'none':'block');
        $('#editInfo').css('display',bol?'block':'none');
        renderUserInfo();
        renderEditInfo();
    }


    //render info

    var renderUserInfo = function() {
        var user = JSON.parse(sessionStorage.getItem('user'));

        $('#iconTop').attr('src',user.infos.icon);
        $('#nickName').html(user.infos.nickName);
        $('.nickname').html(user.infos.nickName);
        $('.emails').html(user.infos.email);
        $('.address').html(user.infos.address);
        $('.sex').html(sex[user.infos.sex]);
    }
    var renderEditInfo = function() {
        var user = JSON.parse(sessionStorage.getItem('user'));


        $('input[name="icon"]').val(user.infos.icon);
        $('input[name="nickName"]').val(user.infos.nickName);
        $('input[name="email"]').val(user.infos.email);
        $('input[name="address"]').val(user.infos.address);
        $('select[name="sex"]').val(user.infos.sex);
    }
    renderUserInfo();
    renderEditInfo();



})










