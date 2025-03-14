document.addEventListener("DOMContentLoaded", function () {
    const searchButton = document.querySelector(".btn-search");
    const resetButton = document.querySelector(".btn-reset");

    searchButton.addEventListener("click", searchCon);
    resetButton.addEventListener("click", resetCon);

    // 페이지 로드 후 검색 결과 확인
    checkSearchResults();
});

function searchCon() {
    const keyword = document.getElementById("filter-content").value.trim();
    const url = new URL(window.location.href);

    if (!keyword) {
        alert("검색할 키워드를 입력하세요.");
        return;
    }

    url.searchParams.set("title", keyword);
    url.searchParams.set("page", "1"); // 검색 시 첫 페이지로 이동
    window.location.href = url.toString();
}

function checkSearchResults() {
    const totalResults = document.getElementById("filter-content").getAttribute("data-total-results");
    const keyword = document.getElementById("filter-content").value.trim();

    if (totalResults === "0" && keyword !== "") {
        alert("검색 결과가 없습니다.");
    }
}

function resetCon() {
  const url = new URL(window.location.href);
  url.searchParams.delete("title");
  url.searchParams.set("page", "1"); // 초기화 후 첫 페이지로 이동

  window.location.href = url.toString();
}
