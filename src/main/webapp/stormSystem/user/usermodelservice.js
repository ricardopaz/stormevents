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
	
	return {
		helloUser : helloUser
	};
});