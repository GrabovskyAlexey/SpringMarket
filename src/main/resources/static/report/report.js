angular.module('market-front').controller('reportController', function ($scope, $http) {
    const filesPath = 'http://localhost:8189/files/';
    let stompClient = null;

    $scope.connect = () => {
        let socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/report', function (report) {
                let link = filesPath + report.body
                console.log(report)
                alert(link)
            });
        });
    }

    $scope.requestReport = () => {stompClient.send("/app/report", {})}

    const alert = (link) => {
        const wrapper = document.createElement('div')
        wrapper.innerHTML = [
            `<div class="alert alert-success alert-dismissible" role="alert">`,
            `   <div>Отчет готов, вы можете скачать его по <a href="${link}">ссылке</a></div>`,
            '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
            '</div>'
        ].join('')
        $('#liveAlertPlaceholder').append(wrapper)
    }

    $scope.connect();
});