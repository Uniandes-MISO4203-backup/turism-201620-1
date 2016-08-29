(function (ng) {

    var mod = ng.module("commentModule");

    mod.controller("commentDeleteCtrl", ["$state", "comment", function ($state, comment) {
            this.confirmDelete = function () {
                comment.remove().then(function () {
                    $state.go('commentList', null, {reload: true});
                });
            };
        }]);
})(window.angular);