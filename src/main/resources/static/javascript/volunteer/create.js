// 봉사정보 입력 안하면 alert
function submitVolunteerForm(form) {
    form.title.value = form.title.value.trim();

    if ( form.title.value.length == 0 ) {
        alert("봉사명을 입력해주세요.");
        form.title.focus();
        return;
    }

    form.startDate.value = form.startDate.value.trim();

    if ( form.startDate.value === "" ) {
        alert("봉사 시작 날짜를 선택해주세요.");
        form.startDate.focus();
        return;
    }

    form.endDate.value = form.endDate.value.trim();

    if ( form.endDate.value === "" ) {
        alert("봉사 끝나는 날짜를 선택해주세요.");
        form.endDate.focus();
        return;
    }

    form.deadLineDate.value = form.deadLineDate.value.trim();

    if ( form.deadLineDate.value === "" ) {
        alert("신청 마감 날짜를 선택해주세요.");
        form.deadLineDate.focus();
        return;
    }

    form.location.value = form.location.value.trim();

    if ( form.location.value.length == 0 ) {
        alert("봉사 장소를 입력해주세요.");
        form.location.focus();
        return;
    }

    form.limit.value = form.limit.value.trim();

    if ( form.limit.value.length == 0 ) {
        alert("최대 신청인원을 입력해주세요.");
        form.limit.focus();
        return;
    }

    if (form.thumbnailImg.files.length === 0) {
        alert("대표 이미지를 선택해주세요.");
        form.thumbnailImg.focus();
        return;
    }

    form.content.value = form.content.value.trim();

    if ( form.content.value.length == 0 ) {
        alert("봉사 내용을 입력해주세요.");
        form.content.focus();
        return;
    }

    form.submit();
}

// 이미지 미리보기
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
        var output = document.getElementById('preview');
        output.src = reader.result;
        output.style.display = 'block'; // 이미지가 로드되면 표시
    }
    reader.readAsDataURL(event.target.files[0]);
}

// 카카오맵 시작
var map;
var selectedPlace = null;
var currentMarker = null; // 현재 마커를 추적하는 변수 추가

// kakao 지도 여는 모달창
function openKakaoMapModal() {
    document.getElementById('kakaoMapModal').style.display = 'block';
    initializeMap(); // 모달 열 때 지도 초기화
}

// 닫기 버튼 눌렀을 때 모달창 닫기
function closeKakaoMapModal() {
    document.getElementById('kakaoMapModal').style.display = 'none';
}

// 카카오맵 초기화
function initializeMap() {
    var mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(36.3527, 127.3857), // 대전 시청 좌표
            level: 3
        };

    map = new kakao.maps.Map(mapContainer, mapOption);
}

// 검색창에 입력한 키워드로 장소 검색
function searchPlaces() {
    var keyword = document.getElementById('keyword').value;
    var places = new kakao.maps.services.Places();
    places.keywordSearch(keyword, placesSearchCB);
}

// 장소 검색 결과를 처리
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        var listEl = document.getElementById('placesList'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds();

        removeAllChildNodes(listEl);

        for (var i = 0; i < data.length; i++) {
            var itemEl = getListItem(i, data[i]);
            fragment.appendChild(itemEl);
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        listEl.appendChild(fragment);
        map.setBounds(bounds);
    }
}

// 검색 결과 항목 li 태그로 생성
function getListItem(index, places) {
    var el = document.createElement('li');
    el.innerHTML = places.place_name + '<br>' + places.address_name;
    el.onclick = function() {
        selectedPlace = places;
        displayPlaceOnMap(places);
        document.getElementById('selectButton').style.display = 'block';
    };
    return el;
}

// 선택된 장소를 지도에 표시
function displayPlaceOnMap(place) {
    var coords = new kakao.maps.LatLng(place.y, place.x);
    map.setCenter(coords);

    // 이전 마커가 있으면 제거
    if (currentMarker) {
        currentMarker.setMap(null);
    }

    // 새로운 마커 생성 및 표시
    currentMarker = new kakao.maps.Marker({
        map: map,
        position: coords
    });
}

// 선택한 장소를 확인
function selectPlace() {
    if (selectedPlace) {
        document.getElementById('location').value = selectedPlace.address_name;
        closeKakaoMapModal();
    }
}

// 특정 요소의 모든 자식 노드 제거
function removeAllChildNodes(el) {
    while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
    }
}