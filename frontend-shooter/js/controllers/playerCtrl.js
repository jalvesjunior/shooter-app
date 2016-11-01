angular.module("shooterApp").controller("playerCtrl", function($scope, $http) {

	var url = "http://localhost:8081/shooter-service/rs/player";

	var init = function() {
		$http.get(url)
			.success(function(data) {
				$scope.players = data;
			})
			.error(function(errorMessage) {
				console.log("erro ao consumir servico [" + url + "]. Erro: " + errorMessage);
			});
	};
	init();

	$scope.savePlayer = function(player) {
		var isPOST = player.id == null;
		player.birthDate = convertTextToDate(player.birthDate);
		if(isPOST) {
			$http.post(url, player).then(function(response) {
				updateGridCallback(player);
			}, function(responseError) {
				errorServiceCallback(responseError);
			});
		} else {
			$http.put(url, player).then(function(response) {
				updateGridCallback(player);
			}, function(responseError) {
				errorServiceCallback(responseError);
			});
		}
		$('#finalizeModal').modal('show');
		$scope.clearForm();
	}

	$scope.clearForm = function () {
		delete $scope.player;
		delete $scope.selectedPlayer;
	}
	
	var updateGridCallback = function(player) {
		var playerFound = $scope.players.filter(function(item) {
			return item.id == player.id;
		});

		var selectedIndex = $scope.players.indexOf(playerFound[0]);
		if(selectedIndex < 0) {
			$scope.players.push(angular.copy(player));
			return;
		}
		// atualizando a grid
		$scope.players[selectedIndex] = player;
		delete $scope.selectedPlayer;
		$scope.actionMessage = "Salvo com sucesso.";
	}

	var errorServiceCallback = function(data) {
		console.log("Erro ao executar servico: " + data);
	}
	

	$scope.editPlayer = function(player) {
		$scope.selectedPlayer = angular.copy(player);
		$scope.selectedPlayer.birthDate = convertDateToText(player.birthDate);
		delete player;
	}

	$scope.showModalRemovePlayer = function(player) {
		$scope.selectedPlayerExclude = player;
		$('#romovePlayerModal').modal('show');
	}

	$scope.removePlayer = function(player) {
		$http.delete(url + "/" + player.id).then(
	       function(response) {
	       		var selectedIndex = $scope.players.indexOf(player);
				$scope.players.splice(selectedIndex, 1);
				$('#romovePlayerModal').modal('hide');

				$scope.actionMessage = "Removido";
				
				$('#finalizeModal').modal('show');
	       }, 
	       function(response){
	       		console.log("removido com ERRO: " + response);
	       }
	    );
	}

	var convertTextToDate = function(data) {
		var date = data.toString().split("/");
		var correctDate = date[2] + "/" + date[1] + "/" + date[0];
		return new Date(correctDate);
	}

	var convertDateToText = function(data) {
		var date = data.toString().split("-");
		return (date[2] + "/" + date[1] + "/" + date[0]);
	}
});