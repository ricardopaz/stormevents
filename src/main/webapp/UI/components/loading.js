stormapp.directive('loading', function () {
	return {
	    restrict: 'E',
	    scope: {},
	    templateUrl: '/UI/components/loading.html',
	    controller: function ($scope,$attrs) {
	        $scope.loadingclass = $attrs.loadingclass;
	    }
	};
});