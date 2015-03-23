stormapp.controller('UserController', function($scope, userService) {
	
	$scope.hello = "";
	$scope.userName = "Ricardo";
	
	//
	// OAUTH PROXY
	//
	var fb = hello( "facebook" ).getAuthResponse();
	
	hello.init({
		facebook : 712005875579621
	},{
		scope : "friends", 
		oauth_proxy : 'https://auth-server.herokuapp.com/proxy',
		redirect_uri: 'http://localhost:8888/#/'
	});

	hello.on('auth.login', function(r){
		// Get Profile
		hello( r.network ).api( '/me' ).then( function(p){
			var label = document.getElementById(r.network);
			label.innerHTML = "<img src='"+ p.thumbnail + "' width=24/> Connected to "+ r.network +" as " + p.name;
			$scope.hello = p.name + " logged in " + r.network;
		});
	});
	
	$scope.getFriends = function (network, path){
		var list = document.getElementById('list');
		list.innerHTML = '';
		var btn_more = document.getElementById('more');
		btn_more.style.display = 'none';
		// login
		hello.login( network, {scope:'friends'}, function(auth){
			if(!auth||auth.error){
				console.log("Signin aborted");
				return;
			}
			// Get the friends
			// using path, me/friends or me/contacts
			hello( network ).api( path , {limit:10}, function responseHandler(r){
				for(var i=0;i<r.data.length;i++){
					var o = r.data[i];
					var li = document.createElement('li');
					li.innerHTML = o.name + (o.thumbnail?" <img src="+o.thumbnail.data.url+" />":'');
					list.appendChild(li);
				};
				btn_more.onclick = function(){
					// Make another request and handle is in the same way
					hello( network ).api( r.paging.next, {limit:10}, responseHandler );
				};
				btn_more.innerHTML = "Next from "+network;
				btn_more.style.display = 'block';
			});
		});
	};
	
	$scope.helloUser = function(){
		userService.helloUser($scope.userName).then(function(data){
			$scope.hello = data.content.name;
		});
	};
	
});