var isLoginPage = window.location.href.indexOf("login") != -1;
if(isLoginPage){
    if($cookies.get("access_token")){
        window.location.href = "index";
    }
} else{
    if($cookies.get("access_token")){
        $http.defaults.headers.common.Authorization =
          'Bearer ' + $cookies.get("access_token");
    } else{
        window.location.href = "login";
    }
}
