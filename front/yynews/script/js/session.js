$('#list1 input').val(sessionStorage.getItem("nickname"));
$('#list2 input').val(sessionStorage.getItem("sexuality"));
$('#list3 input').val(sessionStorage.getItem("email"));
$('#list4 input').val(sessionStorage.getItem("addressbus"));

// //点击确认修改按钮时
$(".truerevise").on("click",function(){
   console.log($('#list1 input').val());
    var Nickname =$('#list1 input').val();
    var sexuality =$('#list2 input').val();
    var eMail =$('#list3 input').val();
    var addressBus =$('#list4 input').val();
    sessionStorage.setItem("nickname",Nickname);
    sessionStorage.setItem("email",eMail);
    sessionStorage.setItem("addressbus",addressBus);
    sessionStorage.setItem("sexuality",sexuality);
    $("#content-rightinside").load("peopleinfo.html");
  })