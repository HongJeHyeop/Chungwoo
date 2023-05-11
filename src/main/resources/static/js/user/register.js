const duplBtn = document.getElementById('dupl-btn');
const duplText = duplBtn.previousElementSibling;
const [pw, pwChk] = [...document.querySelectorAll('input[type=password]')];


duplBtn.addEventListener('click', () => {
    console.log(duplText.value)
    duplicate_check(duplText.value)
})
pwChk.addEventListener('keyup', password_check)

function duplicate_check(id) {
    fetch(`/user/register/duplicate?id=${id}`)
        .then(value => value.text())
        .then(value => {
            if (value === 'true') {
                alert('이미 존재하는 아이디입니다.')
            } else {
                alert('사용가능한 아이디입니다!')
            }
        })
}

function password_check() {

    pwChk.nextElementSibling.remove()
    pwChk.insertAdjacentHTML('afterend', '')
    if (pw.value.trim() !== pwChk.value.trim()) {
        pwChk.insertAdjacentHTML('afterend', `
            <span style="font-size: 0.7rem; color: red"> 비밀번호가 일치하지 않습니다.</span>
        `)
    } else {
        pwChk.insertAdjacentHTML('afterend', `
            <span style="font-size: 0.7rem; color: #69A6F0"> 비밀번호가 일치합니다.</span>
        `)
    }
}