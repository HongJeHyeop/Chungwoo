const container = document.getElementById('container');
const boardNo = document.getElementById('board-no').value;

get_board_detail(boardNo);

// 게시글 번호에 따른 GET요청
function get_board_detail(pageNo) {
    no = pageNo.toString()
    fetch(`/community/restDetail/${no}`)
        .then(response => response.json())
        .then(value => {
            // 생성
            console.log(value)
            create_board(value);
        })
        .catch(reason => {
            alert("페이지가 존재하지않습니다!")
            console.log("보드 생성 오류")
        })
}

// 상세 게시글 생성 메서드
function create_board(boardVO) {
    // console.log(users, userList)
    container.innerHTML = '';
    let nextPage = boardVO.no + 1;
    let prevPage = boardVO.no - 1;
    console.log(nextPage, prevPage)
    container.insertAdjacentHTML("beforeend",
        `<div id="update-btn">
                <input type="button" value="수정하기" onclick="location.href = '/community/noticeWrite/${boardVO.no}'">
            </div>
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
                <tr>
                    <td colspan="2"><i class="fa-solid fa-download"></i> 첨부파일1</td>
                    <td>2022-03-21</td>
                </tr>
                <tr>
                    <td colspan="2"><i class="fa-solid fa-download"></i> 첨부파일1</td>
                    <td>2022-03-21</td>
                </tr>
                </tfoot>
            </table>
            <div id="move-btn">
                <input type="button" value="이전글" onclick="get_board_detail('${prevPage}')">
                <input type="button" value="목록" onclick="location.href='/community/notice'">
                <input type="button" value="다음글" onclick="get_board_detail('${nextPage}')">
            </div>`);
}