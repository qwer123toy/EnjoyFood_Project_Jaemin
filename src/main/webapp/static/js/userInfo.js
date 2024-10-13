addEventListener("load", onload);

let userID;
let userNickname;
let userPhoneNumber;
let userOwnerNumber;

let prevPW;
let userPW;
let confirmPW;

let errorPW;
let errorNickname;
let errorPhoneNumber;

const url = "/api/v1/user";

function onload() {
	userID = document.querySelector("#userID");
	userNickname = document.querySelector("#userNickname");
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userOwnerNumber = document.querySelector("#userOwnerNumber");

	prevPW = document.querySelector("#prevPW");
	userPW = document.querySelector("#userPW");
	confirmPW = document.querySelector("#confirmPW");

	errorPW = document.querySelector("#errorPW");
	errorNickname = document.querySelector("#errorNickname");
	errorPhoneNumber = document.querySelector("#errorPhoneNumber");

	userPW.addEventListener("blur", chkPW);
	confirmPW.addEventListener("blur", chkPW);
	userNickname.addEventListener("blur", chkNickname);
	userPhoneNumber.addEventListener("blur", chkPhoneNumber);
	userPhoneNumber.addEventListener("input", formatPhoneNumber);

	let query = url + "?action=userInfo";
	fetch(query)
		.then((resp) => resp.json())
		.then((user) => {
			console.log(user);
			userID.value = user.userID;
			userNickname.value = user.userNickname;
			userPhoneNumber.value = user.userPhoneNumber;
			if (user.userOwnerNumber != null) {
				userOwnerNumber.value = user.userOwnerNumber;
				userOwnerNumber.parentElement.classList.remove("hidden");
			}
		});
}

function toggleInputPW() {
	let inputPW = document.querySelector("#inputPW");
	if (inputPW.classList.contains("hidden"))
		inputPW.classList.remove("hidden");
	else
		inputPW.classList.add("hidden");
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

function isUnique(type, value) {
	let query = url + "?action=chkDuple&" + type + "=" + value;
	return fetch(query)
		.then((resp) => resp.json())
		.then((authResponse) => authResponse.success);
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

function isPWSame(pw) {
	return pw == confirmPW.value;
}

function isValidPhoneNumber(phoneNumber) {
	return phoneNumber.length > 11;
}

function isValidNickname(nickname) {
	const regex = /^[a-zA-Z][a-zA-Z0-9]{3,19}$/;
	return regex.test(nickname);
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