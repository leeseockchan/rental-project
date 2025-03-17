document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".table-box tbody tr").forEach(row => {
        row.addEventListener("click", function () {
            window.location.href = this.dataset.href;
        });
    });
});

    document.addEventListener("DOMContentLoaded", function () {
        const currentPath = window.location.pathname;
        console.log("현재 경로:", currentPath); // 디버깅용 콘솔 출력

        document.querySelectorAll(".navigation a").forEach(link => {
            const linkPath = link.getAttribute("href");

            // 현재 URL이 네비게이션 항목의 경로로 시작하면 active 클래스 추가
            if (currentPath.startsWith(linkPath)) {
                link.classList.add("active");
            }
        });
    });