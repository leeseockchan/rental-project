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
    // 초기 위도, 경도 값을 입력 필드에서 가져옴
    let latitude = parseFloat(document.getElementById("parkingLatitude").value) || 37.560052; // 기본값: 서울
    let longitude = parseFloat(document.getElementById("parkingLongitude").value) || 126.975296; // 기본값: 서울

    // 카카오 지도 API 초기화
    let mapContainer = document.getElementById('map');
    let mapOption = {
        center: new kakao.maps.LatLng(latitude, longitude), // 지도 초기 중심 위치
        level: 3 // 지도 확대 레벨
    };

    let map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 생성
    let marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(latitude, longitude), // 마커 위치
        map: map
    });

    // 위도와 경도 입력 값이 변경되면 마커와 지도를 업데이트하는 함수
    function updateMarker() {
        let newLat = parseFloat(document.getElementById("parkingLatitude").value);
        let newLng = parseFloat(document.getElementById("parkingLongitude").value);

        // 입력 값이 유효한지 확인
        if (!isNaN(newLat) && !isNaN(newLng)) {
            let newPosition = new kakao.maps.LatLng(newLat, newLng);
            marker.setPosition(newPosition); // 마커 위치 변경
            map.setCenter(newPosition); // 지도 중심 변경
        }
    }

    // 입력 필드의 변경 사항을 감지하여 마커와 지도를 업데이트
    document.getElementById("parkingLatitude").addEventListener("input", updateMarker);
    document.getElementById("parkingLongitude").addEventListener("input", updateMarker);
});
