app.config(function($routeProvider) {
	$routeProvider.when("/player", {
		templateUrl: "view/player.html",
		controller: 'playerCtrl'
	});

	$routeProvider.when("/bodyPart", {
		templateUrl: "view/bodyPart.html",
		controller: 'bodyPartCtrl'
	});
});