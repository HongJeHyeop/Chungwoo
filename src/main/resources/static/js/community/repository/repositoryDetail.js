const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const detailItem = document.getElementById('repository-detail');
let boardNo = document.getElementById('board-no').value;


get_repository_detail(boardNo, '', '자료실');

// 게시글 번호에 따른 GET요청
async function get_repository_detail(boardNo, arrow, boardType) {
    boardNo = boardNo.toString();
    if (arrow === undefined) {
        arrow = '';
    }
    const params = {
        no       : boardNo,
        boardType: boardType,
        arrow    : arrow
    }
    const queryString = new URLSearchParams(params).toString()
    const url = '/community/repository/restDetail?' + queryString;
    await fetch(url)
        .then(response => response.json())
        .then(value => {
            // 생성
            create_repository(value[0]);
            if (value[1] !== null) {
                create_files(value[1]);
            }
        })
        .catch(reason => {
            alert("페이지가 존재하지않습니다!")
            location.href = '/community/repository/repository';
        })
}

// 상세 게시글 생성 메서드
function create_repository(boardVO) {
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
                <tfoot id="upload-files">
                <tr>
                <td colspan="2"></td>
                <td></td>
                </tr>
                </tfoot>
            </table>
            <div id="move-btn">
                <input type="button" value="이전글" onclick="get_repository_detail('${boardVO.no}', 'prev', '자료실')">
                <input type="button" value="목록" onclick="location.href='/community/repository/repository'">
                <input type="button" value="다음글" onclick="get_repository_detail('${boardVO.no}', 'next', '자료실')">
            </div>`);

    boardNo = boardVO.no
}

function create_files(fileVO) {
    const uploadFiles = document.getElementById('upload-files');
    uploadFiles.innerHTML = '';
    if (fileVO.length > 0) {
        for (const file of fileVO) {
            if (file.fileOrgName !== null && file.fileOrgName !== undefined && file.fileOrgName !== '') {
                uploadFiles.insertAdjacentHTML('beforeend',
                    `<tr><td colspan="2"><a href="/board/down/${file.no}"><i class="fa-solid fa-download"></i> ${file.fileOrgName}</a></td>
                  <td>${file.writeDate}</td></tr>`)
            }
        }
    } else {
        uploadFiles.insertAdjacentHTML('beforeend',
            `<tr><td colspan="2"></td><td></td></tr>`
        )
    }
}

// 게시글 삭제
function delete_notice() {
    const check = confirm('정말 삭제하시겠습니까?');
    if (check) {
        fetch('/community/repository/delete', {
            method : 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body   : JSON.stringify({
                no       : boardNo,
                boardType: '자료실'
            })
        }).then(value => value.text())
            .then(value => {
                location.href = '/community/repository/repository'
            })
            .catch(reason => {
                console.log(reason)
            });
    }
}

// 수정하기 클릭
function move_update_write() {
    window.location.href = '/community/repository/repositoryWrite?no=' + boardNo;
}