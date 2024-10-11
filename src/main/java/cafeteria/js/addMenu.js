addEventListener("load", onload);

let menuInputpic;
let menuNamepic;
let imagePreview;
let plusMenuBtn;
let addMenuBtn;
let backBtn;

function onload() {
	menuInputpic = document.querySelector("#menuInputpic");
	menuNamepic = document.querySelector("#menuNamepic");
	imagePreview = document.querySelector("#imagePreview");
	menuInputpic.addEventListener('change', validateFile);
	backBtn = document.querySelector("#backBtn");
	backBtn.addEventListener("click", back);
	plusMenuBtn = document.querySelector("#plusMenuBtn");
	plusMenuBtn.addEventListener("click", plus);
	addMenuBtn = document.querySelector("#addMenuBtn");
	addMenuBtn.addEventListener("click", addMenu);
}

function back(e) {
	e.preventDefault(); // 기본 동작을 방지합니다 (예: 폼 제출)
	window.location.href = "http://localhost:8080/ownerPage"// 이전 페이지로 돌아갑니다
}

function plus(e) {
	e.preventDefault();
	// formContainer에 새로운 메뉴 입력 폼 추가
	const formContainer = document.getElementById("formContainer");
	// formContainer가 처음 클릭 시 보이도록 설정
	        if (formContainer.style.display === "none") {
	            formContainer.style.display = "block"; // formContainer 표시
	        }
	        
	        // 새로운 메뉴 입력 폼을 위한 div 생성
	        const newMenuItem = document.createElement("div");
	        newMenuItem.className = "menuItem";
	       
	        
	        // formContainer에 새로운 메뉴 입력 폼 추가
	        formContainer.appendChild(newMenuItem);
	    }
	    


function addMenu(e) {
	e.preventDefault();
	let formData = new FormData(document.querySelector("#addMenuForm"));
	formData.delete("menuInputpic");
	let json = JSON.stringify(Object.fromEntries(formData));
	fetch("/addMenu", { method: "post", body: json });
}

function validateFile(e) {
	const file = e.target.files[0];
	if (file) {
		const maxSize = 10 * 1024 * 1024;
		const validTypes = ['image/jpeg', 'image/jpg', 'image/png'];

		if (file.size > maxSize) {
			menuNamepic.value = "";
			imagePreview.src = "";
			imagePreview.alt = "파일 크기가 10MB를 초과합니다.";
			return;
		}

		if (!validTypes.includes(file.type)) {
			menuNamepic.value = "";
			imagePreview.src = "";
			imagePreview.alt = "파일 형식이 유효하지 않습니다.";
			return;
		}

		const reader = new FileReader();
		reader.onload = function(e) {
			menuNamepic.value = e.target.result;
			imagePreview.src = e.target.result;
		};
		reader.readAsDataURL(file);
	}
}
