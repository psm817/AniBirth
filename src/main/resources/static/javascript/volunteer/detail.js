// 카카오맵
$(document).ready(function() {
    // 카카오맵 모달 관련
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

    // 모달 닫기 관련
    span.on('click', function() {
        $(this).closest('.modal').hide();
    });

    $(window).on('click', function(event) {
        if ($(event.target).hasClass('modal')) {
            $(event.target).hide();
        }
    });

    // 신청인원보기 버튼 클릭 이벤트
    const applicantsModal = $('#applicants-modal');
    const showApplicantsBtn = $('#show-applicants-btn');
    if (showApplicantsBtn.length) {
        showApplicantsBtn.on('click', function() {
            applicantsModal.show();
            // 여기에 신청 인원 목록을 동적으로 로드하는 코드를 추가할 수 있습니다.
        });
    }
});

// 신청했을 때 알림
function confirmApply() {
    return confirm('봉사활동을 신청하시겠습니까?');
}

// 봉사 삭제했을 때 알림
function volunteerDelete() {
    return confirm('봉사활동을 삭제하시겠습니까?');
}

// 봉사 수정했을 때 알림
function volunteerModify() {
    return confirm('봉사활동을 수정하시겠습니까?');
}

// Check for the apply error parameter and show the alert if it's set
window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('applySuccess')) {
        alert('봉사활동이 신청되었습니다.');
    } else if (urlParams.has('error') && urlParams.get('error') === 'full') {
        alert('신청인원이 가득 찼습니다.');
    }

    if (urlParams.has('deleteSuccess')) {
        alert('봉사활동이 삭제되었습니다.');
    }

    if (urlParams.has('modifySuccess')) {
        alert('봉사활동이 수정되었습니다.');
    }
};