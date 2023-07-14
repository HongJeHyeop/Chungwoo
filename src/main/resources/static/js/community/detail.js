const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const detailItem = document.getElementById('notice-detail');
let boardNo = document.getElementById('board-no').value;


get_board_detail(boardNo);

// 게시글 번호에 따른 GET요청
async function get_board_detail(pageNo, arrow) {
    no = pageNo.toString()
    const url = '/community/restDetail/' + no + '?arrow=' + arrow;
    await fetch(url)
        .then(response => response.json())
        .then(value => {
            // 생성
            create_board(value);
        })
        .catch(reason => {
            alert("페이지가 존재하지않습니다!")
            console.log("보드 생성 오류")
        })
}

// 상세 게시글 생성 메서드
function create_board(boardVO) {
    detailItem.innerHTML = '';
    detailItem.insertAdjacentHTML("beforeend",
        `
            <table>
                <thead>
                <th>${boardVO.title}</th>
                <th colspan="2">${boardVO.writeDate}</th>
                </thead>
                <tbody>
                <td colspan="3">
                    ${boardVO.contents}
                </td>
                </tbody>
                <tfoot>
                <tr id="upload-files">
                <td colspan="2"></td>
                <td></td>
                </tr>
                </tfoot>
            </table>
            <div id="move-btn">
                <input type="button" value="이전글" onclick="get_board_detail('${boardVO.no}', 'prev')">
                <input type="button" value="목록" onclick="location.href='/community/notice'">
                <input type="button" value="다음글" onclick="get_board_detail('${boardVO.no}', 'next')">
            </div>`);
    const uploadFiles = document.getElementById('upload-files');
    if (boardVO.fileName !== null && boardVO.fileName !== undefined && boardVO.fileName !== '') {
        const orgFileName = boardVO.fileName.substring(boardVO.fileName.indexOf('_', boardVO.fileName.indexOf('_') + 1) + 1)
        uploadFiles.innerHTML = '';
        uploadFiles.insertAdjacentHTML('beforeend',
            `<td colspan="2"><a href="/board/down/${boardVO.no}"><i class="fa-solid fa-download"></i> ${orgFileName}</a></td>
                  <td>2022-03-21</td>`)
    }
    boardNo = boardVO.no
}

// 게시글 삭제
function delete_notice() {
    const check = confirm('정말 삭제하시겠습니까?');
    if (check) {
        fetch('/community/notice/delete', {
            method : 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body   : JSON.stringify({
                no: boardNo
            })
        }).then(value => value.text())
            .then(value => {
                location.href = '/community/notice'
            })
            .catch(reason => {
                console.log(reason)
            });
    }
}