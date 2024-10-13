window.addEventListener("load", onload);

let menuInputpic;
let menuNamepic;
let imagePreview;
let addMenuBtn;
let confirmStoreBtn;

function onload() {
	menuInputpic = document.querySelector("#cafePic");
	menuNamepic = document.querySelector("#menuNamepic");
	menuInputpic.addEventListener('change', validateFile);
	imagePreview = document.querySelector("#imagePreview");
	confirmStoreBtn = document.querySelector("#ownerPage");
	confirmStoreBtn.addEventListener("submit", addCafeteria);

}

function addCafeteria(e) {
	let formData = new FormData(document.querySelector("#ownerPage"));
	formData.delete("menuInputpic");
	let json = JSON.stringify(Object.fromEntries(formData));
	console.log(json);
	fetch("/ownerPage", { method: "post", body: json });
}

function validateFile(e) {
	e.preventDefault();
		
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
