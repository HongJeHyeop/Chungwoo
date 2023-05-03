const noticeList = document.getElementById('notice-list');

get_all_notice_list()
function get_all_notice_list() {
    fetch('/community/notice/list')
        .then(value => value.json())
        .then(value => {
            create_notice_list(value)
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