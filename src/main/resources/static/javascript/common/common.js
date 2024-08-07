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
    return confirm('봉사활동 내용을 수정하시겠습니까?');
}

// 봉사 등록했을 때 알림
function volunteerCreate() {
    return confirm('봉사활동을 등록하시겠습니까?');
}

// 후가 작성했을 때 알림
function reviewCreate() {
    return confirm('봉사후기를 작성하시겠습니까?');
}

// 후가 수정했을 때 알림
function reviewModify() {
    return confirm('봉사후기 내용을 수정하시겠습니까?');
}

// 후가 삭제했을 때 알림
function reviewDelete() {
    return confirm('봉사후기를 삭제하시겠습니까?');
}

// 마켓 상품 등록할 때 알림
function productCreate() {
    return confirm('애니마켓에 상품을 등록하시겠습니까?');
}

// 마켓 상품 수정할 때 알림
function productModify() {
    return confirm('상품 내용을 수정하시겠습니까?');
}

// 마켓 상품 삭제할 때 알림
function productDelete() {
    return confirm('애니마켓에 등록된 상품을 삭제하시겠습니까?');
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

    if (urlParams.has('reviewCreateSuccess')) {
        alert('봉사후기가 등록되었습니다.');
    }

    if (urlParams.has('reviewModifySuccess')) {
        alert('봉사후기가 수정되었습니다.');
    }

    if (urlParams.has('createVolunteerSuccess')) {
        alert('봉사활동이 등록되었습니다.');
    }

    if (urlParams.has('reviewDeleteSuccess')) {
        alert('봉사후기가 삭제되었습니다.');
    }

    if(urlParams.has('productCreateSuccess')) {
        alert('애니마켓에 상품이 등록되었습니다.')
    }

    if(urlParams.has('productModifySuccess')) {
        alert('상품수정이 완료되었습니다.')
    }

    if(urlParams.has('productDeleteSuccess')) {
        alert('상품 삭제가 완료되었습니다.')
    }
};