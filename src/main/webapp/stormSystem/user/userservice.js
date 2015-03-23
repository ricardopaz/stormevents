stormapp.service('userService', function($q, $http,userModelService) {
	
	this.helloUser = function(name){
		var def = $q.defer();
		userModelService.helloUser(name, function(data){
			if(data.code == 200){
				def.resolve(data);
			}else{
				def.reject(data);
			}
		});
		return def.promise;
	};
	
});
