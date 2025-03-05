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