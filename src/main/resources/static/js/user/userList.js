const userList = document.getElementById('user-list');

// 데이터베이스에서 회원가입 요청한 유저 목록 받아오기
get_all_register_request();
function get_all_register_request() {
    fetch('/user/allList')
        .then(response => response.json())
        .then(value => {
            // 생성
            create_register_user_list(value);
        })
        .catch(reason => {
            console.log("유저리스트 생성 오류")
        })
}

// 회원가입 요청한 유저 목록 생성
function create_register_user_list(users) {
    // console.log(users, userList)
    userList.innerText = '';
    for (let user of users) {
        userList.insertAdjacentHTML('beforeend',
            `<tr>
                <td>${user.branch}</td>
                <td>${user.position}</td>
                <td>${user.name}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
            </tr>`)
    }
}

