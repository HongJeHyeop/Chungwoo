const branch = document.getElementById('branch')?.value;
console.log(branch);
const branchMaps = [
    {'name': 'daegu', 'latitude': 35.8443562, 'longitude': 128.6225852},
    {'name': 'gumi', 'latitude': 36.101261, 'longitude': 128.383889},
    {'name': 'seoul', 'latitude': 37.562581, 'longitude': 126.970860},
    {'name': 'gyeongsan', 'latitude': 35.866539, 'longitude': 128.818518},
    {'name': 'busan', 'latitude': 35.105471, 'longitude': 129.035942},
]

// 네이버 지도 API
map(branch);
function map(branch) {
    branchMaps.forEach(data => {
        if(branch === data.name) {
            const mapOptions = {
                center: new naver.maps.LatLng(data.latitude, data.longitude), // 회사 위치
                zoom  : 17
            }
            const map = new naver.maps.Map('map', mapOptions);

            const marker = new naver.maps.Marker({
                position: new naver.maps.LatLng(data.latitude, data.longitude),
                map     : map
            });
            return
        }
    })
}
