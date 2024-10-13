addEventListener("load", onload);

let userID;
let userPhoneNumber;
let userOwnerNumber;
let ownerPageBtn;
let addMenuBtn;


const url = "/api/v1/user";

function onload() {
	userID = document.querySelector("#userID");
	userNickname = document.querySelector("#userNickname");
	userPhoneNumber = document.querySelector("#userPhoneNumber");
	userOwnerNumber = document.querySelector("#userOwnerNumber");
	ownerPageBtn = document.querySelector("#ownerPageBtn");
	addMenuBtn = document.querySelector("#addMenuBtn");


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
				ownerPageBtn.classList.remove("hidden");
				addMenuBtn.classList.remove("hidden");
				// ownerPageBtn의 onclick 속성에 동적으로 userID를 추가
				ownerPageBtn.onclick = function() {
					window.location.href = `/ownerPage?userId=${user.userID}`;
				};

				addMenuBtn.onclick = function() {
					window.location.href = `/addMenu?userId=${user.userID}`;
				};
			}
		});
}

