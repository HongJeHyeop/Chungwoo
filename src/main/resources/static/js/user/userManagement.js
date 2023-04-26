const userList = document.getElementById('user-list');


get_all_register_request();
function get_all_register_request() {
    fetch('/user/aaa')
        .then(response => response.json())
        .then(value => {
            create_register_user_list(value);
            console.log(value)
        })
        .catch(reason => {
            console.log("유저리스트 생성 오류")})
}

function create_register_user_list(users) {
    console.log(users, userList)
    userList.insertAdjacentHTML('afterend', '');
    for (let user of users) {
    userList.insertAdjacentHTML('afterend',
        `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.role}</td>
                <td><input type="button" value="승인"></td>
                <td><input type="button" value="거절"></td>
            </tr>`)

    }
}