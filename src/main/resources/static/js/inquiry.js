// 커서 깜박임 방지
//document.addEventListener("DOMContentLoaded", function () {
//    document.body.addEventListener("mousedown", function (event) {
//        const target = event.target;
//
//        if (target.tagName !== "INPUT" && target.tagName !== "TEXTAREA") {
//            document.activeElement.blur();
//            event.preventDefault();
//        }
//    });
//});

// 유효성 검사
function validateAdminNum() {
    const adminNum = document.getElementById("adminNum").value.trim();
    const errorAdminNum = document.getElementById("error-adminNum");

    if (!adminNum) {
        errorAdminNum.textContent = "관리자 번호는 필수 입력값입니다.";
        return false;
    } else if (!/^\d+$/.test(adminNum) || adminNum[0] === '0' || parseInt(adminNum, 10) < 1) {
        errorAdminNum.textContent = "관리자 번호는 1 이상의 숫자여야 하며, 0으로 시작할 수 없습니다.";
        return false;
    } else {
        errorAdminNum.textContent = "";
        return true;
    }
}

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
