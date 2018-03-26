$(function () {
    var ctrl_update = '';
    var newsList = [];
    pageNums = 1;
    pageSizes = 10;
    IsTop=0;
    var myArray=new Array();

    var getHomeWeightList = function () {

        var data = {pageSize: pageSizes, pageNum: pageNums, websiteId:1};
        AjaxFunc($query.home_query, 'get', data, function (re) {
            if (re.success) {
                initTables(re.data.list);
                initPages('pgNewsLaunch', $('#totalPgNewsLaunch'), re.data.total, getHomeWeightList);
            } else {
                alert(re.msg);
            }
        });

    };
    var initTables = function (list) {
        newsList=list;
        $('#indexEditT').empty();

        for (var i = 0; i < list.length; i++) {
            $('#indexEditT').append('<tr>' +
            '<td>' + '<input type="checkbox" id="hw_' + list[i].id +'">' + '</td>' +
            '<td>' + list[i].news.newsTitle + '</td>' +
            '<td style="white-space:pre-wrap;text-align: center;">' + '<input type="radio" name="radio" id="'+ list[i].id+'">' + '</td>' +
            '</tr>');
            $('#hw_' + list[i].id ).change(function () {
                $.each($('input:checkbox:checked'),function(){
                    //console.log('input:checkbox:checked',this)
                    var ids = this.id.split('_');
                    myArray.push(ids[1]);
                });
            });
            $('input[type=radio][name=radio]').change(function(){
                $('input[type=radio]:checked').each(function(){
                    myArray.push(this.id);
                    IsTop=1;
                });
            })

        }
    };


    var initialize = function () {
        for (var i = 0; i < nodeData.buttons.length; i++) {
            if (nodeData.buttons[i].uri.indexOf('/home/update') != -1) {
                ctrl_update = nodeData.buttons[i].uri;
            }
        }
        if (ctrl_update != '') {
            $('.homeWeightBtn').show();
            $('#homeWeight_1').click(function () {
                $.get($components.indexEdit, function (re) {
                    $('#popPanel').html(re);
                    $('#indexEditModal').modal('show');
                    getHomeWeightList();
                    $('#saveBtn').click(function () {//投放时保存按钮
                        var data = {homeWeight:1,ids:$.unique(myArray),isTop:IsTop};
                        AjaxFunc($apiUrl+ctrl_update,'post',data,function (re) {
                            alert(re.msg);
                            if(re.success){
                                $('#indexEditModal').modal('hide');
                            }
                        })
                    });
                });
            });
            $('#homeWeight_2').click(function () {
                $.get($components.indexEdit, function (re) {
                    $('#popPanel').html(re);
                    $('#indexEditModal').modal('show');
                    getHomeWeightList();
                    $('#saveBtn').click(function () {//投放时保存按钮
                        var data = {homeWeight:2,ids:$.unique(myArray),isTop:IsTop};
                        AjaxFunc($apiUrl+ctrl_update,'post',data,function (re) {
                            alert(re.msg);
                            if(re.success){
                                $('#indexEditModal').modal('hide');
                            }
                        })
                    });
                });
            });
            $('#homeWeight_3').click(function () {
                $.get($components.indexEdit, function (re) {
                    $('#popPanel').html(re);
                    $('#indexEditModal').modal('show');
                    $('.isTop').css('width','44');
                    getHomeWeightList();
                    $('#saveBtn').click(function () {//投放时保存按钮
                        var data = {homeWeight:3,ids:$.unique(myArray),isTop:IsTop};
                        AjaxFunc($apiUrl+ctrl_update,'post',data,function (re) {
                            alert(re.msg);
                            if(re.success){
                                $('#indexEditModal').modal('hide');
                            }
                        })
                    });
                });
            });

            $('#homeWeight_5').click(function () {
                $.get($components.indexEdit, function (re) {
                    $('#popPanel').html(re);
                    $('#indexEditModal').modal('show');
                    getHomeWeightList();
                    $('#saveBtn').click(function () {//投放时保存按钮
                        var data = {homeWeight:5,ids:$.unique(myArray),isTop:IsTop};
                        AjaxFunc($apiUrl+ctrl_update,'post',data,function (re) {
                            alert(re.msg);
                            if(re.success){
                                $('#indexEditModal').modal('hide');
                            }
                        })
                    });
                });
            });
        }
    };








    initialize()
});