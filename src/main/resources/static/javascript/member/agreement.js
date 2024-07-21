document.addEventListener('DOMContentLoaded', function () {
    var checkboxes = document.querySelectorAll('input[name="chk"]');
    var submitBtn = document.getElementById('submitBtn');

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            var checkedCheckboxes = document.querySelectorAll('input[name="chk"]:checked');
            submitBtn.disabled = checkedCheckboxes.length !== checkboxes.length;
        });
    });

    // URL 검사 및 리디렉션
    var currentPage = window.location.href;
    if (currentPage.indexOf('signup2.html') !== -1) {
        window.location.href = 'signup.html';
    }
});