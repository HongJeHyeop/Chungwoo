const noticeList = document.getElementById('notice-list');
const pageNum = document.getElementById('page-num').firstElementChild;


get_all_notice_list()
function get_all_notice_list() {
    fetch('/community/notice/list')
        .then(value => value.json())
        .then(value => {
            console.log(value.boardVOS)
            console.log(value.pagination)
            create_notice_list(value.boardVOS)
            create_page_num(value.pagination)
        })
        .catch(reason => {
            console.log('list생성 오류')
        })
}

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
    let url = '/comunity/notice/list';
    url += '?nowPage=' + nowPage;
    url += '&recordSize=' + recordSize;
    url += '&pageSize=' + pageSize;
    url += searchKeyword === '' ? url : url + '&searchKeyword=' + searchKeyword; // 검색키워드
}

console.log(pageNum)

function create_page_num(pagination){
    pageNum.innerHTML = '';
    for (let i = pagination.startPage; i <= pagination.endPage; i++){
        pageNum.insertAdjacentHTML('beforeend', `
        <li><</li>
        <li>${i}</li>
        <li>></li>
        `)
    }

}