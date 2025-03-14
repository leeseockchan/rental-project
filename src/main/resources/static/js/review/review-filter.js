function searchReviews() {
    const year = document.getElementById("filter-year").value;
    const month = document.getElementById("filter-month").value;
    const url = new URL(window.location.href);

    // 연도 또는 월이 선택되지 않은 경우 경고 표시 후 검색 중단
    if (!year || !month) {
        alert("연도와 월을 모두 선택하세요.");
        return;
    }

    // 검색 조건을 URL 파라미터로 설정
    url.searchParams.set("year", year);
    url.searchParams.set("month", month);
    url.searchParams.set("page", "1"); // 검색 시 첫 페이지로 이동

    window.location.href = url.toString();
}

function resetReviews() {
    const url = new URL(window.location.href);

    // 기존의 검색 파라미터 삭제
    url.searchParams.delete("year");
    url.searchParams.delete("month");
    url.searchParams.set("page", "1"); // 첫 페이지로 이동

    window.location.href = url.toString();
}
