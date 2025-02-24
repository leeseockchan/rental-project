document.getElementById("parkingProvince").addEventListener("change", function() {
    var province = this.value;  // parkingProvince의 선택된 값
    console.log(province); // 선택된 값 확인

    if (province) {
      fetch('/api/districts/' + province)
          .then(response => response.json())
          .then(data => {
              if (data && data.districts) {
                  var districtSelect = document.getElementById("parkingDistrict");
                  districtSelect.innerHTML = "";
                  data.districts.forEach(function(district) {
                      var option = document.createElement("option");
                      option.value = district;
                      option.textContent = district;
                      districtSelect.appendChild(option);
                  });
              } else {
                  console.error("잘못되었습니다.!");
              }
          })
          .catch(error => {
              console.error("Error fetching districts:", error);
          });
    }
});
