document.addEventListener('DOMContentLoaded', function() {
    // 모달 열기
    document.getElementById('btnDonate').addEventListener('click', function() {
        document.getElementById('donateModal').style.display = 'block';
    });

    // 모달 닫기
    document.querySelector('.close').addEventListener('click', function() {
        document.getElementById('donateModal').style.display = 'none';
    });

    // 모달 바깥 클릭 시 닫기
    window.onclick = function(event) {
        if (event.target == document.getElementById('donateModal')) {
            document.getElementById('donateModal').style.display = 'none';
        }
    }
});