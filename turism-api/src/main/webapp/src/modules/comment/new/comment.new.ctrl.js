(function (ng) {

    var mod = ng.module("commentModule");

    mod.controller("commentNewCtrl", ["$scope", "$state", "comments",
        function ($scope, $state, comments) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.commentForm.$valid) {
                            comments.post($scope.currentRecord).then(function (rc) {
                                $state.go('commentDetail', {commentId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('commentList');
                    }
                }
            };
        }]);
})(window.angular);