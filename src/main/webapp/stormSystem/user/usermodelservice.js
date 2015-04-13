stormapp.service('userModelService', function(basemodelservice) {
	var base = basemodelservice.baseModel;
	
	var getRequestId = function() {
		return 'gapi.client.stormEventsApi';
	};

	var helloUser = function(name, callback) {
		var data = {
				name : name
				};
		var endpoint = getRequestId() + '.sayHi';
		base.executeEndpoint(endpoint, data, callback);
	};
	
	var loginUser = function(email, password, callback) {
		var data = {
				email : email,
				password : password
				};
		var endpoint = getRequestId() + '.user.login';
		base.executeEndpoint(endpoint, data, callback);
	};
	
	var loginUserByFacebook = function(idFacebook, name, email, gender, picture, callback) {
		var data = {
				idFacebook : idFacebook,
				name : name,
				email : email,
				gender : gender,
				picture : picture
				};
		var endpoint = getRequestId() + '.user.faceLogin';
		base.executeEndpoint(endpoint, data, callback);
	};
	
	var getUserByEmail = function(email, callback) {
		var data = {
				email : email
				};
		var endpoint = getRequestId() + '.user.byemail';
		base.executeEndpoint(endpoint, data, callback);
	};
	
	var newUser = function(name, email, password, gender, callback) {
		var data = {
				name : name,
				email : email,
				password : password,
				gender : gender
				};
		var endpoint = getRequestId() + '.new.user';
		base.executeEndpoint(endpoint, data, callback);
	};
	
	return {
		helloUser : helloUser,
		loginUser : loginUser,
		loginUserByFacebook : loginUserByFacebook,
		getUserByEmail : getUserByEmail,
		newUser : newUser 
	};
});