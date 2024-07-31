// 봉사후기 정보 입력 안하면 alert
function reviewSubmitButton(form) {
    form.title.value = form.title.value.trim();

    if ( form.title.value.length == 0 ) {
        alert("제목을 입력해주세요.");
        form.title.focus();
        return;
    }

    if (form.thumbnailImg.files.length === 0) {
        alert("대표 이미지를 선택해주세요.");
        form.thumbnailImg.focus();
        return;
    }

    form.content.value = form.content.value.trim();

    if ( form.content.value.length == 0 ) {
        alert("내용을 입력해주세요.");
        form.content.focus();
        return;
    }

    form.submit();
}