const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");

const inputId = document.querySelector('input[name=id]');
const inputEmail = document.querySelector('input[name=email]')
const authEmailBtn = inputEmail.nextElementSibling;
const inputBox = document.querySelector('#auth-email');
const authBox = document.querySelector('#auth-num');
const inputAuthNum = document.querySelector('input[name=email-auth-num]');
const authBtn = inputAuthNum.nextElementSibling;
const passwordUpdateBox = document.querySelector('#update-pw');
const pw = passwordUpdateBox.getElementsByTagName('input').item(0);
const pwChk = passwordUpdateBox.getElementsByTagName('input').item(1);
let pwEquals, authChk = false;

authEmailBtn.addEventListener('click', () => {
    fetch('/user/userCheck', {
        method : 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            "id": inputId.value,
            'email':inputEmail.value
        })
    })
        .then(res => {
            if (!res.ok) {
                return res.text().then(errorMessage => {
                    inputId.style.border = '1px solid red';
                    inputEmail.style.border = '1px solid red';
                    inputBox.querySelector('.false-text')?.remove();
                    inputBox.insertAdjacentHTML('beforeend', `<p class="false-text">입력하신 아이디와 이메일이 존재하지 않습니다.</p>`)
                    throw new Error('Failed to fetch data');
                });
            }
            return res.text(); // 문자열 데이터를 읽어옴
        })
        .then(res => {
            console.log(res)
            auth_num_check(res);
            authBox.style.display = 'block';
            inputId.style.border = '1px solid #69A6F0';
            inputEmail.style.border = '1px solid #69A6F0';
            inputId.setAttribute('readonly', '');
            inputEmail.setAttribute('readonly', '');
            inputBox.querySelector('.false-text')?.remove();

        })
        .catch(reason => {
            console.log(reason);
        })
})

const auth_num_check = (authNum) => {
    authBtn.addEventListener('click', () => {
        if (authNum === inputAuthNum.value) {
            alert("인증에 성공하였습니다.");
            authChk = true;
            authBox.insertAdjacentHTML('beforeend', `<p class="auth-text">인증이 완료되었습니다</p>`)
            inputId.style.border = '1px solid #69A6F0';
            inputEmail.style.border = '1px solid #69A6F0';
            inputAuthNum.style.border = '1px solid #69A6F0';
            inputAuthNum.setAttribute('readonly', '');
            passwordUpdateBox.style.display = 'block';
        } else {
            alert("인증에 실패하였습니다.");
            authChk = false;
            inputId.style.border = '1px solid red';
            inputEmail.style.border = '1px solid red';
            inputAuthNum.style.border = '1px solid red';
        }
    })
}


pw.addEventListener('keyup', password_reg)
pwChk.addEventListener('keyup', password_check)
// 패스워드 정규식 체크
function password_reg() {

    const reg = new RegExp('^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$');
    if (reg.test(pw.value)) {
        pw.style.border = '1px solid #69A6F0';
    } else {
        pw.style.border = '1px solid red';
    }

}

// 패스워드 체크
function password_check() {
    if (pw.value.trim() !== pwChk.value.trim()) {
        pwChk.style.border = '1px solid red';
        passwordUpdateBox.querySelector('p')?.remove();
        passwordUpdateBox.insertAdjacentHTML('beforeend', `
            <p style=" color: red; text-align: left"> 비밀번호가 일치하지 않습니다.</p>
        `)
        pwEquals = false;
    } else {
        pwChk.style.border = '1px solid #69A6F0';
        passwordUpdateBox.querySelector('p')?.remove();
        passwordUpdateBox.insertAdjacentHTML('beforeend', `
            <p style="color: #69A6F0; text-align: left"> 비밀번호가 일치합니다.</p>
        `)
        pwEquals = true;
    }
}

// submit 체크
function form_check() {
    if(!authChk){
        alert('아이디와 이메일 인증을 진행해주세요!');
    } else if(!pwEquals){
        alert('비밀번호를 다시확인해주십시오!');
    } else {
        fetch('/user/updatePassword', {
            method: 'PUT',
            headers: {
                'Content-Type': 'Application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: JSON.stringify({
                "id": inputId.value,
                'email':inputEmail.value,
                'pw': pw.value
            })
        })
            .then(res => res.text())
            .then(res => {
                if (res === 'true') {
                    alert('비밀번호가 정상적으로 변경되었습니다!')
                    location.href = '/user/login';
                } else {
                    alert('비밀번호 변경에 실패하였습니다 다시시도해주십시오')
                    location.href = '/user/passwordSearch';
                }
            })
            .catch(reason => {
                console.log(reason)
            })
    }
}