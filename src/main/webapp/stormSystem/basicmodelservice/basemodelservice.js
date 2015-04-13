stormapp.service('basemodelservice', function() {

	var baseModelService = {};
	var isAdminUser = false;

	
	baseModelService.getRequestId = function(){
		//Will be overwritten
	};
	
	baseModelService.executeEndpoint = function(endpoint, data, callback){
		var request = eval(endpoint + "(" + JSON.stringify(data) + ")");
		request.execute(callback);
	};

	return {
		baseModel : baseModelService
	};
	
});

