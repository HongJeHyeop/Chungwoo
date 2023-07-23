const duplBtn = document.getElementById('dupl-btn');
const duplText = duplBtn.previousElementSibling;
const [pw, pwChk] = [...document.querySelectorAll('input[type=password]')];
const inputName = document.querySelector('input[name="name"]');
const inputPhone1 = document.querySelector('#phone-input').firstElementChild;
const inputPhone2 = inputPhone1.nextElementSibling;
const inputPhone3 = inputPhone2.nextElementSibling;
const inputPhone = document.querySelector('input[name="phone"]');
const inputEmail = document.querySelector('input[name="email"]');
const inputPosition = document.querySelector('input[name="position"]');
// const submitBtn = document.querySelector('#btn-box').firstElementChild;

let idCheck, pwCheck, nameCheck, phoneCheck, emailCheck, positionCheck = false;


// 최종확인후 submit
function form_check() {
    // 이릅입력 체크
    if(inputName.value.trim() !== '' && inputName.value.trim() !== undefined){
        nameCheck = true;
    } else {
        nameCheck = false;
    }
    // 전화번호 입력 체크
    if(
        inputPhone1.value.trim() !== '' && inputPhone1.value.trim() !== undefined &&
        inputPhone2.value.trim() !== '' && inputPhone2.value.trim() !== undefined &&
        inputPhone3.value.trim() !== '' && inputPhone3.value.trim() !== undefined
    ) {
        inputPhone.value = inputPhone1.value + inputPhone2.value + inputPhone3.value;
        console.log(inputPhone.value);
        phoneCheck = true;
    } else {
        phoneCheck = false;
    }
    // 이메일 입력 체크
    if (inputEmail.value.trim() !== '' && inputEmail.value.trim() !== undefined) {
        emailCheck = true;
    } else {
        emailCheck = false;
    }
    // 직책 입력 체크
    if (inputPosition.value.trim() !== '' && inputPosition.value.trim() !== undefined) {
        positionCheck = true;
    } else {
        positionCheck = false;
    }


    if(!idCheck) {
        alert('아이디를 입력한후 중복확인해주세요!');
        duplText.focus();
        return;
    } else if(!pwCheck) {
        alert('비밀번호를 다시확인하여 주세요!');
        pw.focus();
        return;
    } else if(!nameCheck) {
        alert('이름을 작성하여주세요!');
        inputName.focus();
        return;
    } else if (!phoneCheck) {
        alert('전화번호를 입력하여주세요!');
        inputPhone.previousElementSibling.previousElementSibling.previousElementSibling.focus();
        return;
    } else if (!emailCheck) {
        alert('이메일을 입력하여주세요!');
        inputEmail.focus();
        return;
    } else if (!positionCheck) {
        alert('직책을 입력하여주세요!');
        inputPosition.focus();
        return;
    } else if (idCheck && pwCheck && nameCheck && phoneCheck && emailCheck && positionCheck) {
        document.forms.item(0).submit();
    }
}
duplText.addEventListener('keyup', () => {
    idCheck = false;
})
duplBtn.addEventListener('click', () => {
    if (duplText.value.trim() === '' || duplText.value === undefined) {
        alert('아이디를 입력하세요!')
    } else {
        duplicate_check(duplText.value)
    }
})
pw.addEventListener('keyup', password_reg)
pwChk.addEventListener('keyup', password_check)

// 중복 체크
function duplicate_check(id) {
    fetch(`/user/register/duplicate?id=${id}`)
        .then(value => value.text())
        .then(value => {
            if (value === 'true') {
                alert('이미 존재하는 아이디입니다.')
                idCheck = false;
            } else {
                alert('사용가능한 아이디입니다!')
                idCheck = true;
            }
        })
}

// 패스워드 체크
function password_check() {

    pwChk.nextElementSibling.remove()
    pwChk.insertAdjacentHTML('afterend', '')
    if (pw.value.trim() !== pwChk.value.trim()) {
        pwChk.style.backgroundColor = 'rgba(255,215,214,0.8)'
        pwChk.insertAdjacentHTML('afterend', `
            <span style="font-size: 0.7rem; color: red"> 비밀번호가 일치하지 않습니다.</span>
        `)
        pwCheck = false;
    } else {
        pwChk.style.backgroundColor = 'rgba(214,232,255,0.8)'
        pwChk.insertAdjacentHTML('afterend', `
            <span style="font-size: 0.7rem; color: #69A6F0"> 비밀번호가 일치합니다.</span>
        `)
        pwCheck = true;
    }
}

// 패스워드 정규식 체크
function password_reg() {
    const reg = new RegExp('^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$');
    if (reg.test(pw.value)) {
        console.log(reg.test(pw.value))
        pw.style.backgroundColor = 'rgba(214,232,255,0.8)'
    } else {
        console.log(reg.test(pw.value))
        pw.style.backgroundColor = 'rgba(255,215,214,0.8)'
    }

}

// 전화번호 길이 제한
function maxLengthCheck(obj){
    if (obj.value.length > obj.maxLength){
        obj.value = obj.value.slice(0, obj.maxLength);
    }
}
