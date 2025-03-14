function searchUser() {
    var userId = document.getElementById("filter-user").value;
    var currentPage = new URLSearchParams(window.location.search).get("page") || 1;
    var size = new URLSearchParams(window.location.search).get("size") || 10;

    window.location.href = `/admin/users?userId=${encodeURIComponent(userId)}&page=${currentPage}&size=${size}`;
}

function resetCon() {
    window.location.href = "/admin/users?page=1&size=10";
}