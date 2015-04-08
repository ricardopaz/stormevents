stormapp.service('userService', function($q, $http,userModelService) {
	
	var user = {};
	
	this.getUser = function(){
		return this.user;
	};
	this.setUser = function(user){
		this.user = user;
	};
	
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
	
	this.loginUser = function(email, password){
		var def = $q.defer();
		userModelService.loginUser(email, password, function(data){
			if(data.code == 200){
				def.resolve(data);
			}else{
				def.reject(data);
			}
		});
		return def.promise;
	};
	
	this.loginUserByFacebook = function(idFacebook, name, email, gender, picture){
		var def = $q.defer();
		userModelService.loginUserByFacebook(idFacebook, name, email, gender, picture, function(data){
			if(data.code == 200){
				def.resolve(data);
			}else{
				def.reject(data);
			}
		});
		return def.promise;
	};
	
	this.getUserByEmail = function(email){
		var def = $q.defer();
		userModelService.getUserByEmail(email, function(data){
			if(data.code == 200){
				def.resolve(data);
			}else{
				def.reject(data);
			}
		});
		return def.promise;
	};
	
});
