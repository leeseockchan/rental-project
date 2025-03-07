document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".btn-update").addEventListener("click", function () {
        const year = document.getElementById("filter-year").value;
        const month = document.getElementById("filter-month").value;
        const url = `/admin/review?year=${year}&month=${month}&page=1`;
        window.location.href = url;
    });
});

function searchReviews() {
    const year = document.getElementById("filter-year").value;
    const month = document.getElementById("filter-month").value;
    window.location.href = `/admin/review?page=1&size=10&year=${year}&month=${month}`;
}