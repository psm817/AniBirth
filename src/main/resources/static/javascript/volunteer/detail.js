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