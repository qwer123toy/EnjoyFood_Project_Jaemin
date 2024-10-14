addEventListener("load", onload);

let menuItems = []; // 동적으로 생성된 메뉴 아이템을 저장하는 배열
let menuCount = 1;  // 초기 메뉴 수 (기본 1개 있음)
let tagCount = 1;   // 초기 태그 수

let backBtn, plusMenuBtn, addMenuBtn;

function onload() {
	// 초기 DOM 요소 가져오기 및 이벤트 리스너 설정
	document.querySelector("#menuInputpic1").addEventListener('change', (e) => validateFile(e, 1));
	backBtn = document.querySelector("#backBtn");
	backBtn.addEventListener("click", back);
	plusMenuBtn = document.querySelector("#plusMenuBtn");
	plusMenuBtn.addEventListener("click", addMenuSection); // 메뉴 추가
	addMenuBtn = document.querySelector("#addMenuBtn");
	addMenuBtn.addEventListener("click", addMenu); // 메뉴 저장
}

function back(e) {
	e.preventDefault(); // 기본 동작 방지 (예: 폼 제출)
	window.location.href = "http://localhost:8080/userInfo"; // 이전 페이지로 이동
}

function addMenuSection(e) {
	e.preventDefault(); // 기본 폼 제출 방지

	if (menuCount < 20) { // 최대 20개 제한
		menuCount++; // 메뉴 수 증가

		const menuContainer = document.querySelector(".menu-container");
		const newMenu = document.createElement("div");
		newMenu.classList.add("menu-item");

		// 새 메뉴 입력 폼 구성
		newMenu.innerHTML = `
            <input type="text" name="menuName${menuCount}" placeholder="메뉴명 입력">
            <input type="text" name="menuPrice${menuCount}" placeholder="메뉴 금액 입력">
            <h2>메뉴 사진을 선택해 주세요.</h2>
            <img id="imagePreview${menuCount}" class="uploaded-image" alt="이미지 미리보기">
            <input type="file" id="menuInputpic${menuCount}" name="menuInputpic${menuCount}" accept="image/*">
            <input type="hidden" id="menuNamepic${menuCount}" name="menuNamepic${menuCount}">
            <textarea name="menuExplain${menuCount}" placeholder="메뉴 설명을 입력해 주세요"></textarea>
        `;

		menuContainer.appendChild(newMenu);

		// 각 메뉴 객체를 배열에 저장
		let menuItem = {
			menuInputpic: newMenu.querySelector(`#menuInputpic${menuCount}`),
			menuNamepic: newMenu.querySelector(`#menuNamepic${menuCount}`),
			imagePreview: newMenu.querySelector(`#imagePreview${menuCount}`)
		};
		menuItems.push(menuItem);

		// 새 파일 입력 필드에 validateFile 이벤트 리스너 추가
		menuItem.menuInputpic.addEventListener("change", (e) => {
			validateFile(e, menuCount); // 메뉴별로 파일 검증 수행
		});

		// 숨겨진 필드에 메뉴 수 업데이트
		document.getElementById("menuCount").value = menuCount;
	} else {
		alert("메뉴는 최대 20개까지 추가할 수 있습니다.");
	}
}

function addMenu(e) {
	e.preventDefault(); // 기본 동작 방지

	let formData = new FormData(document.querySelector("#addMenuForm"));
	formData.delete("menuInputpic"); // 이미지 데이터 제거 후 base64로 처리

	// JSON 형식으로 변환 후 서버로 전송
	let json = JSON.stringify(Object.fromEntries(formData));
	fetch("/addMenu", {
		method: "post",
		headers: { "Content-Type": "application/json" },
		body: json,
	})
		.then((response) => {
			if (response.ok) {
				alert("메뉴가 성공적으로 저장되었습니다!");
				location.href="/mainpage";
			} else {
				alert("메뉴 저장에 실패했습니다.");
			}
		})
		.catch((error) => console.error("에러 발생:", error));
}

function validateFile(e, menuIndex) {
	const file = e.target.files[0];
	const menuNamepic = document.querySelector(`#menuNamepic${menuIndex}`);
	const imagePreview = document.querySelector(`#imagePreview${menuIndex}`);

	if (file) {
		const maxSize = 10 * 1024 * 1024; // 최대 파일 크기: 10MB
		const validTypes = ["image/jpeg", "image/jpg", "image/png"]; // 허용되는 파일 형식

		if (file.size > maxSize) {
			menuNamepic.value = "";
			imagePreview.src = "";
			imagePreview.alt = "파일 크기가 10MB를 초과합니다.";
			return;
		}

		if (!validTypes.includes(file.type)) {
			menuNamepic.value = "";
			imagePreview.src = "";
			imagePreview.alt = "유효하지 않은 파일 형식입니다.";
			return;
		}

		const reader = new FileReader();
		reader.onload = function (e) {
			menuNamepic.value = e.target.result; // base64 값 설정
			imagePreview.src = e.target.result; // 미리보기 이미지 설정
		};
		reader.readAsDataURL(file);
	}
}
