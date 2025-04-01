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

//document.addEventListener("DOMContentLoaded", function () {
//    // 초기 위도, 경도 값 가져오기
//    let latitude = parseFloat(document.getElementById("parkingLatitude").value) || 37.5665; // 기본값: 서울
//    let longitude = parseFloat(document.getElementById("parkingLongitude").value) || 126.9780; // 기본값: 서울
//
//    // 카카오 지도 API 초기화
//    let mapContainer = document.getElementById('map');
//    let mapOption = {
//        center: new kakao.maps.LatLng(latitude, longitude),
//        level: 3 // 지도 확대 레벨
//    };
//
//    let map = new kakao.maps.Map(mapContainer, mapOption);
//
//    // 마커 생성
//    let marker = new kakao.maps.Marker({
//        position: new kakao.maps.LatLng(latitude, longitude),
//        map: map
//    });
//
//    // 위도, 경도 입력 시 마커 업데이트
//    function updateMarker() {
//        let newLat = parseFloat(document.getElementById("parkingLatitude").value);
//        let newLng = parseFloat(document.getElementById("parkingLongitude").value);
//
//        if (!isNaN(newLat) && !isNaN(newLng)) {
//            let newPosition = new kakao.maps.LatLng(newLat, newLng);
//            marker.setPosition(newPosition); // 마커 위치 변경
//            map.setCenter(newPosition); // 지도 중심 변경
//        }
//    }
//
//    // 입력 필드 변경 감지
//    document.getElementById("parkingLatitude").addEventListener("input", updateMarker);
//    document.getElementById("parkingLongitude").addEventListener("input", updateMarker);
//});
document.addEventListener("DOMContentLoaded", function() {
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div
       mapOption = {
           center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
           level: 3 // 지도의 확대 레벨
       };

   var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

   // 지도를 클릭한 위치에 표출할 마커입니다
   var marker = new kakao.maps.Marker({
       // 지도 중심좌표에 마커를 생성합니다
       position: map.getCenter()
   });
   // 지도에 마커를 표시합니다
   marker.setMap(map);

   // 지도에 클릭 이벤트를 등록합니다
   // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
   kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

       // 클릭한 위도, 경도 정보를 가져옵니다
       var latlng = mouseEvent.latLng;

       // 마커 위치를 클릭한 위치로 옮깁니다
       marker.setPosition(latlng);

       var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
       message += '경도는 ' + latlng.getLng() + ' 입니다';

       var resultDiv = document.getElementById('clickLatlng');
       resultDiv.innerHTML = message;

   });
});
