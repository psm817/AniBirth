// 상품정보 입력 안하면 alert
function submitProductForm(form) {
    // 제목 확인
    form.title.value = form.title.value.trim();
    if (form.title.value.length == 0) {
        alert("상품명을 입력해주세요.");
        form.title.focus();
        return false;
    }

    // 카테고리 확인
    form.category.value = form.category.value.trim();
    if (form.category.value === "") {
        alert("상품 종류를 선택해주세요.");
        form.category.focus();
        return false;
    }

    // 가격 확인
    form.price.value = form.price.value.trim();
    const priceValue = parseFloat(form.price.value);
    if(isNaN(priceValue)) {
        alert("상품 가격을 입력해주세요.");
        form.price.focus();
        return false;
    }
    else if (priceValue <= 0) {
        alert("상품가격은 1원 이상입니다. \n다시 입력해주세요.");
        form.price.focus();
        return false;
    }

    // 이미지 확인
    if (form.thumbnailImg.files.length === 0) {
        alert("상품 이미지를 선택해주세요.");
        form.thumbnailImg.focus();
        return false;
    }

    // 설명 확인
    form.description.value = form.description.value.trim();
    if (form.description.value.length == 0) {
        alert("상품 설명을 입력해주세요.");
        form.description.focus();
        return false;
    }

    form.submit();
}