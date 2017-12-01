$.sidebarMenu = function(menu) {
  var animationSpeed = 300;
  
  $(menu).on('click', 'li a', function(e) {
    var $this = $(this);
    var checkElement = $this.next();

    if (checkElement.is('.treeview-menu') && checkElement.is(':visible')) {
      checkElement.slideUp(animationSpeed, function() {
        checkElement.removeClass('menu-open');
      });
      checkElement.parent("li").removeClass("active");
    }

    //如果菜单不可见
    else if ((checkElement.is('.treeview-menu')) && (!checkElement.is(':visible'))) {
      //获取父级菜单
      var parent = $this.parents('ul').first();
      //关闭父内所有打开的菜单

      var ul = parent.find('ul:visible').slideUp(animationSpeed);
      //从父菜单中删除菜单打开类

      ul.removeClass('menu-open');
      //给li的父级
      var parent_li = $this.parent("li");

      //打开目标菜单并添加菜单打开类

      checkElement.slideDown(animationSpeed, function() {
        //向li的父级添加活动类
        checkElement.addClass('menu-open');
        parent.find('li.active').removeClass('active');
        parent_li.addClass('active');
      });
    }
    //如果这不是一个链接，请防止页面被重定向。
    if (checkElement.is('.treeview-menu')) {
      e.preventDefault();
    }
  });
}
