(function (ng) {

    var mod = ng.module("commentModule");

    mod.controller("commentListCtrl", ["$scope", '$state', 'comments', '$stateParams','Restangular',
        function ($scope, $state, comments, $params,Restangular) {
            $scope.records = comments;

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = comments.totalRecords;
            
            $scope.categorys = [];
            
      
            $scope.filtrar = function (parentCategory) {
                Restangular.all("comments").customGET(parentCategory).then(function (response) {                    
                        $scope.records=response;
                });
            };

            this.pageChanged = function () {
                $state.go('faqList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('faqNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                cancel: {
                    displayName: 'Go back',
                    icon: 'arrow-left',
                    fn: function () {
                        $state.go('agencyDetail');
                    }
                }

            };
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('faqDetail', {faqId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('faqEdit', {faqId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('faqDelete', {faqId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);