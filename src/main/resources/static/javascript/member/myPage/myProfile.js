function confirmDelete() {
    if (confirm('정말 탈퇴하시겠습니까?\n충전하신 포인트와 업로드한 게시물 및 내역이 모두 삭제됩니다.')) {
        return true;
    } else {
        return false;
    }
}