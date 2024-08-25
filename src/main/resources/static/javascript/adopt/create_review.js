document.querySelector("form").addEventListener("submit", async function (event) {
    event.preventDefault(); // 기본 제출 동작 방지

    const editorContent = editor.getMarkdown();
    const formData = new FormData(this); // 폼 데이터 가져오기
    formData.set("content", editorContent); // 에디터 내용 추가

    try {
        const response = await fetch(this.action, {
            method: this.method,
            body: formData,
        });

        if (response.ok) {
            alert("입양 후기 작성이 완료되었습니다.");
            window.location.href = "/adopt/review_list"; // 후기 목록 페이지로 이동
        } else {
            alert("후기 작성에 실패했습니다. 다시 시도해 주세요.");
        }
    } catch (error) {
        console.error("작성 중 에러 발생:", error);
        alert("알 수 없는 오류가 발생했습니다. 나중에 다시 시도해 주세요.");
    }
});
