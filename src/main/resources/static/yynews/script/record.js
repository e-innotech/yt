 console.log(1212)
 var nickName =1,
     sexuality,
     eMail,
     addressBus;
//点击确认修改按钮时
$(".truerevise").on("click",function(){
   console.log($('#list1 input').val());
    nickName = $('#list1 input').val();
    sexuality = $('#list2 input').val();
    eMail = $('#list3 input').val();
    addressBus = $('#list4 input').val();

    $("#content-rightinside").load("peopleinfo.html")
	$(".details-left .nickname").html(nickName);
	$(".details-left .email").html(eMail);
	$(".details-right .address").html(addressBus);
	$(".details-right .sex").html(sexuality);
  })

