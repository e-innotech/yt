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





        $components = {
            'pwdReset':'components/pwdReset.html',
            'userGroup':'components/userGroup.html',
            'userQuery':'components/userQuery.html',
            'resourceEdit':'components/resourceEdit.html',
            'resourceList':'components/resourceList.html'
        }
    }
}







