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
                window.location.href = `/admin/parkings/${parkingId}`;
            } else {
                alert('수정에 실패했습니다.');
            }
        })
        .catch(error => console.error('에러 발생:', error));
    });


