const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const deleteBtn = document.getElementById('delete-btn');
const inquiryNo = document.getElementById('inquiry-no').value;
console.log(typeof inquiryNo)
const confirm_req = (process) => {
    const processing = process
    console.log(processing, typeof processing)
    fetch('/service/updateProcess', {
        method : 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body   : JSON.stringify({
            no     : inquiryNo,
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
deleteBtn.addEventListener('click', () => {
    const check = confirm('정말 삭제하시겠습니까?')
    if (check) {
        fetch('/service/deleteInquiry', {
            method : 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body   : JSON.stringify({
                no: inquiryNo
            })
        })
            .then(res => res.text())
            .then(res => {
                location.href ='/service/inquiryList';
            })
            .catch(err => {
                console.log(err)
            })
    }
})
