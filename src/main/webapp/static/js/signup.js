addEventListener("load", onload);

let businessOwnerBtn;
let businessOwnerFields;
let signupBtn;

let userID;
let userPW;
let confirmPW;
let userNickname;
let userPhoneNumber;
let userOwnerNumber;
let userPicture;
let inputPicture;

let errorID;
let errorPW;
let errorNickname;
let errorPhoneNumber;
let errorOwnerNumber;
let errorPicture;
const url = "/api/v1/user";

function onload() {
	businessOwnerBtn = document.querySelector('#businessOwnerBtn');
	businessOwnerFields = document.querySelector('#businessOwnerFields');
	businessOwnerBtn.addEventListener('click', toggleOwnerInput);
	signupBtn = document.querySelector("#signupBtn");

	form = document.querySelector("#signupForm");
	form.addEventListener("submit", trySignup);

	userID = document.querySelector("#userID");
	userPW = document.querySelector("#userPW");
	confirmPW = document.querySelector("#confirmPW");
	userNickname = document.querySelector("#userNickname");
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userOwnerNumber = document.querySelector("#userOwnerNumber");
	inputPicture = document.querySelector("#inputPicture");
	userPicture = document.querySelector("#userPicture");

	errorID = document.querySelector("#errorID");
	errorPW = document.querySelector("#errorPW");
	errorNickname = document.querySelector("#errorNickname");
	errorPhoneNumber = document.querySelector("#errorPhoneNumber");
	errorOwnerNumber = document.querySelector("#errorOwnerNumber");
	errorPicture = document.querySelector("#errorPicture");

	userID.addEventListener("blur", chkID);
	userPW.addEventListener("blur", chkPW);
	confirmPW.addEventListener("blur", chkPW);
	userNickname.addEventListener("blur", chkNickname);
	userPhoneNumber.addEventListener("blur", chkPhoneNumber);
	userPhoneNumber.addEventListener("input", formatPhoneNumber);
	userOwnerNumber.addEventListener("blur", chkOwnerNumber);
	userOwnerNumber.addEventListener("input", formatOwnerNumber);
	inputPicture.addEventListener("change", validateFile);
}

function chkID() {
	let id = userID.value;
	if (!isValidID(id)) {
		errorID.innerHTML = "아이디는 영문, 숫자 4~20자여야 합니다.";
		errorID.classList.remove("hidden");
		return;
	}
	isUnique("userID", id)
		.then((success) => {
			if (success) {
				errorID.classList.add("hidden");
			} else {
				errorID.innerHTML = "중복된 아이디입니다.";
				errorID.classList.remove("hidden");
			}
		});
}

function chkPW() {
	let pw = userPW.value;
	if (!isValidPW(pw)) {
		errorPW.innerHTML = "비밀번호는 영문, 숫자, 특문 조합해 8~20자여야 합니다.";
		errorPW.classList.remove("hidden");
		return;
	}
	if (isPWSame(pw)) {
		errorPW.classList.add("hidden");
	} else {
		errorPW.innerHTML = "비밀번호가 일치하지 않습니다.";
		errorPW.classList.remove("hidden");
	}
}

function chkNickname() {
	let nickname = userNickname.value;
	if (!isValidNickname(nickname)) {
		errorNickname.innerHTML = "닉네임은 영문, 숫자 4~20자여야 합니다.";
		errorNickname.classList.remove("hidden");
		return;
	}
	isUnique("userNickname", nickname)
		.then((success) => {
			if (success) {
				errorNickname.classList.add("hidden");
			} else {
				errorNickname.innerHTML = "중복된 닉네임입니다.";
				errorNickname.classList.remove("hidden");
			}
		});
}

function chkPhoneNumber() {
	let phoneNumber = userPhoneNumber.value;
	if (!isValidPhoneNumber(phoneNumber)) {
		errorPhoneNumber.innerHTML = "전화번호 입력을 정확하게 해주세요.";
		errorPhoneNumber.classList.remove("hidden");
		return;
	}
	isUnique("userPhoneNumber", phoneNumber)
		.then((success) => {
			if (success) {
				errorPhoneNumber.classList.add("hidden");
			} else {
				errorPhoneNumber.innerHTML = "중복된 전화번호입니다.";
				errorPhoneNumber.classList.remove("hidden");
			}
		});
}

function chkOwnerNumber() {
	let ownerNumber = userOwnerNumber.value;
	if (!isValidOwnerNumber(ownerNumber)) {
		errorOwnerNumber.innerHTML = "사업자 번호 입력을 정확하게 해주세요.";
		errorOwnerNumber.classList.remove("hidden");
		return;
	}
	isUnique("userOwnerNumber", ownerNumber)
		.then((success) => {
			if (success) {
				errorOwnerNumber.classList.add("hidden");
			} else {
				errorOwnerNumber.innerHTML = "중복된 사업자 번호입니다.";
				errorOwnerNumber.classList.remove("hidden");
			}
		});
}

function isUnique(type, value) {
	let query = url + "?" + type + "=" + value;
	return fetch(query)
		.then((resp) => resp.json())
		.then((authResponse) => authResponse.success);
}

function isPWSame(pw) {
	return pw == confirmPW.value;
}

function isValidID(id) {
	const regex = /^[a-zA-Z][a-zA-z0-9]{3,19}$/;
	return regex.test(id);
}

function isValidPW(pw) {
	if (pw.length < 8 || pw.length > 20) {
		return false;
	}

	const hasUpperCase = /[A-Z]/.test(pw);
	const hasLowerCase = /[a-z]/.test(pw);
	const hasNumber = /\d/.test(pw);
	const hasSpecialChar = /[~!@#$%^&*_\-+=`|\\(){}[\]:;"'<>,.?/]/.test(pw);

	return (hasUpperCase || hasLowerCase) && hasNumber && hasSpecialChar;
}

function isValidPhoneNumber(phoneNumber) {
	return phoneNumber.length > 11;
}

function isValidNickname(nickname) {
	const regex = /^[a-zA-Z][a-zA-Z0-9]{3,19}$/;
	return regex.test(nickname);
}

function isValidOwnerNumber(ownerNumber) {
	return ownerNumber.length === 12;
}

function toggleOwnerInput() {
	if (businessOwnerFields.classList.contains('hidden')) {
		businessOwnerFields.classList.remove('hidden');
		userOwnerNumber.setAttribute("required", "required");
		inputPicture.setAttribute("required", "required");
		businessOwnerBtn.textContent = '사업자 등록 취소';
	} else {
		businessOwnerFields.classList.add('hidden');
		errorOwnerNumber.classList.add("hidden");
		errorPicture.classList.add("hidden");
		userOwnerNumber.value = "";
		inputPicture.value = "";
		userPicture.value = "";
		userOwnerNumber.removeAttribute("required");
		inputPicture.removeAttribute("required");
		businessOwnerBtn.textContent = '사업자 등록';
	}
}

function formatPhoneNumber() {
	let value = userPhoneNumber.value.replace(/[^0-9]/g, '');
	if (value.length > 11) {
		value = value.slice(0, 11);
	}

	if (value.length === 11) {
		value = value.replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3');
	} else if (value.length > 6) {
		value = value.replace(/^(\d{3})(\d{3})(\d+)$/, '$1-$2-$3');
	} else if (value.length > 3) {
		value = value.replace(/^(\d{3})(\d+)/, '$1-$2');
	}

	userPhoneNumber.value = value;
}

function formatOwnerNumber() {
	let value = userOwnerNumber.value.replace(/[^0-9]/g, '');
	if (value.length > 10) {
		value = value.slice(0, 10);
	}

	if (value.length > 5) {
		value = value.replace(/^(\d{3})(\d{2})(\d+)$/, '$1-$2-$3');
	} else if (value.length > 3) {
		value = value.replace(/^(\d{3})(\d+)/, '$1-$2');
	}

	userOwnerNumber.value = value;
}

function validateFile(e) {
	const file = e.target.files[0];

	if (file) {
		const maxSize = 10 * 1024 * 1024;
		const validTypes = ['image/jpeg', 'image/jpg', 'image/png'];

		if (file.size > maxSize) {
			errorPicture.textContent = "파일 크기가 10MB를 초과합니다.";
			errorPicture.classList.remove("hidden");
			inputPicture.value = "";
			userPicture.value = "";
			return;
		}

		if (!validTypes.includes(file.type)) {
			errorPicture.textContent = "파일 형식이 유효하지 않습니다.";
			errorPicture.classList.remove("hidden");
			inputPicture.value = "";
			userPicture.value = "";
			return;
		}

		const reader = new FileReader();
		reader.onload = function(e) {
			userPicture.value = e.target.result;
		};
		reader.readAsDataURL(file);

		errorPicture.classList.add("hidden");
	}
}

function trySignup(e) {
	e.preventDefault();
	userID.focus();
	userPW.focus();
	userNickname.focus();
	userPhoneNumber.focus();
	if (!businessOwnerFields.classList.contains('hidden')) {
		userOwnerNumber.focus();
		inputPicture.focus();
	}
	signupBtn.focus();
	if (!errorID.classList.contains("hidden")) {
		userID.focus();
		return;
	} else if (!errorPW.classList.contains("hidden")) {
		userPW.focus();
		return;
	} else if (!errorNickname.classList.contains("hidden")) {
		userNickname.focus();
		return;
	} else if (!errorPhoneNumber.classList.contains("hidden")) {
		userPhoneNumber.focus();
		return;
	} else if (!errorOwnerNumber.classList.contains("hidden")) {
		userOwnerNumber.focus();
		return;
	} else if (!errorPicture.classList.contains("hidden")) {
		inputPicture.focus();
		return;
	}
	let formData = new FormData(form);
	formData.delete("confirmPW");
	formData.delete("inputPicture");
	let authRequest = {
		action: "signup",
		user: Object.fromEntries(formData)
	};
	let json = JSON.stringify(authRequest);
	console.log(json);
	fetch(url, { method: "post", body: json })
		.then((resp) => { return resp.json(); })
		.then((authResponse) => {
			console.log(authResponse);
		});
}