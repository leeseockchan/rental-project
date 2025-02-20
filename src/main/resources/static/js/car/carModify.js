 // 도/시 선택 시 행정구역 로드
    function fetchRegions() {
        const city = document.getElementById("parkingProvince").value;
        const regionSelect = document.getElementById("parkingDistrict");
        regionSelect.innerHTML = "";

        fetch(`/api/admin/vehicles/regions?city=${encodeURIComponent(city)}`)
            .then(response => response.json())
            .then(regions => {
                regions.forEach(region => {
                    const option = document.createElement("option");
                    option.value = region;
                    option.textContent = region;
                    regionSelect.appendChild(option);
                });
            })
            .catch(error => console.error("행정구역 로드 실패:", error));
    }