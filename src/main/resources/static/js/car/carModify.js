document.addEventListener("DOMContentLoaded", function () {
    const carId = document.body.getAttribute("data-car-id");

    const provinceSelect = document.getElementById("parkingProvince");
    const districtSelect = document.getElementById("parkingDistrict");
    const parkingSelect = document.getElementById("carParking");
    const latInput = document.getElementById("parkingLatitude");
    const lngInput = document.getElementById("parkingLongtitude");
    const mapContainer = document.getElementById("map");

    let map, marker;
    
console.log("parkingId:", document.getElementById("carParking").value);


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

    // 차량 정보 조회
    console.log(">> fetching vehicle data for carId:", carId);
    fetch(`/admin/vehicles/${carId}/modify`)
        .then(response => response.json())
        .then(data => {
            console.log("전체 응답:", data);
            const vehicle = data;
            document.getElementById("modelBrand").value = vehicle.model.modelBrand;
            document.getElementById("modelName").value = vehicle.model.modelName;
            document.getElementById("carYear").value = vehicle.carYear;
            document.getElementById("carFuel").value = vehicle.carFuel;
            document.getElementById("carGrade").value = vehicle.carGrade;

            // 주차장 정보

            const p = vehicle.parking;
             if (!p) {
                console.error("parking 정보가 없습니다:", vehicle);
                return;
              }
            provinceSelect.value = p.parking.province;
//            provinceSelect.innerHTML = `<option value="${p.parkingProvince}" selected>${p.parkingProvince}</option>`;
            districtSelect.innerHTML = `<option value="${p.parkingDistrict}" selected>${p.parkingDistrict}</option>`;
            parkingSelect.innerHTML = `<option value="${p.parkingId}" selected>${p.parkingName}</option>`;

            console.log("parking from server:", p);
            latInput.value = p.parkingLatitude;
            lngInput.value = p.parkingLongtitude;

            initMap(p.parkingLatitude, p.parkingLongtitude);

    })
    .catch(err => {
        console.error(">> fetch or JSON error:", err);
      });

    // 도/시 선택 → 행정구역 목록 로드
    provinceSelect.addEventListener("change", function () {
        const selectedProvince = this.value;
        districtSelect.innerHTML = '<option value="">행정구역 선택</option>';
        parkingSelect.innerHTML = '<option value="">주차장 선택</option>';
        latInput.value = '';
        lngInput.value = '';

        if (selectedProvince) {
            fetch(`/admin/vehicles/districts?province=${encodeURIComponent(selectedProvince)}`)
                .then(response => response.json())
                .then(data => {
                    data.forEach(district => {
                        const option = document.createElement("option");
                        option.value = district;
                        option.textContent = district;
                        districtSelect.appendChild(option);
                    });
                });
        }
    });

    // 행정구역 선택 → 주차장 목록 로드
    districtSelect.addEventListener("change", function () {
        const selectedProvince = provinceSelect.value;
        const selectedDistrict = this.value;
        parkingSelect.innerHTML = '<option value="">주차장 선택</option>';
        latInput.value = '';
        lngInput.value = '';

        if (selectedDistrict) {
            fetch(`/admin/vehicles/parkings?province=${encodeURIComponent(selectedProvince)}&district=${encodeURIComponent(selectedDistrict)}`)
                .then(response => response.json())
                .then(data => {
                    data.forEach(parking => {
                        const option = document.createElement("option");
                        option.value = parking.parkingId;
                        option.textContent = parking.parkingName;
                        option.setAttribute("data-lat", parking.parkingLatitude);
                        option.setAttribute("data-lng", parking.parkingLongtitude);
                        parkingSelect.appendChild(option);
                    });
                });
        }
    });

    // 주차장 선택 → 위도/경도 입력 + 지도 이동
    parkingSelect.addEventListener("change", function () {
        const opt = this.options[this.selectedIndex];
        const lat = parseFloat(opt.getAttribute("data-lat"));
        const lng = parseFloat(opt.getAttribute("data-lng"));

         latInput.value = !isNaN(lat) ? lat : '';
         lngInput.value = !isNaN(lng) ? lng : '';

        if (!isNaN(lat) && !isNaN(lng)) {
            updateMarker(lat, lng);
        }
    });

    // 차량 수정 요청
    const form = document.getElementById("model_modify");
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const modelBrand = document.getElementById("modelBrand").value;
        const modelName = document.getElementById("modelName").value;
        const carYear = document.getElementById("carYear").value;
        const carFuel = document.getElementById("carFuel").value;
        const carGrade = document.getElementById("carGrade").value;
        const province = provinceSelect.value;
        const district = districtSelect.value;

        const selectedOption = parkingSelect.options[parkingSelect.selectedIndex];
        const parkingId = selectedOption?.value || "";
        const parkingName = selectedOption?.textContent || "";

        fetch('/admin/vehicles/modify/' + carId, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                model: { modelBrand, modelName },
                carYear,
                carFuel,
                carGrade,
                parking: { province, district, parkingId, parkingName }
            })
        })
        .then(response => {
            if (!response.ok) return response.json().then(err => { throw err; });
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert("정보가 수정되었습니다.");
                window.location.reload();
            } else {
                alert("잘못 입력하였습니다.");
            }
        })
        .catch(error => {
            console.error("수정 요청 오류:", error);
            alert("오류 발생: " + (error.message || "서버 오류"));
        });
    });


});
