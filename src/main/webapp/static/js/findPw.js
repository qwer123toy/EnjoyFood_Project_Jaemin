addEventListener("load", onload);

let userPhoneNumber;
let userID;
let userPW;
let confirmPW;
let result;
let form;
let findPw;
let changePw;

const url = "/api/v1/user";

function onload() {
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userID = document.querySelector("#userID");
	userPW = document.querySelector("#userPW");
	confirmPW = document.querySelector("#confirmPW");
	result = document.querySelector("#result");
	title = document.querySelector("#title");
	form = document.querySelector("#findPwForm");

	findPw = document.querySelector("#findPw");
	findPw.addEventListener("click", find);

	changePw = document.querySelector("#changePw");
	changePw.addEventListener("click", change);

	userPhoneNumber.addEventListener("input", formatPhoneNumber);

}

function find(e) {
	e.preventDefault();
	let idValue = userID.value;
	let phoneValue = userPhoneNumber.value;
	if (!isValidID(idValue)) {
		result.classList.remove("hidden");
		result.innerHTML = "아이디를 정확하게 입력해주세요.";
		return;
	}
	if (!isValidPhoneNumber(phoneValue)) {
		result.classList.remove("hidden");
		result.innerHTML = "전화번호를 정확하게 입력해주세요.";
		return;
	}
	let query = url + "?action=findPW&userID=" + idValue + "&userPhoneNumber=" + phoneValue;
	fetch(query)
		.then((resp) => resp.json())
		.then((authResponse) => {
			if (authResponse.success) {
				alert("비밀번호를 변경해주세요");
				result.classList.add("hidden");
				userID.classList.add("hidden");
				userPhoneNumber.classList.add("hidden");
				findPw.classList.add("hidden");
				document.querySelector('label[for="userID"]').classList.add("hidden");
				document.querySelector('label[for="userPhoneNumber"]').classList.add("hidden");
				userPW.classList.remove("hidden");
				confirmPW.classList.remove("hidden");
				changePw.classList.remove("hidden");
				document.querySelector('label[for="userPW"]').classList.remove("hidden");
				document.querySelector('label[for="confirmPW"]').classList.remove("hidden");
				title.innerHTML = "비밀번호 변경";
			} else {
				result.classList.remove("hidden");
				result.innerHTML = authResponse.message;
			}
		});
}

function change(e) {
	e.preventDefault();
	let pwValue = userPW.value;
	if (!isValidPW(pwValue)) {
		result.classList.remove("hidden");
		result.innerHTML = "비밀번호는 영문, 숫자, 특문 조합해 8~20자여야 합니다.";
		return;
	}
	if (!isPWSame(pwValue)) {
		result.classList.remove("hidden");
		result.innerHTML = "비밀번호가 일치하지 않습니다.";
		return;
	}
	result.classList.add("hidden");
	let formData = new FormData(form);
	formData.delete("confirmPW");
	let authRequest = {
		action: "changePW",
		user: Object.fromEntries(formData)
	};
	let json = JSON.stringify(authRequest);

	fetch(url, { method: "put", body: json })
		.then((resp) => resp.json())
		.then((authResponse) => {
			alert(authResponse.message);
			if (authResponse.success)
				location.href = "/login";
		});
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

function isValidID(id) {
	const regex = /^[a-zA-Z][a-zA-z0-9]{3,19}$/;
	return regex.test(id);
}

function isValidPhoneNumber(phoneNumber) {
	return phoneNumber.length > 11;
}

function isPWSame(pw) {
	return pw == confirmPW.value;
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