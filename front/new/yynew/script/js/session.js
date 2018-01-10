$('#list1 input').val(sessionStorage.getItem(1));
$('#list2 input').val(sessionStorage.getItem(4));
$('#list3 input').val(sessionStorage.getItem(2));
$('#list4 input').val(sessionStorage.getItem(3));

// //点击确认修改按钮时
$(".truerevise").on("click",function(){
   console.log($('#list1 input').val());
    var Nickname =$('#list1 input').val();
    var sexuality =$('#list2 input').val();
    var eMail =$('#list3 input').val();
    var addressBus =$('#list4 input').val();
    sessionStorage.setItem(1,nickName);
    sessionStorage.setItem(2,eMail);
    sessionStorage.setItem(3,addressBus);
    sessionStorage.setItem(4,sexuality);

    // var sexuality = $('#list2 input').val()
    // var eMail = $('#list3 input').val()
    // var addressBus = $('#list4 input').val()

    
    $("#content-rightinside").load("peopleinfo.html")

    // sexuality = $('#list2 input').val();
    // eMail = $('#list3 input').val();
    // addressBus = $('#list4 input').val();
  })

