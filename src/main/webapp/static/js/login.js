addEventListener("load", onload);

let userID;
let userPW;
let error;
let form;
const url = "/api/v1/user";

function onload(e) {
	userID = document.querySelector("#userID");
	userPW = document.querySelector("#userPW");
	error = document.querySelector(".error");

	form = document.querySelector("#loginForm");
	form.addEventListener("submit", tryLogin);

	let signup = document.querySelector("#signup");
	signup.addEventListener("click", (e) => {
		location.href = "/signup";
	});
}


function tryLogin(e) {
	e.preventDefault();
	let query = url + "?method=login&userID=" + userID.value + "&userPW=" + userPW.value;

	fetch(query)
		.then((resp) => { return resp.status; })
		.then((status) => {
			if (status == 200) {
				form.submit();
			} else if (status == 400) {
				error.classList.remove("hidden");
				//				window.location.href = "https://www.daum.net";
			}
		})
}