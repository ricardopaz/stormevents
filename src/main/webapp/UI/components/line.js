stormapp.directive('line', function () {
	return {
	    restrict: 'E',
	    scope: {},
	    templateUrl: '/UI/components/line.html',
	    controller: function ($scope,$attrs) {
	        $scope.lineClass = $attrs.lineclass;
	    }
	};
});