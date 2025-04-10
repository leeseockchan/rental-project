//
//document.getElementById('parkingForm').addEventListener('submit', function(e) {
//    e.preventDefault();
//
//    const parkingData = {
//        name: document.getElementById('parking_name').value,
//        address : document.getElementById('parking_address').value,
//        latitude : document.getElementById('parking_latitude').value,
//        longitude : document.getElementById('parking_longitude').value,
//        type : document.getElementById('parkingType').value
//    };
//
//    fetch("/parking/parking_add", {
//           method: 'POST',
//           headers: {'Content-Type': 'application/json'},
//           body: JSON.stringify(parkingData)
//       }).then(response => {
//           if (response.ok) {
//               alert('주차장이 성공적으로 등록되었습니다.');
//               document.getElementById('parkingForm').reset();
//           } else {
//               alert('주차장 등록에 실패했습니다.');
//           }
//       }).catch(error) {
//           alert('오류가 발생했습니다.');
//       };
//   };
document.addEventListener("DOMContentLoaded", function () {
    console.log("카카오 API 로드 확인:", typeof kakao !== "undefined" && kakao.maps);

    let mapContainer = document.getElementById('map');

    if (!mapContainer) {
        console.error("지도를 표시할 #map 요소를 찾을 수 없습니다.");
        return;
    }

    // 위도, 경도 값 가져오기 (기본값: 서울)
    let latitude = parseFloat(document.getElementById("parkingLatitude").value) || 37.560052;
    let longtitude = parseFloat(document.getElementById("parkingLongtitude").value) || 126.975296;

    let mapOption = {
        center: new kakao.maps.LatLng(latitude, longtitude), // 초기 지도 중심
        level: 3 // 확대 레벨
    };

    let map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 생성
    let marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(latitude, longtitude),
        map: map
    });

    console.log("초기 지도 중심:", latitude, longtitude);

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

