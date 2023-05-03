const board = document.getElementById('board');
const boardNo = document.getElementById('board-no');
console.log(boardNo.value)

get_board_detail();
function get_board_detail() {
    fetch(`/community/${boardNo}`)
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

function create_board(b) {
    // console.log(users, userList)
    board.insertAdjacentHTML("beforeend", `${b.contents}`);
}