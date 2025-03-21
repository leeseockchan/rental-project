document.addEventListener("DOMContentLoaded", function () {
    var carId = document.body.getAttribute("data-car-id");
    var provinceElement = document.getElementById("parkingProvince");
    var districtElement = document.getElementById("parkingDistrict");

    var selectedProvince = provinceElement.value;
    var selectedDistrict = districtElement.value;

    console.log("수정 전 도/시:", selectedProvince);
    console.log("수전 정 행정구역:", selectedDistrict);

    if (selectedProvince) {
        fetch('/admin/vehicles/api/districts/' + encodeURIComponent(selectedProvince))
            .then(response => response.json())
            .then(data => {
                console.log("행정구역 데이터:", data);
                districtElement.innerHTML = "";
                if (Array.isArray(data)) {
                    data.forEach(function (district) {
                        var option = document.createElement("option");
                        option.value = district;
                        option.textContent = district;
                        if (district === selectedDistrict) {
                            option.selected = true;
                        }
                        districtElement.appendChild(option);
                    });
                } else {
                    console.error("잘못된 형식:", data);
                }
            })
            .catch(error => {
                console.error("Error fetching districts:", error);
            });
    }

    document.getElementById("model_modify").addEventListener("submit", function (e) {
        e.preventDefault();
        var modelBrand = document.getElementById("modelBrand").value;
        var modelName = document.getElementById("modelName").value;
        var carYear = document.getElementById("carYear").value;
        var carFuel = document.getElementById("carFuel").value;
        var carGrade = document.getElementById("carGrade").value;
        var province = provinceElement.value;
        var district = districtElement.value;
        var parkingName = document.getElementById("parkingName").value;

        console.log("carId:", carId);

        fetch('/admin/vehicles/modify/' + carId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                model: {
                    modelBrand: modelBrand,
                    modelName: modelName
                },
                carYear: carYear,
                carFuel: carFuel,
                carGrade: carGrade,
                parking: {
                    province: province,
                    district: district,
                    parkingName: parkingName
                }
            })
        })
        .then(response => {
        console.log("서버 응답 코드:", response.status);
            if (!response.ok) {
                return response.json().then(err => { throw err; });
            }
            return response.json();
        })
        .then(data => {
        console.log("서버 응답 데이터:", data);
            if (data.success) {
                 alert("정보가 수정 되었습니다.");
                window.location.reload();
            } else {
                 alert("잘못 입력하였습니다.");
            }
        })
        .catch(error => {
            console.error("Error sending update request:", error);
            alert("오류 발생: " + error.message);
        });
    });
});


document.addEventListener('DOMContentLoaded', function () {
    const modelBrandSelect = document.getElementById('modelBrand');
    const modelNameSelect = document.getElementById('modelName');

    // 제조사 선택 이벤트
    modelBrandSelect.addEventListener('change', function () {
        const selectedBrand = modelBrandSelect.value;

        // 모델 셀렉트 초기화
        modelNameSelect.innerHTML = '<option value="">-- 모델 선택 --</option>'; // 기본 옵션 유지

        if (selectedBrand) {
            // AJAX 요청 (제조사에 맞는 모델 목록 요청)
            fetch(`/admin/vehicles/getCarModels?brand=${selectedBrand}`)
                .then(response => response.json())
                .then(data => {
                    // 응답 받은 모델 목록을 모델 셀렉트 박스에 추가
                    data.models.forEach(function (model) {
                        const option = document.createElement('option');
                        option.value = model;
                        option.textContent = model;
                        modelNameSelect.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error fetching car models:", error);
                });
        }
    });
});
