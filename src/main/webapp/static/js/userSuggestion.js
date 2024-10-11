addEventListener("load", onload);

let submitBtn;

function onload() {
	document.querySelector("#submitBtn").addEventListener("click", (e) => {
		e.preventDefault();

		let form = document.querySelector("#suggestionForm");
		let formData = new FormData(form);
		let json = JSON.stringify(Object.fromEntries(formData));

		fetch("/userSuggestion", { method: "post", body: json })
			.then((resp) => {
				if (resp.redirected) {
					console.log(resp.url);
					location.href = resp.url;
				}
			});
	})
}