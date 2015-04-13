stormapp.controller('ConfigController', function($scope, userService) {
	
	$scope.user = userService.getUser();
	var userEmail = $.cookie("userEmail");
	
	if(!$scope.user){
		if(userEmail){
			userService.getUserByEmail(userEmail).then(function(data){
				$scope.user = data.content;
			});
		}else{
			window.location.href = "#/login";
		}
	}
	
	
});