// 입양동물 공고 정보 입력 안하면 alert
function submitAdoptNoticeForm(form) {

    form.name.value = form.name.value.trim();

    if ( form.name.value.length == 0 ) {
        alert("동물 이름을 입력해주세요.");
        form.name.focus();
        return;
    }

    form.regId.value = form.regId.value.trim();

    if ( form.regId.value === "" ) {
        alert("관리번호를 입력해주세요.");
        form.regId.focus();
        return;
    }

    form.adoptionStatusCd.value = form.adoptionStatusCd.value.trim();

    if ( form.adoptionStatusCd.value === "" ) {
        alert("입양상태를 입력해주세요.");
        form.adoptionStatusCd.focus();
        return;
    }

    form.classification.value = form.classification.value.trim();

    if ( form.classification.value === "" ) {
        alert("동물 분류를 입력해주세요.");
        form.classification.focus();
        return;
    }

    form.gender.value = form.gender.value.trim();

    if ( form.gender.value.length == 0 ) {
        alert("성별을 입력해주세요.");
        form.gender.focus();
        return;
    }

    form.rescueDate.value = form.rescueDate.value.trim();

    if ( form.rescueDate.value.length == 0 ) {
        alert("구조날짜를 입력해주세요.");
        form.rescueDate.focus();
        return;
    }

    form.age.value = form.age.value.trim();

    if ( form.age.value.length == 0 ) {
        alert("추정나이를 입력해주세요.");
        form.age.focus();
        return;
    }

    form.weight.value = form.weight.value.trim();

    if ( form.weight.value.length == 0 ) {
        alert("몸무게를 입력해주세요.");
        form.weight.focus();
        return;
    }

    form.hairColor.value = form.hairColor.value.trim();

    if ( form.hairColor.value.length == 0 ) {
        alert("털색을 입력해주세요.");
        form.hairColor.focus();
        return;
    }

    form.species.value = form.species.value.trim();

    if ( form.species.value.length == 0 ) {
        alert("동물종류를 입력해주세요.");
        form.species.focus();
        return;
    }

    form.memo.value = form.memo.value.trim();

    if ( form.memo.value.length == 0 ) {
        alert("기타정보를 입력해주세요.");
        form.memo.focus();
        return;
    }

    if ( form.thumbnail.value.length == 0 ) {
        alert("대표사진을 선택해주세요.");
        form.thumbnail.focus();
        return;
    }

    form.submit();
}


