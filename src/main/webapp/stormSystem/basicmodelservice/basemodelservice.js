stormapp.service('basemodelservice', function() {

	/*
	 * built by fhab@cesar.org.br
	 * baseModelService serve as a baseModel for all Models built on Client-Side.
	 * Those two functions set: getRequestId and executeEndpoint are bridges for our back-end process.
	 * */
	
	gapi.client.load('motoplay', 'v1', function() {}, '/_ah/api');	
	var baseModelService = {};
	var isAdminUser = false;

	
	baseModelService.getRequestId = function(){
		//Will be overwritten
	}
	
	baseModelService.executeEndpoint = function(endpoint, data, callback){
		var request = eval(endpoint + "(" + JSON.stringify(data) + ")");
		request.execute(callback);
	};

	return {
		baseModel : baseModelService
	};
	
});

