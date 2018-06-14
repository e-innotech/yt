var config ={
    init:function(){
        //$apiUrl = ' http://192.168.20.195:8080';//本地
        //$apiUrl = ' http://123.59.156.27:8080';//测试
        //$uploadUrl = 'http://123.59.156.27:8080/yy/upload';//测试
        //$uploadUrl = 'http://192.168.20.195:8080/yy/upload';//本地
        $apiUrl = 'http://www.yourcaijing.cn:8888';//测试
        $uploadUrl = 'http://www.yourcaijing.cn:8888/yy/upload';//测试
        $admin = {
            'login':$apiUrl+'/login',
            'logout':$apiUrl+'/logout'
        };

        $user = {
            'pwd':$apiUrl+'/user/pwd'
        };


        $query = {
            'ad':$apiUrl+'/ad/query',
            'adPositions':$apiUrl+'/adPositions/query',
            'channel':$apiUrl+'/channel/query',
            'members':$apiUrl+'/members/query',
            'membersComment':$apiUrl+'/member/comment/query',
            'news':$apiUrl+'/news/query',
            'newsLaunch':$apiUrl+'/news/launch/query',
            'newsPublish':$apiUrl+'/news/publish/query',
            'resource':$apiUrl+'/resource/query',
            'role':$apiUrl+'/role/query',
            'user':$apiUrl+'/user/query',
            'userGroup':$apiUrl+'/userGroup/query',
            'log':$apiUrl+'/log/query',
            'websites':$apiUrl+'/websites/query',
            'newsQuery':$apiUrl+'/news/websites/query',
            'websitesTemplate':$apiUrl+'/websites/template/query',
            'home':$apiUrl+'/home',
            'home_query':$apiUrl+'/home/query',
            'userId':$apiUrl+'/user/findEditorGroup',
            'config':$apiUrl+'/config/query',
            'newsQueryWebChannel':$apiUrl+'/news/websites/queryAll',
            'newsQueryWebChannelRelationShip':$apiUrl+'/news/websites/queryRealation'
        };




        $components = {
            'indexEdit':'components/indexEdit.html'+'?v='+version,


            'confirm':'components/confirm.html'+'?v='+version,



            'pwdReset':'components/pwdReset.html'+'?v='+version,

            'userGroup':'components/userGroup.html'+'?v='+version,
            'userQuery':'components/userQuery.html'+'?v='+version,
            'adduserGroup':'components/userGroup.html'+'?v='+version,
            'addSelectWebsite':'components/selectWebsite.html'+'?v='+version,
            'addRole':'components/addroles.html'+'?v='+version,
            'userGroupQuery':'components/userGroupQuery.html'+'?v='+version,
            'userGroup_rolesId':'components/userGroup_rolesId.html'+'?v='+version,

            'resourceEdit':'components/resourceEdit.html'+'?v='+version,
            'resourceList':'components/resourceList.html'+'?v='+version,

            'roleEdit':'components/roleEdit.html'+'?v='+version,

            'websitesEdit':'components/websitesEdit.html'+'?v='+version,
            'channelEdit':'components/channelEdit.html'+'?v='+version,

            'newsEdit':'components/newsEdit.html'+'?v='+version,
            'newsEdit_topImg':'components/newsEdit_topImg.html'+'?v='+version,
            'newsLaunchEdit':'components/newsLaunchEdit.html'+'?v='+version,


            'newsLaunchAduit':'components/newsLaunchAduit.html'+'?v='+version,
            'newsList':'components/newsList.html'+'?v='+version,
            'newsContentPreview':'components/newsContentPreview.html'+'?v='+version,

            'adEdit':'components/adEdit.html'+'?v='+version,
            'adPositionsEdit':'components/adPositionsEdit.html'+'?v='+version,
            'adPositionsList':'components/adPositionsList.html'+'?v='+version,

            'webTemplateList':'components/webTemplateList.html'+'?v='+version,


            'homeCtrl':'components/homeCtrl.html'+'?v='+version,

            'configQuery':'components/configQuery.html'+'?v='+version



        };

    }
}






