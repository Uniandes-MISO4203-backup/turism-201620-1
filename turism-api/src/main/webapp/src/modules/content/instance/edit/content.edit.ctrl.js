(function (ng) {

    var mod = ng.module("contentModule");

    mod.controller("contentEditCtrl", ["$scope", "$state", "content",
        function ($scope, $state, content) {
            $scope.currentRecord = content;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.contentForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('contentDetail', {contentId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('contentDetail');
                    }
                }
            };
        }]);
})(window.angular);