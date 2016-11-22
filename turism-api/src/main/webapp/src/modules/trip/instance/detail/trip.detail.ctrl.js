/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {

    var mod = ng.module("tripModule");

    mod.controller("tripDetailCtrl", ['$scope', "$state", "$http", "$stateParams","Restangular",
        function ($scope, $state, $http, $stateParams, Restangular) {
             
            $http.get("/turism-api/api/trips/detail/" + $stateParams.tripId).then(function(response){
                $scope.currentRecord = response.data;
            });
            
            $scope.trip =  Restangular.one("trips/detail", $stateParams.tripId);

            
            $scope.edit = function(){
                $state.go('tripEdit');
            };
            
            $scope.rateTrip = function(){
                $state.go('raitingHomeList', {tripId: $stateParams.tripId});
            };
            
            $scope.actions = {
                create: { 
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('tripNew');
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function () {
                        $state.go('tripEdit');
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function () {
                        $state.go('tripDelete');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                list: {
                    displayName: 'List',
                    icon: 'th-list',
                    fn: function () {
                        $state.go('tripList');
                    }
                },
                category: {
                    displayName: 'Category',
                    icon: 'link',
                    fn: function () {
                        $state.go('tripCategoryList');
                    }
                },
                comments: {
                    displayName: 'Comments',
                    icon:'comment',
                    fn: function () {
                        $state.go('commentList');
                    }
                },
                taxes: {
                    displayName: 'Taxes',
                    icon: 'usd',
                    fn: function () {
                        $state.go('taxList');
                    }
                },
                raitings: {
                    displayName: 'Raitings',
                    icon: 'star',
                    fn: function () {
                        $state.go('raitingList');
                    }
                },
                news: {
                    displayName: 'News',
                    icon: 'pencil',
                    fn: function () {
                        $state.go('newsList');
                    }    
                },    
                contents: {
                    displayName: 'Contents',
                    icon: 'film',
                    fn: function () {
                        $state.go('contentList');
                    }
                },
                raitingTrip: {
                    displayName: 'Rate Trip',
                    icon: 'star',
                    fn: function () {
                        $state.go('raitingItemTripList');
                    }
                }
                
            };
        }]);
})(window.angular);
