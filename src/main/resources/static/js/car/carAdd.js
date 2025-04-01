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

document.addEventListener("DOMContentLoaded", function() {
    var mapContainer = document.getElementById('map');

    if (mapContainer) {
        var latitude = mapContainer.getAttribute("data-latitude");
        var longitude = mapContainer.getAttribute("data-longitude");
        var parkingName = mapContainer.getAttribute("data-parking-name");

        var mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3
        };

        var map = new kakao.maps.Map(mapContainer, mapOption);
        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(latitude, longitude),
            map: map
        });

        var infowindow = new kakao.maps.InfoWindow({
            content: `<div style="padding:5px; white-space: nowrap; font-size:14px;">${parkingName}</div>`
        });
        infowindow.open(map, marker);
    }
});
