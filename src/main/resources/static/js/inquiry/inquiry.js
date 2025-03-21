// 유효성 검사
function validateInquiriesA() {
    const inquiriesA = document.getElementById("inquiriesA").value.trim();
    const errorInquiriesA = document.getElementById("error-inquiriesA");

    if (!inquiriesA) {
        errorInquiriesA.textContent = "답변 내용을 입력해야 합니다.";
        return false;
    } else {
        errorInquiriesA.textContent = "";
        return true;
    }
}

// 폼 제출 방지 및 리디렉트
document.getElementById("reply-form").onsubmit = function(event) {
    if (!validateAdminNum() || !validateInquiriesA()) {
        event.preventDefault();  // 폼 제출 방지
        alert("폼을 제출하지 못했습니다.");
        window.location.reload();  // 현재 페이지 새로고침 (리디렉트)
        return false;
    }
};

// 검색
function searchCon() {
    var filterContent = document.getElementById("filter-content").value.trim();
    var currentUrl = window.location.pathname;
    var params = new URLSearchParams(window.location.search);

    if (filterContent === "") {
        alert("검색어를 입력하세요.");
        return;
    }

    // 'content' 파라미터 추가
    params.set("content", filterContent);

    // 검색 후 해당 페이지로 이동
    window.location.href = currentUrl + "?" + params.toString();
}

// 검색취소
function resetCon() {
    document.getElementById("filter-content").value = ""; // 검색 입력 필드 초기화
    window.location.href = "/admin/inquiry"; // 검색어 없이 목록 다시 불러오기
}


