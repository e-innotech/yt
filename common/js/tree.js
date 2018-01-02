$(function() {
    var treeData = JSON.parse(sessionStorage.getItem('permissons'));
    console.log(treeData);

    $('#tree').treeview({
        data: treeData.menu,
        showTags: true,
        text: "resourceName",
        levels:2,//折叠
        color: "#000000",
        backColor: "#FFFFFF",
        selectable: true,
        enableLinks: true,//启用链接
        highlightSelected: true,
        collapseAll:{ silent: true },
        state: {
            checked: true,
            disabled: true,
            expanded: true,
            selected: true
        }
    })


    $(".node-tree[data-nodeid='1'] a").attr('href','../../back/html/uname.html');
    $(".node-tree[data-nodeid='2'] a").attr('href','../../back/html/user_group.html');
    $(".node-tree[data-nodeid='4'] a").attr('href','../../back/html/uname.html');
    $(".node-tree[data-nodeid='5'] a").attr('href','../../back/html/roles.html');
    $(".node-tree[data-nodeid='7'] a").attr('href','../../back/html/websites.html');
    $(".node-tree[data-nodeid='8'] a").attr('href','../../back/html/column.html');
    $(".node-tree[data-nodeid='10'] a").attr('href','../../back/html/adPositions.html');
    $(".node-tree[data-nodeid='11'] a").attr('href','../../back/html/adPositions.html');
    $(".node-tree[data-nodeid='13'] a").attr('href','../../back/html/news.html');
    $(".node-tree[data-nodeid='14'] a").attr('href','../../back/html/news.html');
    $(".node-tree[data-nodeid='16'] a").attr('href','../../back/html/系统配置列表.html');
    $(".node-tree[data-nodeid='17'] a").attr('href','../../back/html/用户日志查询列表.html');
    $(".node-tree[data-nodeid='18'] a").attr('href','../../back/html/uname.html');
    $(".node-tree[data-nodeid='19'] a").attr('href','../../back/html/uname.html');

})
