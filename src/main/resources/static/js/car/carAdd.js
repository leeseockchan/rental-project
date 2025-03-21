document.getElementById("parkingProvince").addEventListener("change", function() {
    var province = this.value;

    fetch(`/admin/vehicles/getDistricts?province=${province}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        var districtSelect = document.getElementById("parkingDistrict");
        districtSelect.innerHTML = ""; // 기존 옵션 제거

        // 기본 옵션 추가
        var defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.textContent = "-- 행정구역 선택 --";
        districtSelect.appendChild(defaultOption);

        // 새로운 행정구역 옵션 추가
        data.forEach(function(district) {
            var option = document.createElement("option");
            option.value = district;
            option.textContent = district;
            districtSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching districts:', error));
});


document.addEventListener('DOMContentLoaded', function () {
    const carBrandSelect = document.getElementById('carBrand');
    const carModelNameSelect = document.getElementById('carModelName');

    // 제조사 선택 이벤트
    carBrandSelect.addEventListener('change', function () {
        const selectedBrand = carBrandSelect.value;

        // 모델 셀렉트 초기화
        carModelNameSelect.innerHTML = '<option value="">-- 모델 선택 --</option>';

        if (selectedBrand) {
            // AJAX 요청
            fetch(`/admin/vehicles/getCarModels?brand=${selectedBrand}`)
                .then(response => response.json())
                .then(data => {
                    data.models.forEach(function (model) {
                        const option = document.createElement('option');
                        option.value = model;
                        option.textContent = model;
                        carModelNameSelect.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error fetching car models:", error);
                });
        }
    });
});

