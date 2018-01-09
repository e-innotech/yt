var config ={
    init:function(){
        // $apiUrl = ' http://192.168.20.195:8080';//本地
       $apiUrl = ' http://123.59.156.27:8080';//测试


        $uploadUrl = 'http://192.168.20.195:8888/yy/upload';

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
            'config':$apiUrl+'/config/query'
        };




        $components = {
            'confirm':'components/confirm.html',

            'pwdReset':'components/pwdReset.html',

            'userGroup':'components/userGroup.html',
            'userQuery':'components/userQuery.html',
            'adduserGroup':'components/userGroup.html',
            'addRole':'components/addroles.html',
            'userGroupQuery':'components/userGroupQuery.html',
            'userGroup_rolesId':'components/userGroup_rolesId.html',

            'resourceEdit':'components/resourceEdit.html',
            'resourceList':'components/resourceList.html',

            'roleEdit':'components/roleEdit.html',

            'websiteQuery':'components/websiteQuery.html',
            'channelEdit':'components/channelEdit.html',

            'newsEdit':'components/newsEdit.html',


            'adEdit':'components/adEdit.html',
            'adPositionsEdit':'components/adPositionsEdit.html',
            'adPositionsList':'components/adPositionsList.html',


            'homeCtrl':'components/homeCtrl.html',

            'configQuery':'components/configQuery.html'



        };

    }
}






