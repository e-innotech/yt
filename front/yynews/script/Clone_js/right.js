$(document).ready(function(){

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


