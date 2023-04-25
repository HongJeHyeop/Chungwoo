const userList = document.getElementById('user-list-container');


get_all_register_request();
function get_all_register_request() {
    fetch('/user/registerAuthorization')
        .then(response => response.json())
        .then(value => {
            create_register_user_list(value);
            console.log(value)
        })
        // .catch(reason => {
        //     console.log("유저리스트 생성 오류")})
}

function create_register_user_list(users) {
    // userList.innerText = '';
    for (let user of users) {
    userList.insertAdjacentElement('beforeend',
        `<table>
            <tr>
                <td>지점명</td>
                <td>이름</td>
                <td>아이디</td>
                <td>연락처</td>
            </tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.role}</td>
                <td></td>
            </tr>
        </table>`)

    }
}