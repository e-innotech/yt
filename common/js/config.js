var config ={
    init:function(){
        $apiUrl = ' http://192.168.20.195:8080/';
        $admin = {
            'login':$apiUrl+'login',
            'logout':$apiUrl+'logout'
        };
        $user = {
            'update':$apiUrl+'user/update/pwd'
        };
    }
}