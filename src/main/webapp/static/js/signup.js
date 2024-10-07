addEventListener("load", onload);

let businessOwnerBtn;
let businessOwnerFields;

let userID;
let userPW;
let confirmPW;
let userNickname;
let userPhoneNumber;
let userOwnerNumber;
let userPicture;

let errorID;
let errorPW;
let errorNickname;
let errorPhoneNumber;
let errorOwnerNumber;
const url = "/api/v1/user";

function onload(e) {
	businessOwnerBtn = document.querySelector('#businessOwnerBtn');
	businessOwnerFields = document.querySelector('#businessOwnerFields');
	userID = document.querySelector("#userID");
	userPW = document.querySelector("#userPW");
	confirmPW = document.querySelector("#confirmPW");
	userNickname = document.querySelector("#userNickname");
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userOwnerNumber = document.querySelector("#userOwnerNumber");
	userPicture = document.querySelector("#userPicture");

	errorID = document.querySelector("#errorID");
	errorPW = document.querySelector("#errorPW");
	errorNickname = document.querySelector("#errorNickname");
	errorPhoneNumber = document.querySelector("#errorPhoneNumber");
	errorOwnerNumber = document.querySelector("#errorOwnerNumber");

	userID.addEventListener("blur", chkID);

	userPW.addEventListener("blur", chkPW);
	confirmPW.addEventListener("blur", chkPW);
	userNickname.addEventListener("blur", chkNickname);
	userPhoneNumber.addEventListener("blur", chkPhoneNumber);
	userOwnerNumber.addEventListener("blur", chkOwnerNumber);


	businessOwnerBtn.addEventListener('click', toggleOwnerInput);

	document.querySelector('#signupForm').addEventListener('submit', (event) => {
		event.preventDefault();
		alert('회원가입이 완료되었습니다!');
	});
}

function chkID() {
	let query = url + "?method=chkID&userID=" + userID.value;
	fetch(query)
		.then((resp) => { return resp.status; })
		.then((status) => {
			if (status == 200) {
				errorID.classList.add("hidden");
			} else {
				errorID.classList.remove("hidden");
			}
		})
}

function chkPW() {
	console.log("userp" + userPW.value);
	console.log("userc" + userPW.value);
	if (userPW.value == confirmPW.value) {
		errorPW.classList.add("hidden");
	} else {
		errorPW.classList.remove("hidden");
	}
}

function chkNickname() {
	let query = url + "?method=chkNickname&userNickname=" + userNickname.value;
	fetch(query)
		.then((resp) => { return resp.status; })
		.then((status) => {
			if (status == 200) {
				errorNickname.classList.add("hidden");
			} else {
				errorNickname.classList.remove("hidden");
			}
		})
}

function chkPhoneNumber() {
	if (isValidPhoneNumber(userPhoneNumber.value)) {
		errorPhoneNumber.classList.add("hidden");
	} else {
		errorPhoneNumber.classList.remove("hidden");
		return;
	}
	let query = url + "?method=chkPhoneNumber&userPhoneNumber=" + userPhoneNumber.value;
	fetch(query)
		.then((resp) => { return resp.status; })
		.then((status) => {
			if (status == 200) {
				errorPhoneNumber.classList.add("hidden");
			} else {
				errorPhoneNumber.classList.remove("hidden");
			}
		})
}

function chkOwnerNumber() {
	let query = url + "?method=chkOwnerNumber&userOwnerNumber=" + userOwnerNumber.value;
	fetch(query)
		.then((resp) => { return resp.status; })
		.then((status) => {
			if (status == 200) {
				errorOwnerNumber.classList.add("hidden");
			} else {
				errorOwnerNumber.classList.remove("hidden");
			}
		})
}

function toggleOwnerInput() {
	if (businessOwnerFields.classList.contains('hidden')) {
		businessOwnerFields.classList.remove('hidden');
		userOwnerNumber.setAttribute("required", "required");
		userPicture.setAttribute("required", "required");
		businessOwnerBtn.textContent = '사업주 닫기';
	} else {
		businessOwnerFields.classList.add('hidden');
		userOwnerNumber.value = "";
		userOwnerNumber.removeAttribute("required");
		userPicture.removeAttribute("required");
		businessOwnerBtn.textContent = '사업주';
	}
}

function isValidID(id) {
	const regex = /^[a-zA-Z]\w{3,19}$/;
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
	console.log(hasUpperCase);
	console.log(hasLowerCase);
	console.log(hasNumber);
	console.log(hasSpecialChar);

	return hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;
}

function isValidPhoneNumber(phoneNumber) {
	const regex = /^(01[0-9]-\d{3,4}-\d{4}|0\d{2}-\d{3,4}-\d{4})$/;
	return regex.test(phoneNumber);
}

function isValidNickname(nickname) {
	const regex = /^[a-zA-Z][a-zA-Z0-9]{3,19}$/;
	return regex.test(nickname);
}