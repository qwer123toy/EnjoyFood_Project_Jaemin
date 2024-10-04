addEventListener("load", onload);

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
	const businessOwnerBtn = document.querySelector('#businessOwnerBtn');
	const businessOwnerFields = document.querySelector('#businessOwnerFields');
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

	userID.addEventListener("input", chkDupleID);

	userPW.addEventListener("input", chkPW);
	confirmPW.addEventListener("input", chkPW);
	userNickname.addEventListener("input", chkDupleNickname);
	userPhoneNumber.addEventListener("input", chkDuplePhoneNumber);
	userOwnerNumber.addEventListener("input", chkDupleOwnerNumber);


	businessOwnerBtn.addEventListener('click', () => {
		if (businessOwnerFields.classList.contains('hidden')) {
			businessOwnerFields.classList.remove('hidden');
			businessOwnerBtn.textContent = '사업주 닫기';
		} else {
			businessOwnerFields.classList.add('hidden');
			userOwnerNumber.value="";
			businessOwnerBtn.textContent = '사업주';
		}
	});

	document.querySelector('#signupForm').addEventListener('submit', (event) => {
		event.preventDefault();
		alert('회원가입이 완료되었습니다!');
	});
}

function chkDupleID() {
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

function chkDupleNickname() {
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

function chkDuplePhoneNumber() {
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

function chkDupleOwnerNumber() {
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