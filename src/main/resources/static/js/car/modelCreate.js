document.addEventListener("DOMContentLoaded", function () {
  console.log('DOMContentLoaded 실행됨');
        const imageInput = document.querySelector('#imageFile');
        const preview = document.getElementById('preview');

        // 미리보기 사진 변경
        imageInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        console.log("File input changed!");

        if (file) {
        // 이미지 파일만 가능
            if (!file.type || !file.type.startsWith("image/")) {
                alert("이미지 파일만 선택이 가능합니다.");
                imageInput.value = ""; // 입력 초기화
                preview.src = "/images/car/defaultModel.png"; // 기본 이미지 복구
                return;
            }

            const reader = new FileReader();
            reader.onload = function(p) {
                preview.src = p.target.result; // 사용자가 선택한 이미지로 미리보기 변경
            };
            reader.readAsDataURL(file);
        } else {
               preview.src = "/images/car/defaultModel.png"; // 파일 선택 취소 시 기본 이미지로 복원
        }
  });

    const form = document.getElementById('model_create');
    // 폼 제출 시 데이터 처리
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const model = new FormData(form);
        // 사용자가 새로운 이미지를 등록시 'imageFile'을 FormData에 추가
        if (imageInput.files.length > 0) {
            model.append("imageFile", imageInput.files[0]);
        }

        // 서버로 폼 데이터 전송
        fetch('/admin/models/create', {
            method: 'POST',
            body: model
        })
        .then(response => {
            console.log("서버 응답 상태:", response.status); // 상태 코드 확인
            // 서버에서 오류가 발생한 경우
            if (!response.ok) {
                throw new Error('서버에서 오류가 발생했습니다.');
            }
            return response.json();
        })
        .then(data => {
            console.log("서버 응답 데이터:", data);
            if (data.success) {
                alert('차량 정보가 추가되었습니다.');
                const newImageUrl = data.model.imageUrl;  // 새 이미지 URL 가져오기
                document.getElementById('preview').src = newImageUrl;
                window.location.href = '/admin/models';
            } else {
                alert('차량 정보 추가에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('차량 정보 추가 중 오류가 발생했습니다.');
        });

    });
});

