(function (ng) {

    var mod = ng.module("contentModule");

    mod.controller("contentDeleteCtrl", ["$state", "content", function ($state, content) {
            this.confirmDelete = function () {
                content.remove().then(function () {
                    $state.go('contentList', null, {reload: true});
                });
            };
        }]);
})(window.angular);