$(document).ready(function(){
  $.get("http://123.59.156.27:8080/websites/find/id?id=1",function(data,status){
    console.log(data.data.channels[1].channelName);
    var items =data.data.channels;
    for (var i = 0 ; i < items.length; i++) {
        console.log(items[i])
        var ii = $("<li></li>");
        var aa = $("<a></a>");
        // aa.attr('href', 'new.html');//添加链接这一块你注意区分
        // aa.appendTo(ii);
        // aa.text(items[i].channelName);
        // ii.appendTo($("#nav"));
        switch (items[i].channelName){
          case "国内新闻":
          aa.attr('href', 'index.html');
          aa.text("国内新闻");
          break;
          case "财经新闻":
          aa.attr('href', 'nav.html');
          aa.text("财经新闻");
          break;
          case "汽车1":
          aa.attr('href', 'index.html');
          aa.text("汽车1");
          break;
        }
        aa.appendTo(ii);
        ii.appendTo($(".nav"));
    }
  });
});