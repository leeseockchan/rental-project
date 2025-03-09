document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".btn-update").addEventListener("click", searchReviews);
});

function searchReviews() {
    const year = document.getElementById("filter-year").value;
    const month = document.getElementById("filter-month").value;

    let url = `/admin/review?page=1&size=10`;

    if (year) url += `&year=${year}`;
    if (month) url += `&month=${month}`;

    window.location.href = url;
}
