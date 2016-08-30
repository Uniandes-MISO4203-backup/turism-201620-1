(function (ng) {

    var mod = ng.module("commentModule");

    mod.controller("commentEditCtrl", ["$scope", "$state", "comment",
        function ($scope, $state, comment) {
            $scope.currentRecord = comment;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.commentForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('commentDetail', {commentId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('commentDetail');
                    }
                }
            };
        }]);
})(window.angular);