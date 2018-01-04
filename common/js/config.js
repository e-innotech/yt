var config ={
    init:function(){
        $apiUrl = ' http://192.168.20.195:8080';
        $admin = {
            'login':$apiUrl+'/login',
            'logout':$apiUrl+'/logout'
        };
        $user = {
            'update':$apiUrl+'/user/update/pwd'
        };


        $query = {
            'ad':$apiUrl+'/ad/query',
            'adPositions':$apiUrl+'/adPositions/query',
            'channel':$apiUrl+'/channel/query',
            'members':$apiUrl+'/members/query',
            'news':$apiUrl+'/news/query',

            'resource':$apiUrl+'/resource/query',
            'role':$apiUrl+'/resource/query',
            'user':$apiUrl+'/user/query',
            'userGroup':$apiUrl+'/userGroup/query',
            'log':$apiUrl+'/log/query',
            'websites':$apiUrl+'/websites/query',
            'config':$apiUrl+'/config/query'
        };




        $components = {
            'pwdReset':'components/pwdReset.html',

            'resourceEdit':'components/resourceEdit.html',
            'resourceList':'components/resourceList.html',

            'roleEdit':'components/roleEdit.html'
        }
    }
}