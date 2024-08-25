function selectAll(selectAll)  {
  const checkboxes
     = document.querySelectorAll('input[type="checkbox"]');

  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
}

// 입양동물 공고 정보 입력 안하면 alert
function submitAdoptForm(form) {

    form.name.value = form.name.value.trim();

    if ( form.name.value.length == 0 ) {
        alert("이름을 입력해주세요.");
        form.name.focus();
        return;
    }

    form.phone.value = form.phone.value.trim();

    if ( form.phone.value === "" ) {
        alert("연락처를 입력해주세요.");
        form.phone.focus();
        return;
    }

    form.email.value = form.email.value.trim();

    if ( form.email.value === "" ) {
        alert("이메일을 입력해주세요.");
        form.email.focus();
        return;
    }

    var email = form.email.value.trim();
    var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z]{2,}$/;

    if (exptext.test(email) == false ) {
        alert("이메일 형식이 올바르지 않습니다.");
        form.email.focus();
        return;
    }

    form.age.value = form.age.value.trim();

    if ( form.age.value === "" ) {
        alert("나이를 입력해주세요.");
        form.age.focus();
        return;
    }

    form.company.value = form.company.value.trim();

    if ( form.company.value.length == 0 ) {
        alert("직장을 입력해주세요.");
        form.company.focus();
        return;
    }

    form.sample6_postcode.value = form.sample6_postcode.value.trim();

    if ( form.sample6_postcode.value.length == 0 ) {
        alert("주소를 입력해주세요.");
        form.sample6_postcode.focus();
        return;
    }

    form.sample6_detailAddress.value = form.sample6_detailAddress.value.trim();

    if ( form.sample6_detailAddress.value.length == 0 ) {
        alert("상세주소를 입력해주세요.");
        form.sample6_detailAddress.focus();
        return;
    }

    // 개인정보 수집 및 이용에 대한 동의 체크박스 확인
    var privacyCheckbox = document.getElementById("privacyCheckbox");
    if (!privacyCheckbox.checked) {
        alert("개인정보 수집 및 이용에 동의해주세요.");
        return false;
    }

    // 입양 동의 체크박스 확인
    var agreementCheckboxes = document.querySelectorAll('.adopt-agreement input[type="checkbox"]');
    for (var i = 0; i < agreementCheckboxes.length; i++) {
        if (!agreementCheckboxes[i].checked) {
            alert("모든 입양 동의 항목에 동의해주세요.");
            return false;
        }
    }

    if ( form.file.value.length == 0 ) {
        alert("입양신청서 양식을 첨부해주세요.");
        form.file.focus();
        return;
    }

    // 모든 조건이 충족된 경우에만 폼 제출
    form.submit();
}


