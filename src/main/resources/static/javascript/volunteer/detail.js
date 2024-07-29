// 카카오맵
$(document).ready(function() {
    var mapModal = $('#map-modal');
    var span = $(".close");

    $('#show-map-btn').on('click', function() {
        mapModal.show();

        var geocoder = new kakao.maps.services.Geocoder();
        var address = $('#volunteer-location').text();

        geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                var mapContainer = document.getElementById('map');
                var mapOption = {
                    center: coords,
                    level: 3
                };
                var map = new kakao.maps.Map(mapContainer, mapOption);
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
            }
        });
    });

    span.on('click', function() {
        mapModal.hide();
    });

    $(window).on('click', function(event) {
        if (event.target == mapModal[0]) {
            mapModal.hide();
        }
    });
});

// 신청했을 때 알림
function confirmApply() {
    return confirm('봉사활동을 신청하시겠습니까?');
}

// Check for the apply error parameter and show the alert if it's set
window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('applySuccess')) {
        alert('봉사활동이 신청되었습니다.');
    } else if (urlParams.has('error') && urlParams.get('error') === 'full') {
        alert('신청인원이 가득 찼습니다.');
    }
};