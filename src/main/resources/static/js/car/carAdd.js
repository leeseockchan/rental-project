document.getElementById("parkingProvince").addEventListener("change", function() {
    var province = this.value;

    fetch(`/api/admin/vehicles/getDistricts?province=${province}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        var districtSelect = document.getElementById("parkingDistrict");
        districtSelect.innerHTML = ""; // 기존 옵션 제거

        // 기본 옵션 추가
        var defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.textContent = "행정구역 선택";
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
