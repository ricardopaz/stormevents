var stormapp = angular.module('stormevents', [ 'ui.router', 'infinite-scroll', 'autocomplete' ]);

stormapp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');

	$stateProvider.state('index', {
		url : '/',
		views : {
			'' : {
				templateUrl : 'stormSystem/login/login.html'
			}
		}
	})
	.state('system', {
		url : '/system',
		views : {
			'' : {
				templateUrl : 'stormSystem/template/templateSystem.html'
			},
			'contentstorm@system' : {
				templateUrl : 'stormSystem/initialPage.html'
			}
		}
	})
	.state('config', {
		url : '/config',
		views : {
			'' : {
				templateUrl : 'stormSystem/template/templateSystem.html'
			},
			'contentstorm@config' : {
				templateUrl : 'stormSystem/config/config.html'
			}
		}
	})
	.state('site', {
		url : '/web',
		views : {
			'' : {
				templateUrl : 'redirect.html'
			}
		}
	})
	.state('login', {
		url : '/login',
		views : {
			'' : {
				templateUrl : 'stormSystem/login/login.html'
			}
		}
	});
});

gapi.client.load('stormEventsApi', 'v1', undefined, '/_ah/api');

stormapp.run(function() {
	google.load('visualization', '1', {
		'callback' : "console.debug('success');",
		'packages' : [ 'corechart' ]
	});
});

