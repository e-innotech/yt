var config ={
    init:function(){
        // $apiUrl = ' http://192.168.20.195:8080';
        $apiUrl = ' http://123.59.156.27:8080';
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

            'resourceEdit':'components/resourceEdit.html',
            'resourceList':'components/resourceList.html',

            'roleEdit':'components/roleEdit.html',

            'websiteQuery':'components/websiteQuery.html',
            'channelQuery':'components/channelQuery.html',

            'newsQuery':'components/newsQuery.html',
            'adEdit':'components/adEdit.html',
            'adPositionsEdit':'components/adPositionsEdit.html'
        };

    }
}







