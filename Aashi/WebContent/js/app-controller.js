app.controller("loginController",function($scope){
    $scope.signin = true;
    $scope.signIn = function(){
        $scope.signin = true;
        $scope.signup = false;
    }
     $scope.signUp = function(){
        $scope.signin = false;
        $scope.signup = true;
    }
});

welcome.controller("welcomeController",function($scope,$http){
    $http.get("details").then(function(response){
        $scope.user = response.data;
    });
});