document.addEventListener("DOMContentLoaded", function () {
        const preview = document.getElementById('preview');
        const imageInput = document.getElementById('imageFile');

        imageInput.addEventListener('change', function(e) {
        const file = e.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                preview.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            preview.src = "/images/car/defaultModel.png";
        }
    });


    const form = document.getElementById('model_create');
    // 폼 제출 시 데이터 처리
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const model = new FormData(form);
        const imageInput = document.getElementById('imageFile');
        // 사용자가 새로운 이미지를 등록시 'imageFile'을 FormData에 추가
        if (imageInput.files.length > 0) {
            model.append("imageFile", imageInput.files[0]);
        }

    // FormData 값 출력
        for (const [key, value] of model.entries()) {
                 console.log(key, value instanceof File ? value.name : value);
        }

        // 서버로 폼 데이터 전송
        fetch('/admin/models/create', {
            method: 'POST',
            body: model
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('차량 정보가 추가되었습니다.');
                // 새로 추가된 모델의 이미지 URL을 사용하여 미리보기
                const newImageUrl = data.model.imageUrl;  // 응답에서 새 이미지 URL 가져오기
                document.getElementById('preview').src = newImageUrl; // 미리보기 이미지 갱신
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

