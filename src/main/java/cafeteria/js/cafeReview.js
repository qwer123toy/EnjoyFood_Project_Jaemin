function toggleInput(menu) {
	var checkbox = document.getElementById('checkbox-' + menu);
	var numberInput = document.getElementById('input-' + menu);

	if (checkbox.checked) {
		numberInput.style.display = 'block'; // 활성화
	} else {
		numberInput.style.display = 'none'; // 비활성화
		numberInput.value = ''; // 값 초기화
	}
	calculateTotal(); // 총합 계산
}

function calculateTotal() {
	var total = 0;
	var priceInput = document.getElementById('price');
	var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	checkboxes.forEach(function(checkbox) {
		if (checkbox.checked) {
			var menuId = checkbox.id.split('-')[1];
			var quantityInput = document.getElementById('input-' + menuId);
			var quantity = parseFloat(quantityInput.value) || 0; // 수량
			var menuPrice = parseFloat(checkbox.getAttribute('data-price')) || 0; // 가격
			total += quantity * menuPrice; // 총합 계산
		}
	});
	priceInput.value = total; // 총합을 이용 금액에 추가
}

// 이미지 업로드 시 미리보기 기능
document.getElementById('imageUpload').addEventListener(
	'change',
	function(event) {
		const imagePreview = document
			.getElementById('imagePreview');
		const file = event.target.files[0];
		let userPic = document.querySelector("#userPic");
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				imagePreview.src = e.target.result;
				userPic.value = e.target.result;
				imagePreview.style.display = 'block';
			}
			reader.readAsDataURL(file);
		}
	});

document.addEventListener('DOMContentLoaded', function() {
	const stars = document.querySelectorAll('.star');
	const ratingValue = document.querySelector('.rating-value');
	const ratingInput = document.getElementById('rating');

	stars.forEach(star => {
		star.addEventListener('click', function() {
			const rating = this.getAttribute('data-value');

			// 클릭한 별까지 노란색으로 채우기
			stars.forEach((s, index) => {
				if (index > 4 - rating) {
					s.classList.add('selected');
				} else {
					s.classList.remove('selected');
				}
			});

			// 점수 표시 및 hidden input 값 설정
			ratingValue.textContent = rating + ".0";
			ratingInput.value = rating;
		});
	});
});

document.querySelector("#reviewBtn").addEventListener("click", (e) => {
	e.preventDefault();
	let form = document.querySelector("#reviewForm");
	let formData = new FormData(form);
	formData.delete("imageUpload");
	let json = JSON.stringify(Object.fromEntries(formData));
	console.log(json);
	fetch("/cafeReview", {
		method: "post",
		body: json
	}).then((resp) => resp.json())
		.then((result) => {
			if (result.success) {
				alert("작성 완료");
				location.href = "\mainpage"
			} else {
				if (result.message === "로그인") {
					alert("로그인을 해주세요");
					location.href = "\login";
				}else if(result.message==="실패"){
					alert("작성 실패");
					location.href="\mainpage"
				}
			}
		});

})