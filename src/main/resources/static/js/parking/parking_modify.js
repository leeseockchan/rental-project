document.addEventListener('submit', function(e) {

    e.preventDefault();

    const parking = {
        name: document.getElementById('parkingName').value,
        address: document.getElementById('parkingAddress').value,
        latitude: document.getElementById('parkingLatitude').value,
        longtitude: document.getElementById('parkingLongtitude').value,
        province: document.getElementById('parkingProvince').value,
        district: document.getElementById('parkingDistrict').value,
    }


