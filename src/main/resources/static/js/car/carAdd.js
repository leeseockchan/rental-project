document.addEventListener("DOMContentLoaded", function () {
    const provinceSelect = document.getElementById("parkingProvince");
    const districtSelect = document.getElementById("parkingDistrict");
    const parkingSelect = document.getElementById("carParking");
    const latInput = document.getElementById("parkingLatitude");
    const lngInput = document.getElementById("parkingLongitude");
    const mapContainer = document.getElementById("map");

    let map, marker;

    // 지도 초기화
    function initMap(lat, lng) {
        const center = new kakao.maps.LatLng(lat, lng);
        map = new kakao.maps.Map(mapContainer, {
            center: center,
            level: 3
        });

        marker = new kakao.maps.Marker({
            map: map,
            position: center
        });
    }

    // 마커 위치 갱신
    function updateMarker(lat, lng) {
        if (!isNaN(lat) && !isNaN(lng)) {
            const position = new kakao.maps.LatLng(lat, lng);
            marker.setPosition(position);
            map.setCenter(position);
        }
    }

    // 도/시 선택 → 행정구역 목록 조회
    $(provinceSelect).change(function () {
        const selectedProvince = $(this).val();

        $(districtSelect).empty().append('<option value="">행정구역 선택</option>');
        $(parkingSelect).empty().append('<option value="">주차장 선택</option>');
        $(latInput).val('');
        $(lngInput).val('');

        if (selectedProvince) {
            $.get("/admin/vehicles/districts", { province: selectedProvince }, function (data) {
                data.forEach(district => {
                    $(districtSelect).append(`<option value="${district}">${district}</option>`);
                });
            });
        }
    });

    // 행정구역 선택 → 주차장 목록 조회
    $(districtSelect).change(function () {
        const province = $(provinceSelect).val();
        const district = $(this).val();

        $(parkingSelect).empty().append('<option value="">주차장 선택</option>');
        $(latInput).val('');
        $(lngInput).val('');

        if (district) {
            $.get("/admin/vehicles/parkings", { province: province, district: district }, function (data) {
                data.forEach(parking => {
                    $(parkingSelect).append(
                        `<option value="${parking.parkingId}" data-lat="${parking.parkingLatitude}" data-lng="${parking.parkingLongtitude}">
                            ${parking.parkingName}
                        </option>`
                    );
                });
            });
        }
    });

    // 주차장 선택 → 위도/경도 입력 + 마커 이동
    $(parkingSelect).change(function () {
        const selected = $(this).find("option:selected");
        const lat = parseFloat(selected.data("lat"));
        const lng = parseFloat(selected.data("lng"));

        $(latInput).val(lat || '');
        $(lngInput).val(lng || '');

        if (!isNaN(lat) && !isNaN(lng)) {
            updateMarker(lat, lng);
        }
    });

    // 위도/경도 수동 입력 시 마커 이동
    $(latInput).on("input", function () {
        const lat = parseFloat($(latInput).val());
        const lng = parseFloat($(lngInput).val());
        updateMarker(lat, lng);
    });

    $(lngInput).on("input", function () {
        const lat = parseFloat($(latInput).val());
        const lng = parseFloat($(lngInput).val());
        updateMarker(lat, lng);
    });

    // 초기 지도 표시 (숭례문 좌표 또는 입력된 값 기준)
    const defaultLat = parseFloat(latInput.value) || 37.560052;
    const defaultLng = parseFloat(lngInput.value) || 126.975296;
    initMap(defaultLat, defaultLng);
  });
