
//更多页面调用
var page = 1;
var searchData;

var getOriginalList = function (data) {
    searchData=data;
    console.log('qqq',data)
    window.sessionStorage.setItem("Original_data", JSON.stringify(data));
    location.href = 'more.html';
};
//更多
$("#Original").click(function () {
    homeList(4,1,10,getOriginalList);
});
$('.more').click(function () {
    if(searchData.length< 10){
        $('.more').html('没有更2222222222222多了');
    }else{
        page++;
        getOriginalList()
    }
});