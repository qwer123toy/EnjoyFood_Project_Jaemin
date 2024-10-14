const districts = {
	서울: ['전체', '관악구', '강남구', '강북구', '마포구', '서초구', '송파구', '영등포구', '동작구', '종로구', '중구', '용산구', '성동구', '광진구', '동대문구', '중랑구', '성북구', '강서구', '양천구', '구로구', '금천구'],
	부산: ['전체', '중구', '서구', '동구', '해운대구', '부산진구', '사하구', '연제구', '금정구', '강서구', '동래구', '영도구', '북구', '기장군'],
	인천: ['전체', '남구', '연수구', '미추홀구', '부평구', '계양구', '서구', '중구', '옹진군'],
	대구: ['전체', '중구', '동구', '서구', '남구', '북구', '수성구', '달서구', '달성군'],
	광주: ['전체', '동구', '서구', '남구', '북구', '광산구'],
	대전: ['전체', '동구', '중구', '서구', '유성구', '대덕구'],
	울산: ['전체', '남구', '중구', '동구', '북구', '울주군'],
	세종: ['전체'],
	경기: ['전체', '수원시', '성남시', '고양시', '용인시', '부천시', '안산시', '화성시', '남양주시', '전주시', '광명시', '시흥시', '의정부시', '하남시', '구리시', '안양시', '파주시', '김포시', '양주시', '이천시', '동두천시', '오산시', '여주시', '광주시'],
	강원: ['전체', '춘천시', '원주시', '강릉시', '동해시', '태백시', '속초시', '삼척시', '홍천군', '횡성군', '영월군', '평창군', '정선군', '철원군', '화천군', '양구군', '인제군', '고성군', '양양군'],
	충북: ['전체', '청주시', '충주시', '제천시', '음성군', '진천군', '괴산군', '증평군', '단양군'],
	충남: ['전체', '홍성군', '서산시', '천안시', '아산시', '예산군', '공주시', '부여군', '논산시', '계룡시', '금산군', '당진시'],
	전북: ['전체', '전주시', '익산시', '군산시', '정읍시', '남원시', '김제시', '완주군', '진안군', '무주군', '장수군', '임실군', '순창군', '고창군', '부안군'],
	전남: ['전체', '광양시', '목포시', '여수시', '순천시', '담양군', '장성군', '곡성군', '구례군', '해남군', '완도군', '진도군', '신안군'],
	경북: ['전체', '포항시', '경산시', '안동시', '구미시', '영주시', '영천시', '상주시', '문경시', '예천군', '봉화군', '울진군', '울릉군', '청도군', '고령군', '성주군', '김천시', '경주시'],
	경남: ['전체', '창원시', '진주시', '김해시', '양산시', '거제시', '통영시', '밀양시', '사천시', '함안군', '의령군', '창녕군', '남해군', '하동군', '산청군', '거창군', '합천군'],
	제주: ['전체', '제주시', '서귀포시']
};

let selectedRegion = '';
let selectedDistrict = '';

// 시도 버튼 생성
function createRegionButtons() {
	const regionButtons = document.getElementById('regionButtons');
	for (const region in districts) {
		const button = document.createElement('button');
		button.classList.add("button");
		button.textContent = region;
		button.onclick = () => selectRegion(region, button);
		regionButtons.appendChild(button);
	}
}

// 시구군 버튼 생성
function createDistrictButtons() {
	const districtButtons = document.getElementById('districtButtons');
	districtButtons.innerHTML = ''; // 이전 버튼 제거
	if (selectedRegion) {
		districts[selectedRegion].forEach(district => {
			const button = document.createElement('button');
			button.classList.add("button");
			button.textContent = district;
			button.onclick = () => selectDistrict(district, button);
			districtButtons.appendChild(button);
		});
	}
}

// 시도 선택
function selectRegion(region, button) {
	selectedRegion = region;
	createDistrictButtons(); // 시구군 버튼 생성
	selectedDistrict = ''; // 기존 선택 초기화
	resetRegionButtons(); // 모든 시도 버튼 색 초기화
	button.classList.add('selected'); // 선택된 버튼 색 변경
	resetDistrictButtons(); // 시구군 버튼 초기화

	// 선택된 목록에 같은 시도가 없을 경우 "전체" 자동 선택
	const existingItems = document.querySelectorAll('.selectedItem');
	const hasSameRegion = Array.from(existingItems).some(item => item.textContent.startsWith(selectedRegion + " - "));
	if (!hasSameRegion) {
		selectDistrict("전체", null); // "전체" 선택
	} else {
		highlightButtons(); // 하이라이트 업데이트
	}
}

// 시구군 선택 및 목록에 추가
function selectDistrict(district, button) {
	selectedDistrict = district;
	addToList(); // 목록에 자동으로 추가
	resetDistrictButtons(); // 모든 시구군 버튼 색 초기화
	if (button) {
		button.classList.add('selected'); // 선택된 버튼 색 변경
	}
	highlightButtons(); // 하이라이트 업데이트
}

// 목록에 추가
function addToList() {
	if (selectedRegion && selectedDistrict) {
		const existingItems = document.querySelectorAll('.selectedItem');
		let itemExists = false;

		// "전체" 선택 시 같은 시도의 다른 항목 삭제
		if (selectedDistrict === "전체") {
			existingItems.forEach(item => {
				if (item.textContent.startsWith(selectedRegion + " - ") && item.textContent !== selectedRegion + " - 전체") {
					item.remove(); // 같은 시도의 다른 항목 삭제
				}
			});
		} else {
			// 다른 시구군 선택 시 "전체" 삭제
			existingItems.forEach(item => {
				if (item.textContent === selectedRegion + " - 전체") {
					item.remove(); // "전체" 항목 삭제
				}
			});
		}

		// 항목 존재 여부 확인
		existingItems.forEach(item => {
			if (item.textContent === selectedRegion + " - " + selectedDistrict) {
				item.remove(); // 이미 존재하면 항목 삭제
				itemExists = true;
			}
		});

		// 새로운 항목 추가
		if (!itemExists) {
			const listItem = document.createElement('button');
			listItem.textContent = selectedRegion + " - " + selectedDistrict;
			listItem.className = 'selectedItem';
			listItem.onclick = () => removeFromList(listItem); // 클릭 시 목록에서 제거
			document.getElementById('selectedList').appendChild(listItem);
			highlightButtons(); // 선택된 버튼 하이라이트
		}
	} else {
		alert('시도와 시구군을 모두 선택하세요.');
	}
}

// 목록에서 제거
function removeFromList(button) {
	button.remove(); // 버튼 제거
	highlightButtons(); // 하이라이트 업데이트
}

// 선택된 버튼 하이라이트
function highlightButtons() {
	const regionButtons = document.getElementById('regionButtons').querySelectorAll('button');
	const districtButtons = document.getElementById('districtButtons').querySelectorAll('button');

	// 모든 버튼 초기화
	regionButtons.forEach(button => button.classList.remove('selected'));
	districtButtons.forEach(button => button.classList.remove('selected'));

	// 선택된 목록을 통해 버튼 하이라이트
	const selectedListItems = document.querySelectorAll('.selectedItem');
	selectedListItems.forEach(item => {
		const [region, district] = item.textContent.split(' - ');
		regionButtons.forEach(button => {
			if (button.textContent === region) {
				button.classList.add('selected');
			}
		});
		districtButtons.forEach(button => {
			if (button.textContent === district && selectedRegion === region) {
				button.classList.add('selected'); // 같은 시도의 시구군만 하이라이트
			}
		});
	});
}

// 모든 시도 버튼 색 초기화
function resetRegionButtons() {
	const regionButtons = document.getElementById('regionButtons').querySelectorAll('button');
	regionButtons.forEach(button => {
		button.classList.remove('selected');
	});
}

// 모든 시구군 버튼 색 초기화
function resetDistrictButtons() {
	const districtButtons = document.getElementById('districtButtons').querySelectorAll('button');
	districtButtons.forEach(button => {
		button.classList.remove('selected');
	});
}

// 전체 취소 버튼 기능
document.getElementById('clearAllButton').onclick = function() {
	document.getElementById('selectedList').innerHTML = ''; // 목록 비우기
	document.getElementById('districtButtons').innerHTML = '';
	resetRegionButtons(); // 시도 버튼 초기화
	resetDistrictButtons(); // 시구군 버튼 초기화
	selectedRegion = ''; // 선택 초기화
	selectedDistrict = ''; // 선택 초기화
};

// 검색 버튼 기능
document.getElementById('searchButton').onclick = function() {
	const selectedItems = Array.from(document.querySelectorAll('.selectedItem')).map(item => item.textContent);
	//	console.log(selectedItems); // 선택된 목록 배열로 출력
	let json = JSON.stringify(selectedItems);
	fetch("/searchArea", {
		method: "post",
		body: json
	}).then((resp) => resp.json())
		.then((result) => {
			if (result) {
				let searchResult = document.querySelector("#searchResult");
				let storeList = document.querySelector(".store-list");

				searchResult.innerHTML = "검색결과 : " + result.length + " 건";
				storeList.innerHTML = '';
				result.map((cafeteriaWithPic) => createCard(cafeteriaWithPic)
				).forEach((elem) => {
					storeList.append(elem);
				})
			} else {
				alert("요청 실패");
			}
		});
};

function createCard(cafeteriaWithPic) {
	let template = document.querySelector("#template");
	let clone = document.importNode(template.content, true);
	clone.querySelector(".store-item").onclick = () => {
		location.href = "cafeteria?cafeNum=" + cafeteriaWithPic.cafeteria.cafeNum + "&cafeName=" + cafeteriaWithPic.cafeteria.cafeName;
	};
	if (cafeteriaWithPic.cafePics && cafeteriaWithPic.cafePics[0] && cafeteriaWithPic.cafePics[0].cafePic)
		clone.querySelector("img").src = cafeteriaWithPic.cafePics[0].cafePic;
	clone.querySelector("ul > li:nth-child(1)").innerText = cafeteriaWithPic.cafeteria.cafeName;
	clone.querySelector("ul > li:nth-child(2)").innerText = "평균 금액: " + cafeteriaWithPic.cafeteria.cafePrice + " 원";
	clone.querySelector("ul > li:nth-child(3)").innerText = "전화번호: " + cafeteriaWithPic.cafeteria.cafePhoneNumber;
	clone.querySelector("ul > li:nth-child(4)").innerText = "주소: " + cafeteriaWithPic.cafeteria.cafeAddress;
	return clone;
}

// 페이지 로드 시 시도 버튼 생성
createRegionButtons();