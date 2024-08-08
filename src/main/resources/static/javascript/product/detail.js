function addToCartAndConfirm() {
    const form = document.getElementById('add-to-cart-form');
    const action = form.getAttribute('action');

    fetch(action, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(new FormData(form))
    })
    .then(response => {
        if (response.ok) {
            alert('상품이 장바구니에 추가되었습니다.');
            if (confirm('장바구니로 이동하시겠습니까?')) {
                window.location.href = '/cart/list';
            }
        } else {
            alert('상품을 장바구니에 추가하는 데 실패했습니다.');
        }
    })
    .catch(error => {
        alert('상품을 장바구니에 추가하는 중 오류가 발생했습니다.');
    });
}

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.star-rating').forEach(function(starRatingElem) {
        var rating = starRatingElem.getAttribute('data-rating');
        starRatingElem.innerText = '★'.repeat(rating);
    });
    var averageStarElem = document.querySelector('.average-star-rating');
    if (averageStarElem) {
        var averageRating = averageStarElem.getAttribute('data-rating');
        averageStarElem.innerHTML = '★'.repeat(Math.floor(averageRating)) + (averageRating % 1 > 0 ? '☆' : '');
    }
});

function productReviewCreate() {
    const content = document.getElementById('content').value.trim();
    const starRating = document.querySelector('input[name="starRating"]:checked');

    if (!content) {
        alert('리뷰 내용을 입력해주세요.');
        return false;
    }

    if (!starRating) {
        alert('별점을 선택해주세요.');
        return false;
    }

    return confirm('해당 상품에 리뷰를 등록하시겠습니까?')
}