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