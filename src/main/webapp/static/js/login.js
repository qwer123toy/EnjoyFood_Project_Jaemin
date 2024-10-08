addEventListener("load", onload);

let userID;
let userPW;
let error;
let form;
const url = "/api/v1/user";

function onload() {
	userID = document.querySelector("#userID");
	userPW = document.querySelector("#userPW");
	error = document.querySelector(".error");

	form = document.querySelector("#loginForm");
	form.addEventListener("submit", tryLogin);

	let signup = document.querySelector("#signup");
	signup.addEventListener("click", () => {
		location.href = "/signup";
	});
}


function tryLogin(e) {
	e.preventDefault();
	let authRequest = {
		action: "login",
		user: Object.fromEntries(new FormData(form))
	};
	let json = JSON.stringify(authRequest);
	fetch(url, { method: "post", body: json })
		.then((resp) => { return resp.json(); })
		.then((authResponse) => {
//				console.log(authResponse);
			if (authResponse.success) {
				window.location.href = "\mainpage"
			} else {
				userID.value = "";
				userPW.value = "";
				userID.focus();
				error.innerHTML = "아이디나 비밀번호가 일치하지 않습니다.";
				error.classList.remove("hidden");
			}
		});
}