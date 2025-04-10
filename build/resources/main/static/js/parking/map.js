document.addEventListener("DOMContentLoaded", function() {
    var mapContainer = document.getElementById('map');

    if (mapContainer) {
        var latitude = mapContainer.getAttribute("data-latitude");
        var longitude = mapContainer.getAttribute("data-longitude");
        var parkingName = mapContainer.getAttribute("data-parking-name");

        var mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3
        };

        var map = new kakao.maps.Map(mapContainer, mapOption);
        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(latitude, longitude),
            map: map
        });

        var infowindow = new kakao.maps.InfoWindow({
            content: `<div style="padding:5px; white-space: nowrap; font-size:14px;">${parkingName}</div>`
        });
        infowindow.open(map, marker);
    }
});
