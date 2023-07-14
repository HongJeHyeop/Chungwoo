const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const deleteBtn = document.getElementById('delete-btn');

const confirm_req = (no, process) => {
    const processing = process
    fetch('/service/updateProcess', {
        method : 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body   : JSON.stringify({
            no        : no,
            processing: processing
        })
    })
        .then(res => res.text())
        .then(res => {
            location.href = '/service/inquiryList'
        })
        .catch(err => {
            console.log(err)
        })
}

// 삭제요청
const delete_req = (no) => {
    const check = confirm('정말 삭제하시겠습니까?')
    if (check) {
        fetch('/service/deleteInquiry', {
            method : 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body   : JSON.stringify({
                no: no
            })
        })
            .then(res => res.text())
            .then(res => {
                location.href = '/service/inquiryList';
            })
            .catch(err => {
                console.log(err)
            })
    }
}

// 이전, 다음 문의내역
const move_page = (no, arrow) => {
    console.log(no)
    location.href = `/service/inquiryDetail?no=${no}&arrow=${arrow}`
}

// 일반유저 본인 확인
const check_anonymous = (name, phone) => {
    const inputName = document.getElementById('check-name').value.trim();
    const inputPhone = document.getElementById('check-phone').value.trim();

    if (inputPhone.includes('-')) {
        alert('하이픈(\'-\')을 제외하고 입력하세요');
        return
    } else if (inputName === name && inputPhone === phone) {
        fetch(`/service/inquiryDetailUser?no=${inquiryNo}&arrow=`)
            .then(res => res.json())
            .then(res => {
                create_detail_anonymous(res);
            })
            .catch(err => {
                console.log(err)
            })
    }

}

function create_detail_anonymous(res) {
    const anonymousInquiry = document.getElementById('anonymous-inquiry');
    anonymousInquiry.innerHTML = '';
    anonymousInquiry.insertAdjacentHTML('beforeend', `
    <div id="process-btn">
                <input type="button" value="삭제하기" id="delete-btn" onclick="delete_req(${res.no})">
            </div>
            <div id="inquiry-detail-box">
                <table>
                    <tr>
                        <td class="detail-box-header">문의 번호</td>
                        <td class="detail-box-body" th:text="${res.no}"></td>
                    </tr>
                    <tr>
                        <td class="detail-box-header">성 함</td>
                        <td class="detail-box-body" th:text="${res.name}"></td>
                    </tr>
                    <tr>
                        <td class="detail-box-header">연락처</td>
                        <td class="detail-box-body" th:text="${res.phone}"></td>
                    </tr>
                    <tr>
                        <td class="detail-box-header">회사명</td>
                        <td class="detail-box-body" th:text="${res.company}"></td>
                    </tr>
                    <tr>
                        <td class="detail-box-header">제 목</td>
                        <td class="detail-box-body" th:text="${res.header}"></td>
                    </tr>
                    <tr>
                        <td class="detail-box-header">문의 내용</td>
                        <td class="detail-box-body" th:text="${res.contents}"></td>
                    </tr>
                </table>
            </div>
            <div id="move-page-btn">
                <input type="button" value="목록" onclick="location.href = '/service/inquiryList'">
            </div>`)
}