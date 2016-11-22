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

    var mod = ng.module("itemModule");

    mod.controller("itemDetailCtrl", ['$scope', "$state", "item",
        function ($scope, $state, item) {
            $scope.currentRecord = item;
            
            //Methods for item actions
            $scope.tripRelatedEvent= function(){
                  $state.go('tripDetailItem', {tripId: item.trip.id});
            };
            
            //Methos for trip detail actions
            $scope.editItemWhislist= function(){
                  $state.go('itemEdit', {itemId: item.id});
            };
            
            $scope.rateItemTrip= function(){
                  $state.go('raitingItemTripList', {tripId: item.trip.id});
            };
            
            $scope.actions = {
                create: {
                    displayName: 'Add Item to wishlist',
                    icon: 'plus',
                    fn: function () {
                        $state.go('itemNew');
                    }
                },
                edit: {
                    displayName: 'Edit Item in the wishlist',
                    icon: 'edit',
                    fn: function () {
                        $state.go('itemEdit');
                    }
                },
                delete: {
                    displayName: 'Delete Item in the Wishlist',
                    icon: 'minus',
                    fn: function () {
                        $state.go('itemDelete');
                    }
                },
                refresh: {
                    displayName: 'Refresh items',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                list: {
                    displayName: 'Go to List',
                    icon: 'th-list',
                    fn: function () {
                        $state.go('itemList');
                    }
                },
                tripRelated: {
                    displayName: 'Trip Related',
                    icon: 'link',
                    fn: function () {
                        $state.go('tripDetailItem', {tripId: item.trip.id});
                    }
                }
            };
        }]);
})(window.angular);
