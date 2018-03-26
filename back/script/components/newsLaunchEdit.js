$(function () {
    pageNum = 1;
    pageSize = 10;

    var siteName = '';

    var websitesList = [];

    var newsLaunchConfig = [];
    var getWebsitesList = function () {
        var data = {pageNum: pageNum, pageSize: pageSize, newsId: 342};
        if (siteName != '') {
            data.siteName = siteName;
        }
        AjaxFunc($query.newsQuery, 'get', data, function (re) {
            if (re.success) {
                initTable(re.data.list);
                console.log('re.data.list', re.data.list)
                initPage('pgNewsLaunch', $('#totalPgNewsLaunch'), re.data.total, getWebsitesList);
            } else {
                alert(re.msg);
            }
        });

    };


    var initializes = function () {
        $('#searchNewsLaunchBtn').click(function () {
            siteName = $('#siteNameNewsLaunchTxt').val();
            getWebsitesList();
        });
        $('#saveBtn').click(function () {
            if (newsLaunchConfig.length > 0) {
                // 禁用按钮防止重复提交
                $('#saveBtn').attr("disabled", "true");
                $('#newsLaunchConfig').val(JSON.stringify(newsLaunchConfig));
                $('#newsLaunchEditModal').trigger('save');
            } else {
                alert('请选择栏目投放');
            }
        });
        getWebsitesList();
    };

    var initTable = function (list) {
        websitesList = list;
        $('#newsLaunchEditT').empty();
        for (var i = 0; i < list.length; i++) {
            $('#newsLaunchEditT').append('<tr>' +
            '<td>' + list[i].siteName + '</td>' +
            '<td style="white-space:pre-wrap">' + getChannels(list[i].websiteId, list[i].channelLaunch) + '</td>' +
            '</tr>');
            for (var j = 0; j < list[i].channelLaunch.length; j++) {

                $('#wcCB_' + list[i].websiteId + '_' + list[i].channelLaunch[j].channelId).click(function () {
                    var ids = this.id.split('_');
                    updateSelectLaunch(ids[1], ids[2], this.checked);
                });
            }
        };
    }


    var getChannels = function (websiteId, list) {
        var re = '';
        for (var i = 0; i < list.length; i++) {
            var s;
            if (list[i].channelCheck == 1) {
                s = '<input type="checkbox" checked="checked" id="wcCB_' + websiteId + '_' + list[i].channelId + '">';
            } else {
                s = '<input type="checkbox" id="wcCB_' + websiteId + '_' + list[i].channelId + '">';
            }
            re += '<label style="margin-left: 5px;margin-top: 5px;">' + s + list[i].channelName + '</label>';
        }
        return re;
    };


    var updateSelectLaunch = function (websiteId, cid) {

        console.log($("").channelId);
        for (var i = 0; i < newsLaunchConfig.length; i++) {
            console.log('newsLaunchConfig==', newsLaunchConfig)
            if (websiteId == newsLaunchConfig[i].websiteId && cid == jQuery.inArray(cid, newsLaunchConfig[i].channelId) == -1) {
                newsLaunchConfig[i].channelId.push(cid);
                // console.log('newsLaunchConfig=='+JSON.stringify(newsLaunchConfig));
                return;
            }
        }
        newsLaunchConfig.push({websiteId: websiteId, channelId: [cid]});
    }

    initializes()
})