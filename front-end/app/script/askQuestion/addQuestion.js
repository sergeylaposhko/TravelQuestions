'use strict';

angular.module('tqApp')

.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/askQuestion/', {
			templateUrl: 'script/askQuestion/askQuestion.html',
			controller: 'AskQuestionController'
		});
 
	}])
	.controller('AskQuestionController', ['$scope', '$routeParams', 'loginService', 'Question',
		function($scope, $routeParams, loginService, Question) {
			$scope.userQuestion = {};
            
            $scope.sendQuestion = function() {
                alert($scope.userQuestion.text);
                Question.add({}, $.param({
                    sessionId: loginService.sessionId,
                    header: $scope.userQuestion.header,
                    text: $scope.userQuestion.text
                }), function(data){
                    alert(data);
                })
            }
            
		}


	]);