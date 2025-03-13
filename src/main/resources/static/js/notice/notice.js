document.addEventListener("DOMContentLoaded", function () {
  const searchButton = document.querySelector(".btn-search");
  const resetButton = document.querySelector(".btn-reset");

  searchButton.addEventListener("click", searchCon);
  resetButton.addEventListener("click", resetCon);
});

function searchCon() {
  const keyword = document.getElementById("filter-content").value.trim();
  const url = new URL(window.location.href);

  if (keyword) {
    url.searchParams.set("title", keyword);
    url.searchParams.set("page", "1"); // 검색 시 첫 페이지로 이동
  } else {
    url.searchParams.delete("title");
  }

  window.location.href = url.toString();
}

function resetCon() {
  const url = new URL(window.location.href);
  url.searchParams.delete("title");
  url.searchParams.set("page", "1"); // 초기화 후 첫 페이지로 이동

  window.location.href = url.toString();
}
