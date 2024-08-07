// 봉사정보 입력 안하면 alert
function submitProductForm(form) {
    form.title.value = form.title.value.trim();

    if ( form.title.value.length == 0 ) {
        alert("상품명을 입력해주세요.");
        form.title.focus();
        return;
    }

    form.price.value = form.price.value.trim();

    if ( form.price.value.length == 0 ) {
        alert("상품가격을 입력해주세요.");
        form.price.focus();
        return;
    }

    if (form.thumbnail.files.length === 0) {
        alert("상품 이미지를 선택해주세요.");
        form.thumbnail.focus();
        return;
    }

    form.description.value = form.description.value.trim();

    if ( form.description.value.length == 0 ) {
        alert("상품 설명을 입력해주세요.");
        form.description.focus();
        return;
    }

    form.submit();
}