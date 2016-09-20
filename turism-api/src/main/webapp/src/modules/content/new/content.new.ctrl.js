(function (ng) {

    var mod = ng.module("contentModule");

    mod.controller("contentNewCtrl", ["$scope", "$state", "contents",
        function ($scope, $state, contents) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.contentForm.$valid) {
                            contents.post($scope.currentRecord).then(function (rc) {
                                $state.go('contentDetail', {contentId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('contentList');
                    }
                }
            };
        }]);
})(window.angular);