addEventListener("load", onload);

let userID;
let userPhoneNumber;
let userOwnerNumber;

const url = "/api/v1/user";

function onload() {
	userID = document.querySelector("#userID");
	userNickname = document.querySelector("#userNickname");
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userOwnerNumber = document.querySelector("#userOwnerNumber");

	console.log(sessionStorage.getItem("userID"));
	let query = url + "?action=userInfo";
	fetch(query)
		.then((resp) => resp.json())
		.then((user) => {
			console.log(user);
			userID.innerHTML += user.userID;
			userNickname.innerHTML += user.userNickname;
			userPhoneNumber.innerHTML += user.userPhoneNumber;
			if (user.userOwnerNumber != null) {
				userOwnerNumber.innerHTML += user.userOwnerNumber;
				userOwnerNumber.classList.remove("hidden");
			}
		});
}

