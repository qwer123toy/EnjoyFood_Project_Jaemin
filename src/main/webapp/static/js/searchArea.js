const districts = {
	서울: ['전체', '관악구', '강남구', '강북구', '마포구', '서초구', '송파구', '영등포구', '동작구'],
	부산: ['전체', '중구', '서구', '동구', '해운대구', '부산진구', '사하구', '연제구', '금정구'],
	인천: ['전체', '남구', '연수구', '미추홀구', '부평구', '계양구'],
	대구: ['전체', '중구', '동구', '서구', '남구', '북구'],
	광주: ['전체', '동구', '서구', '남구', '북구'],
	대전: ['전체', '동구', '중구', '서구', '유성구'],
	울산: ['전체', '남구', '중구', '동구', '북구', '울주군'],
	세종: ['전체'],
	경기: ['전체', '수원시', '성남시', '고양시', '용인시', '부천시', '안산시', '화성시', '남양주시', '전주시'],
	강원: ['전체', '춘천시', '원주시', '강릉시', '동해시', '태백시', '속초시', '삼척시', '홍천군', '횡성군', '영월군', '평창군', '정선군', '철원군', '화천군', '양구군', '인제군', '고성군', '양양군'],
	충북: ['전체', '청주시', '충주시', '제천시', '음성군', '진천군', '괴산군', '증평군', '단양군'],
	충남: ['전체', '홍성군', '서산시', '천안시', '아산시', '예산군', '공주시', '부여군', '논산시', '계룡시', '금산군', '당진시'],
	전북: ['전체', '전주시', '익산시', '군산시', '정읍시', '남원시', '김제시', '완주군', '진안군', '무주군', '장수군', '임실군', '순창군', '고창군', '부안군'],
	전남: ['전체', '광양시', '목포시', '여수시', '순천시', '담양군', '장성군', '곡성군', '구례군', '해남군', '완도군', '진도군', '신안군'],
	경북: ['전체', '포항시', '경산시', '안동시', '구미시', '영주', '영천시', '상주', '문경', '예천군', '봉화군', '울진군', '울릉군', '청도군', '고령군', '성주군', '김천시'],
	경남: ['전체', '창원시', '진주시', '김해시', '양산시', '거제시', '통영시', '밀양시', '사천시', '함안군', '의령군', '창녕군', '남해군', '하동군', '산청군', '거창군', '합천군'],
	제주: ['전체', '제주시', '서귀포시']
};

function showDistricts() {
	const selectedRegion = document.querySelector('input[name="region"]:checked').value;
	const districtsDiv = document.getElementById('districts');

	// districtsDiv 초기화
	districtsDiv.innerHTML = '';

	districts[selectedRegion].forEach(district => {
		const label = document.createElement('label');
		label.className = 'radio-label';
		label.textContent = district;

		const radio = document.createElement('input');
		radio.type = 'radio';
		radio.name = 'district';
		radio.value = district;
		radio.id = district;

		radio.onclick = function() {
			updateSelectedDistricts(district);
			// 선택된 라디오 버튼 강조
			highlightSelectedLabels();
		};

		label.prepend(radio);
		districtsDiv.appendChild(label);
	});
	districtsDiv.classList.remove('hidden');

	document.getElementById("전체").click();

	// 첫 번째 지역 선택 시 해당 지역의 라디오 버튼 강조
	highlightSelectedLabels();
}

function highlightSelectedLabels() {
	// 모든 라벨에서 selected 클래스 제거
	document.querySelectorAll('#cities .radio-label').forEach(label => {
		label.classList.remove('selected');
	});
	document.querySelectorAll('#districts .radio-label').forEach(label => {
		label.classList.remove('selected');
	});

	// 선택된 도시 라벨 강조
	const selectedRegionLabel = document.querySelector('input[name="region"]:checked').closest('.radio-label');
	if (selectedRegionLabel) {
		selectedRegionLabel.classList.add('selected');
	}

	// 선택된 구 라벨 강조
	const selectedDistrictLabel = document.querySelector('input[name="district"]:checked');
	if (selectedDistrictLabel) {
		selectedDistrictLabel.closest('.radio-label').classList.add('selected');
	}
}

function updateSelectedDistricts(district) {
	const selectedRegion = document.querySelector('input[name="region"]:checked').value;
	const selected = document.getElementById('selected');

	if (district == "전체")
		selected.value = selectedRegion;
	else
		selected.value = selectedRegion + " " + district;
}