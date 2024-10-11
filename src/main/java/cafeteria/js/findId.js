addEventListener("load", onload);

let userPhoneNumber;
let result;
let form;

const url = "/api/v1/user";

function onload() {
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	result = document.querySelector("#result");

	form = document.querySelector("#findIdForm");
	form.addEventListener("submit", find);

	userPhoneNumber.addEventListener("input", formatPhoneNumber);

}

function find(e) {
	e.preventDefault();
	let value = userPhoneNumber.value;
	if (!isValidPhoneNumber(value)) {
		result.classList.remove("hidden");
		result.classList.add("error");
		result.innerHTML = "전화번호를 정확하게 입력해주세요.";
		return;
	}
	let query = url + "?action=findID&userPhoneNumber=" + value;
	fetch(query)
		.then((resp) => resp.json())
		.then((authResponse) => {
			result.classList.remove("hidden");
			if (authResponse.success)
				result.classList.remove("error");
			else
				result.classList.add("error");
			result.innerHTML = authResponse.message;
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

function isValidPhoneNumber(phoneNumber) {
	return phoneNumber.length > 11;
}