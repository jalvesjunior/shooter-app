app.config(function($routeProvider) {
	$routeProvider
		.when("/player", {
			templateUrl: "view/player.html",
			controller: 'playerCtrl'
		})
		.when("/bodyPart", {
			templateUrl: "view/bodyPart.html",
			controller: 'bodyPartCtrl'
		});
});