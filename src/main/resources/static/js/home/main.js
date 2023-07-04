const images = document.querySelectorAll('.main-img');
const selectBtns = document.querySelectorAll('.select-btn');
const simpleMainNotice = document.getElementById('simple-main-notice');

// 현재 이미지 번호
let currentImageIndex = 0;

/*** 이미지 슬라이드 ***/
// 5초 간격으로 이미지 전환 함수 호출
setInterval(change_image, 5000);

// 버튼 클릭했을 시 해당 이미지로 이동
selectBtns.forEach(btn => {
    btn.addEventListener('click', select_image);
});

// 초기 이미지 버튼 색상 강조
selectBtns[currentImageIndex].style.backgroundColor = 'rgba(255, 255, 255, 0.9)';

// 이미지 전환 함수
function change_image() {
    // 현재 이미지 숨기기
    images[currentImageIndex].classList.remove('active');
    // 다음 이미지로 인덱스 증가
    currentImageIndex = (currentImageIndex + 1) % images.length;
    // 다음 이미지 보이기
    images[currentImageIndex].classList.add('active');
    // 선택한 버튼 색상 강조
    selectBtns.forEach(btn => {
        btn.style.backgroundColor = 'rgba(255, 255, 255, 0.3)';
    });
    selectBtns[currentImageIndex].style.backgroundColor = 'rgba(255, 255, 255, 0.9)';
}

// 버튼으로 이미지 선택하는 함수
function select_image(e) {
    // 클릭한 버튼의 인덱스 가져오기
    const i = parseInt(e.target.getAttribute('data-index'));
    // 현재 이미지 숨기기
    images[currentImageIndex].classList.remove('active');
    // 선택한 이미지 보이기
    images[i].classList.add('active');
    // 선택한 버튼 활성
    selectBtns[currentImageIndex].classList.remove('active');
    selectBtns[i].classList.add('active');
    // 선택한 버튼 색상 강조
    selectBtns.forEach(btn => {
        btn.style.backgroundColor = 'rgba(255, 255, 255, 0.3)';
    });
    selectBtns[i].style.backgroundColor = 'rgba(255, 255, 255, 0.9)';
    // 현재 이미지 인덱스 업데이트
    currentImageIndex = i;
}

// 공지사항 간략히 보기
fetch('/mainNotice')
    .then(value => value.json())
    .then(value => {
        create_simple_main_notice(value)
    })
    .catch(reason => {
        console.log('심플 공지사항 생성 오류! :' + reason)
    })

// 공지사항 생성 함수
function create_simple_main_notice(value) {
    simpleMainNotice.innerHTML = '';
    value.forEach((boardVO) => {
        simpleMainNotice.insertAdjacentHTML('beforeend',
            `<tr>
                <td><a href="/community/detail?no=${boardVO.no}">${boardVO.title}</a></td>
                <td>${boardVO.writeDate}</td>
            </tr>`)
    })
}


// 네이버 지도 API
const mapOptions = {
    center: new naver.maps.LatLng(35.8443562, 128.6225852), // 회사 위치
    zoom: 15
}
const map = new naver.maps.Map('map', mapOptions);

const marker = new naver.maps.Marker({
    position: new naver.maps.LatLng(35.8443562, 128.6225852),
    map: map
});