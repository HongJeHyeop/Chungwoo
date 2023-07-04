const inquiryBoxWrap = document.getElementById('inquiry-box-wrap');
const inquiryPageNum = document.getElementById('inquiry-page-num').firstElementChild;

find_all_inquiry();

// DB에서 문의정보 불러오기
function find_all_inquiry(nowPage, recordSize, pageSize, searchKeyword) {
    let url = '/service/findAllInquiry';
    url = nowPage === undefined || nowPage === '' ? url : url + '?nowPage=' + nowPage;
    url = recordSize === undefined || recordSize === '' ? url : url + '&recordSize=' + recordSize;
    url = pageSize === undefined || pageSize === '' ? url : url + '&pageSize=' + pageSize;
    url = searchKeyword === undefined || searchKeyword === '' ? url : url + '&searchKeyword=' + searchKeyword; // 검색키워드

    fetch(`${url}`)
        .then(value => value.json())
        .then(value => {
            // 리스트 생성
            create_inquiry_list(value.inquiryVOS);
            // 페이지네이션 생성
            create_page_num(value.pagination);
            // 클릭이벤트
            click_page_num(value.pagination.nowPage);
        })
        .catch(reason => {
            console.log("온라인문의 목록 생성 오류!")
        })
}

/* 온라인문의 목록 생성 함수 */
const create_inquiry_list = (data) => {
    inquiryBoxWrap.innerHTML = '';
    data.forEach((data, i) => {
        let asterisk = '';
        for (let i = 0; i < data.name.length - 2; i++) {
            asterisk += '*';
        }
        const name = data.name?.slice(0, 1) + asterisk + data.name?.slice(-1);
        const phone = data.phone?.slice(-4);
        const validation = data.processing === 0 ? '미확인' : '확인완료';

        inquiryBoxWrap.insertAdjacentHTML('beforeend',
            `<div class="inquiry-box">
                <ul>
                    <li class="inquiry-no">${data.no}</li>
                    <li class="inquiry-title">비공개입니다.</li>
                    <li class="inquiry-write">${name}</li>
                    <li class="inquiry-phone">${phone}</li>
                    <li class="inquiry-write-date">${data.writeDate}</li>
                    <li class="inquiry-validation">${validation}</li>
                </ul>
            </div>`)

        const inquiryBox = document.getElementsByClassName('inquiry-box').item(i);
        if (data.processing === 1) {
            inquiryBox.style.backgroundColor = "rgba(255, 255, 0, 0.2)";
        }
    })
}

// 페이지네이션 생성 함수
const create_page_num = (pagination) => {
    inquiryPageNum.innerHTML = '';
    const nextBtnNum = (Math.trunc(pagination.nowPage / 10) + 1) * 10 + 1;
    const prevBtnNum = (Math.trunc(pagination.nowPage / 10) - 1) * 10 + 1;
    if (pagination.existPrevPage) {
        inquiryPageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_inquiry(${prevBtnNum})"><</li>              
    `)
    }
    for (let i = pagination.startPage; i <= pagination.endPage; i++){
        inquiryPageNum.insertAdjacentHTML('beforeend', `
        <li class="page-no" onclick="find_all_inquiry(${i})">${i}</li>
        `)
    }

    if (pagination.existNextPage) {
        inquiryPageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_inquiry(${nextBtnNum})">></li>            
    `)
    }

}

// 클릭한 페이지 색상 변경
const click_page_num = (nowPage) => {
    const no = document.getElementsByClassName('page-no');
    for (let n of no) {
        if (n.textContent === nowPage.toString()) {
            n.style.color = '#69A6F0';
        }
    }
}