angular.module("shooterApp").controller("bodyPartCtrl", function($scope, $http) {

	var url = "http://localhost:8081/shooter-service/rs/bodyPart";

	var init = function() {
		$http.get(url)
			.success(function(data) {
				$scope.bodyParts = data;
			})
			.error(function(errorMessage) {
				console.log("erro ao consumir servico [" + url + "]. Erro: " + errorMessage);
			});
	};
	init();

	$scope.saveBodyPart = function(bodyPart) {
		var isPOST = bodyPart.id == null;
		if(isPOST) {
			$http.post(url, bodyPart).then(function(response) {
				updateGridCallback(bodyPart);
			}, function(responseError) {
				errorServiceCallback(responseError);
			});
		} else {
			$http.put(url, bodyPart).then(function(response) {
				updateGridCallback(bodyPart);
			}, function(responseError) {
				errorServiceCallback(responseError);
			});
		}
		$('#finalizeModal').modal('show');
		$scope.clearForm();
	}

	$scope.clearForm = function () {
		delete $scope.bodyPart;
		delete $scope.selectedBodyPart;
	}
	
	var updateGridCallback = function(bodyPart) {
		var bodyPartFound = $scope.bodyParts.filter(function(item) {
			return item.id == bodyPart.id;
		});

		var selectedIndex = $scope.bodyParts.indexOf(bodyPartFound[0]);
		if(selectedIndex < 0) {
			$scope.bodyParts.push(angular.copy(bodyPart));
			return;
		}
		// atualizando a grid
		$scope.bodyParts[selectedIndex] = bodyPart;
		delete $scope.selectedBodyPart;
		$scope.actionMessage = "Salvo com sucesso.";
	}

	var errorServiceCallback = function(data) {
		console.log("Erro ao executar servico: " + data);
	}
	

	$scope.editBodyPart = function(bodyPart) {
		$scope.selectedBodyPart = angular.copy(bodyPart);
		delete bodyPart;
	}

	$scope.showModalRemoveBodyPart = function(bodyPart) {
		$scope.selectedBodyPartExclude = bodyPart;
		$('#romoveBodyPartModal').modal('show');
	}

	$scope.removeBodyPart = function(bodyPart) {
		$http.delete(url + "/" + bodyPart.id).then(
	       function(response) {
	       		var selectedIndex = $scope.bodyParts.indexOf(bodyPart);
				$scope.bodyParts.splice(selectedIndex, 1);
				$('#romoveBodyPartModal').modal('hide');

				$scope.actionMessage = "Removido";
				
				$('#finalizeModal').modal('show');
	       }, 
	       function(response){
	       		console.log("removido com ERRO: " + response);
	       }
	    );
	}
});