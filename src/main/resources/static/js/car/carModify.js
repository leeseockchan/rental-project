// JavaScript (carModify.js)
document.getElementById("parkingProvince").addEventListener("change", function() {
    var province = this.value;

    // AJAX 요청으로 districtList를 받아옵니다
    fetch(`/api/admin/vehicles/getDistricts?province=${province}`, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
        var districtSelect = document.getElementById("parkingDistrict");
        districtSelect.innerHTML = ""; // 기존 옵션 제거
        data.forEach(function(district) {
            var option = document.createElement("option");
            option.value = district;
            option.textContent = district;
            districtSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching districts:', error));
});
