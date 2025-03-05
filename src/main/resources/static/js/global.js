document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".table-box tbody tr").forEach(row => {
        row.addEventListener("click", function () {
            window.location.href = this.dataset.href;
        });
    });
});