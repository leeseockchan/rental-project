function searchUser() {
    var userId = document.getElementById("filter-user").value.trim(); // 공백 제거
    var totalResults = document.getElementById("filter-user").getAttribute("data-total-results");
    var currentPage = new URLSearchParams(window.location.search).get("page") || 1;
    var size = new URLSearchParams(window.location.search).get("size") || 10;

    // 공백 입력 시 검색 차단
    if (!userId) {
        alert("검색할 아이디를 입력하세요.");
        return;
    }

    // 검색 수행
    window.location.href = `/admin/users?userId=${encodeURIComponent(userId)}&page=${currentPage}&size=${size}`;

    // 검색 결과가 없을 경우 alert 띄우기 (페이지 로드 후 실행)
    setTimeout(function () {
        if (totalResults === "0") {
            alert("검색 결과가 없습니다.");
        }
    }, 500);
}

function resetCon() {
    window.location.href = "/admin/users?page=1&size=10";
}