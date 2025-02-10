function validateForm() {
    // 차 제조사 검사
    const carManufacture = document.querySelector('input[name="carManufacture"]:checked');
    if (!carManufacture) {
        alert("차 제조사를 선택해주세요.");
        return false;
    }

    // 차 연료 검사
    const carFuel = document.querySelector('input[name="carFuel"]:checked');
    if (!carFuel) {
        alert("차 연료를 선택해주세요.");
        return false;
    }

    // 차 종류 검사 (공백 제외 문자 검사)
    const carCategory = document.getElementById('carCategory').value;
    const carCategoryRegex = /^(?!\s*$).+/;  // 공백만 입력되지 않도록 하는 정규표현식
    if (!carCategoryRegex.test(carCategory)) {
        alert("차 종류를 입력해주세요.");
        return false;
    }

    // 차 이름 검사 (공백 제외 문자 검사)
    const carName = document.getElementById('carName').value;
    const carNameRegex = /^(?!\s*$).+/;  // 공백만 입력되지 않도록 하는 정규표현식
    if (!carNameRegex.test(carName)) {
        alert("차 이름을 입력해주세요.");
        return false;
    }

    // 차 연식 검사 (4자리 숫자만 입력 가능)
    const carYear = document.getElementById('carYear').value;
    const carYearRegex = /^\d{4}$/;  // 4자리 숫자만
    if (!carYearRegex.test(carYear)) {
        alert("올바른 연식을 입력해주세요. (4자리 숫자)");
        return false;
    }

    // 좌석 수 검사 (숫자만, 1 이상의 값이어야 함)
    const carSeateNum = document.getElementById('carSeateNum').value;
    const carSeateNumRegex = /^[1-9]\d*$/;  // 1 이상의 숫자
    if (!carSeateNumRegex.test(carSeateNum)) {
        alert("올바른 좌석 수를 입력해주세요. (1 이상의 숫자)");
        return false;
    }

    // 변속기 검사 (공백 제외 문자 검사)
    const carTransmission = document.getElementById('carTransmission').value;
    const carTransmissionRegex = /^(?!\s*$).+/;  // 공백만 입력되지 않도록 하는 정규표현식
    if (!carTransmissionRegex.test(carTransmission)) {
        alert("변속기를 입력해주세요.");
        return false;
    }

    return true; // 모든 검사가 통과되면 폼 제출
}
