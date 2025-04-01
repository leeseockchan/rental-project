document.addEventListener('DOMContentLoaded', function () {
    const imageInput = document.querySelector('input[name="imageFile"]');
    const preview = document.getElementById('preview');
    const form = document.getElementById('model_modify');
    const modelId = document.querySelector('input[name="modelId"]');

    if (!imageInput || !preview || !modelId) {
        console.error("이미지 입력 필드, 미리보기 사진 또는 모델 ID를 찾을 수 없습니다.");
        return;
    }

    // 기본 이미지 경로 설정
    const existingImageUrl = preview.dataset.imageUrl || "";
    const defaultImage = "/images/car/defaultModel.png";  // 기본 이미지 경로
    preview.src = existingImageUrl.trim() !== "" ? existingImageUrl : defaultImage;

    // 사용자가 이미지를 변경하면 미리보기 업데이트
    imageInput.addEventListener('change', function (event) {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result;  // 사용자가 선택한 이미지로 미리보기 변경
            };
            reader.readAsDataURL(file);
        } else {
            preview.src = defaultImage;  // 파일 선택 취소 시 기본 이미지로 복원
        }
    });


    // 수정 완료 버튼 클릭 시 데이터 처리
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const model = new FormData(form);

        // 사용자가 새로운 이미지를 선택 유무 선택시 1로 바뀜 imageFile FormData에 추가
        if (imageInput.files.length > 0) {
            model.append("imageFile", imageInput.files[0]);
        } else {
            // 이미지가 선택되지 않았을 경우 기존 이미지 URL을 전달
            model.append("imageUrl", preview.src);  // 기존 이미지 URL을 FormData에 추가
        }

        // FormData에 포함된 값들 확인하기 위한 for문
        for (const [key, value] of model.entries()) {
            console.log(key, value);
        }

        // 서버로 폼 데이터 전송
        fetch('/admin/models/'+modelId.value+'/modify', {
            method: 'POST',
            body: model
        })
       .then(response => {
           return response.json(); // JSON 형식으로 응답을 처리
       })
       .then(data => {
           if (data.success) {
               alert('수정 완료되었습니다.');
               window.location.href = '/admin/models';
           } else {
               alert('수정에 실패했습니다.');
           }
       })
        .catch(error => {
            console.error('Error:', error);
            alert('수정 중 오류가 발생했습니다.');
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        // 삭제하기 버튼 클릭 이벤트
        const deleteBtn = document.getElementById('delete_btn');
        if (deleteBtn) {
            deleteBtn.addEventListener('click', function() {
                if (confirm('정말 삭제하시겠습니까?')) {
                    // 삭제 확인 후, 숨겨진 form을 제출
                    document.getElementById('delete_form').submit();
                }
            });
        }
    });

});
