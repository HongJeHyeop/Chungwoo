const noticeList = document.getElementById('notice-list');
const pageNum = document.getElementById('page-num').firstElementChild;


find_all_notice()


function create_notice_list(list) {
    noticeList.innerText = '';
    for (let item of list) {
    noticeList.insertAdjacentHTML('beforeend',
        `<tr>
                        <td class="tbl-title">${item.no}</td>
                        <td class="tbl-content"><a href="/community/detail/${item.no}">${item.title}</a></td>
                        <td class="tbl-upload">${item.writeDate}</td>
                    </tr>`)

    }
}

function find_all_notice(nowPage, recordSize, pageSize, searchKeyword) {
    let url = '/community/notice/list';
    url = nowPage === undefined || nowPage === '' ? url : url + '?nowPage=' + nowPage;
    url = recordSize === undefined || recordSize === '' ? url : url +'&recordSize=' + recordSize;
    url = pageSize === undefined || pageSize === '' ? url : url +'&pageSize=' + pageSize;
    url = searchKeyword === undefined ||searchKeyword === '' ? url : url + '&searchKeyword=' + searchKeyword; // 검색키워드

    console.log(url)
    fetch(`${url}`)
        .then(value => value.json())
        .then(value => {
            console.log(value.boardVOS)
            console.log(value.pagination)
            create_notice_list(value.boardVOS)
            create_page_num(value.pagination)
            click_page_num(value.pagination.nowPage)
        })
        .catch(reason => {
            console.log('list생성 오류')
        })
}

console.log(pageNum)

function create_page_num(pagination){
    pageNum.innerHTML = '';
    if (pagination.existPrevPage) {
    pageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_notice(${pagination.nowPage} - 1)"><</li>              
    `)
    }
    for (let i = pagination.startPage; i <= pagination.endPage; i++){
        pageNum.insertAdjacentHTML('beforeend', `
        <li class="page-no" onclick="find_all_notice(${i})">${i}</li>
        `)
    }
    if (pagination.existNextPage) {
        pageNum.insertAdjacentHTML('beforeend',`
        <li onclick="find_all_notice(${pagination.nowPage} + 1)">></li>            
    `)
    }

}
function click_page_num(nowPage) {

    const no = document.getElementsByClassName('page-no');
    for (let n of no) {
        if (n.textContent === nowPage.toString()) {
            n.style.color = '#69A6F0';
        }
    }
}