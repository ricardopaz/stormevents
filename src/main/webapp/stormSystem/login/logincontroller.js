stormapp.controller('loginController', function($scope, userService) {
	
	$scope.email = "";
	$scope.password = "";
	
	$.backstretch("UI/templateStormSystem/img/login-bg.jpg", {
		speed : 500
	});
	
	hello.init({
		facebook : 712005875579621
	},{
		scope : "friends", 
		oauth_proxy : 'https://auth-server.herokuapp.com/proxy',
		redirect_uri: 'http://localhost:8888/#/login'
	});
	
	hello.on('auth.login', function(r){
		// Get Profile
		hello( r.network ).api( '/me' ).then( function(user){
			userService.loginUserByFacebook(user.id, user.name, user.email, user.gender, user.picture).then(function(data){
				if(data.code==200){
					userService.setUser(data.content);
					window.location.href = "#/system";
				}
			});
		});
	});
	
	$scope.loginUser = function(){
		if($scope.email.trim() != "" && $scope.password.trim() != ""){
			userService.loginUser($scope.email,$scope.password).then(function(data){
				if(data.code == 200){
					if(data.content != null){
						userService.setUser(data.content);
						window.location.href = "#/system";
					}else{
						alert("Reveja seu login ou sua senha!");
					}
				}
			});
		}
	};
	
});