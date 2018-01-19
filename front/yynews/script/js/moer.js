
//更多页面调用
var page = 1;
var getOriginalList = function (data) {
    window.sessionStorage.setItem("Original_data", JSON.stringify(data));
    location.href = 'more.html';
};
//搜索
$("#Original").click(function () {
    homeList(4,1,10,getOriginalList);
});
$('.more').click(function () {
    page++;

});