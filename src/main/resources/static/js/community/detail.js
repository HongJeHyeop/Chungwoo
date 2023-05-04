const board = document.getElementById('board');
const boardNo = document.getElementById('board-no');
console.log(boardNo.value)

get_board_detail();
function get_board_detail() {
    fetch(`/community/aa/${boardNo}`)
        .then(response => response.json())
        .then(value => {
            // 생성
            console.log(value)
            create_board(value);
        })
        .catch(reason => {
            console.log("보드 생성 오류")
        })
}

function create_board(boardVO) {
    // console.log(users, userList)
    board.innerHTML = '';
    board.insertAdjacentHTML("beforeend",
        `<thead>
                <th>${boardVO.title}</th>
                <th>${boardVO.writeDate}</th>
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
                </tfoot>`);
}