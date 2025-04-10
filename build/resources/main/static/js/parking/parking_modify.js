document.getElementById('parkingModify').addEventListener('submit', function(e) {
    e.preventDefault();

    const parking = {
        name: document.getElementById('parkingName').value,
        address: document.getElementById('parkingAddress').value,
        latitude: document.getElementById('parkingLatitude').value,
        longtitude: document.getElementById('parkingLongtitude').value,
        province: document.getElementById('parkingProvince').value,
        district: document.getElementById('parkingDistrict').value,
    }
     // 서버로 PUT 요청 보내기
        fetch(`/admin/parkings/${parkingId}/modify`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(parking) // JSON 형식으로 변환
        })
        .then(response => {
            if (response.ok) {
                alert('수정이 완료되었습니다!');
                updateMap();
                window.location.href = `/admin/parkings/${parkingId}`;
            } else {
                alert('수정에 실패했습니다.');
            }
        })
        .catch(error => console.error('에러 발생:', error));
});

document.addEventListener("DOMContentLoaded", function() {
        console.log("카카오 API 로드 확인:", typeof kakao !== "undefined" && kakao.maps);
        let mapContainer = document.getElementById('map');

            if (!mapContainer) {
                console.error("지도를 표시할 #map 요소를 찾을 수 없습니다.");
                return;
            }

        let latitude = parseFloat(document.getElementById('parkingLatitude').value);
        let longitude = parseFloat(document.getElementById('parkingLongtitude').value);

        let mapOption = {
                center: new kakao.maps.LatLng(latitude, longitude),
                level: 3 // 확대 레벨
        };

        let map = new kakao.maps.Map(mapContainer, mapOption);
        // 마커 생성
            let marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(latitude, longitude),
                map: map
            });

        console.log("기존 지도 중심:", latitude, longitude);
        // 위도/경도 입력 필드 변경 시 마커 이동
            function updateMarker() {
                let newLat = parseFloat(document.getElementById("parkingLatitude").value);
                let newLng = parseFloat(document.getElementById("parkingLongtitude").value);

                if (!isNaN(newLat) && !isNaN(newLng)) {
                    let newPosition = new kakao.maps.LatLng(newLat, newLng);
                    marker.setPosition(newPosition);
                    map.setCenter(newPosition);
                    console.log("마커 위치 변경:", newLat, newLng);
                }
            }

            // input 이벤트 리스너 추가 (즉시 반영)
            document.getElementById("parkingLatitude").addEventListener("input", updateMarker);
            document.getElementById("parkingLongtitude").addEventListener("input", updateMarker);

});


