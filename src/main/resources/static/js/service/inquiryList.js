const inquiryBoxWrap = document.getElementById('inquiry-box-wrap');
const inquiryPageNum = document.getElementById('inquiry-page-num').firstElementChild;
const searchKeyword = document.getElementById('inquiry-search-keyword');
const inquirySearchBtn = document.getElementById('inquiry-search-btn');
find_all_inquiry();

// DB에서 문의정보 불러오기
function find_all_inquiry(nowPage, recordSize, pageSize, searchKeyword, searchTpype, tradeType) {
    let url = '/service/findAllInquiry';
    url = nowPage === undefined || nowPage === '' ? url : url + '?nowPage=' + nowPage;
    url = recordSize === undefined || recordSize === '' ? url : url + '&recordSize=' + recordSize;
    url = pageSize === undefined || pageSize === '' ? url : url + '&pageSize=' + pageSize;
    url = searchKeyword === undefined || searchKeyword === '' ? url : url + '&keyword=' + searchKeyword; // 검색키워드
    url = searchTpype === undefined || searchTpype === '' ? url : url + '&searchType=' + searchTpype; // 검색타입
    url = tradeType === undefined || tradeType === '' ? url : url + '&tradeType=' + tradeType; // 무역유형(수출수입)
    console.log(url)
    fetch(url)
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
    const authCheck = document.getElementById('auth').value === 'true';
    inquiryBoxWrap.innerHTML = '';
    if (!authCheck) {
        data.forEach((data, i) => {
            let asterisk = '';
            let nameComut = 0;
            let name = '';
            if(data.name.length <= 2) {
                nameComut = data.name.length - 1;
                for (let i = 0; i < nameComut; i++) {
                    asterisk += '*';
                }
                name = data.name?.slice(0, 1) + asterisk;
            } else {
                nameComut = data.name.length - 2;
                for (let i = 0; i < nameComut; i++) {
                    asterisk += '*';
                }
                name = data.name?.slice(0, 1) + asterisk + data.name?.slice(-1);
            }
            const phone = data.phone?.slice(-4);
            const validation = data.processing === 0 ? '미확인' : '확인완료';

            inquiryBoxWrap.insertAdjacentHTML('beforeend',
                `<div class="inquiry-box">
                <ul>
                    <li class="inquiry-no">${data.no}</li>
                    <li class="inquiry-title"><a href="/service/inquiryDetail?no=${data.no}&arrow=">${name}님의 글입니다.</a></li>
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
    } else if (authCheck) {
        data.forEach((data, i) => {
            const validation = data.processing === 0 ? '미확인' : '확인완료';

            inquiryBoxWrap.insertAdjacentHTML('beforeend',
                `<div class="inquiry-box">
                <ul>
                    <li class="inquiry-no">${data.no}</li>
                    <li class="inquiry-title"><a href="/service/inquiryDetail?no=${data.no}&arrow=">${data.header}</a></li>
                    <li class="inquiry-write">${data.name}</li>
                    <li class="inquiry-phone">${data.phone}</li>
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
}

// 페이지네이션 생성 함수
const create_page_num = (pagination) => {
    const searchType = document.getElementById('inquiry-search-type').value;
    const keyword = searchKeyword.value;
    inquiryPageNum.innerHTML = '';
    const nextBtnNum = (Math.trunc((pagination.nowPage - 1) / 10) + 1) * 10 + 1;
    console.log('next : ' + nextBtnNum)
    const prevBtnNum = (Math.trunc(pagination.nowPage / 10) - 1) * 10 + 1;
    console.log('prev : ' + prevBtnNum)
    console.log('now : ' + pagination.nowPage)
    if (pagination.existPrevPage) {
        inquiryPageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_inquiry('${prevBtnNum}','', '', '${keyword}', '${searchType}')"><</li>              
    `)
    }
    for (let i = pagination.startPage; i <= pagination.endPage; i++){
        inquiryPageNum.insertAdjacentHTML('beforeend', `
        <li class="page-no" onclick="find_all_inquiry('${i}','', '', '${keyword}', '${searchType}')">${i}</li>
        `)
    }

    if (pagination.existNextPage) {
        inquiryPageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_inquiry('${nextBtnNum}','', '', '${keyword}', '${searchType}')">></li>            
    `)
    }

}

// 게시글 검색 이벤트
inquirySearchBtn.addEventListener('click', () => {
    const searchType = document.getElementById('inquiry-search-type').value;
    const keyword = searchKeyword.value;
    find_all_inquiry('1', '', '', keyword, searchType);
})
searchKeyword.addEventListener('keyup', (e) => {
    if(e.code === 'Enter'){
        inquirySearchBtn.click();
    }

})

// 클릭한 페이지 색상 변경
const click_page_num = (nowPage) => {
    const no = document.getElementsByClassName('page-no');
    for (let n of no) {
        if (n.textContent === nowPage.toString()) {
            n.style.color = '#69A6F0';
        }
    }
}

// 수출/수입 선택
const select_trade = (item, tradeType) => {
    const trades = [...document.getElementById('select-tradeType').querySelectorAll('ul li')];

    trades.forEach(trade => {
        trade.style.backgroundColor = '#777';
    })
    item.style.backgroundColor = '#1a2636';
    console.log(tradeType);
    find_all_inquiry('1', '', '', '', '', tradeType);

}