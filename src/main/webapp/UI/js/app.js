var stormapp = angular.module('stormevents', [ 'ui.router', 'infinite-scroll', 'autocomplete' ]);

stormapp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');

	$stateProvider.state('system', {
		url : '/system',
		views : {
			'index' : {
				templateUrl : 'stormSystem/initialPage.html'
			}
		}
	})
	.state('site', {
		url : '/web',
		views : {
			'index' : {
				templateUrl : 'redirect.html'
			}
		}
	})
	.state('login', {
		url : '/login',
		views : {
			'index' : {
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

