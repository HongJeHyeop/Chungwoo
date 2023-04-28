const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const userList = document.getElementById('user-list');

// 데이터베이스에서 회원가입 요청한 유저 목록 받아오기
get_all_register_request();
function get_all_register_request() {
    fetch('/user/list')
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
                <td>${user.id}</td>
                <td><input type="button" value="승인" class="approval" onclick="register_approval('${user.id}')"></td>
                <td><input type="button" value="거절" class="refusal" onclick="register_refusal('${user.id}')"></td>
            </tr>`)
    }
}


// 회원가입 요청 승인 처리
function register_approval(userId) {
    console.log("승인할 유저 아이디 >>> ", userId);
    fetch('/user/approval', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            id: userId
        })
    })
        .then(value => value.text())
        .then(value => {
            console.log("승인여부 >>>" + value);
            get_all_register_request();
        })
        .catch(reason => {
            console.log("승인오류");
        })
}

// 회원가입 요청 거절 처리
function register_refusal(userId) {
    console.log("거절할 유저 아이디 >>> ", userId);
    fetch('/user/refusal', {
        method : 'DELETE',
        headers : {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            id: userId
        })
    })
        .then(value => value.text())
        .then(value => {
            console.log('거절여부 >>>' + value);
            get_all_register_request();
        })
        .catch(reason => {
            console.log('거절오류')
        })
}