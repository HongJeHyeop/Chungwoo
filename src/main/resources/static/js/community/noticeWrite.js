const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const contents = document.getElementById('quill_html');

//btn
const writeBtn = document.getElementById('btn').firstElementChild;
const cancelBtn = writeBtn.nextElementSibling;

// quill 툴바옵션
const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons

    [{ 'header': 1 }, { 'header': 2 }],               // custom button values
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
    [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
    [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
    [{ 'direction': 'rtl' }],                         // text direction

    [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

    [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
    [{ 'font': [] }],
    [{ 'align': [] }],

    ['clean']                                         // remove formatting button
];

// quill 생성
const quill = new Quill('#editor', {
    modules: {
        toolbar: toolbarOptions
    },
    theme: 'snow'
});
// 서버로 보내기 위해 input태그로 카피
quill.on('text-change', function() {
    contents.value = quill.root.innerHTML;
});

// 작성하기 버튼 클릭시 서버전송
// function insert_contents() {
//     console.log("클릭")
//     fetch('/community/insert',{
//         method:'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': csrfToken
//         },
//         body: JSON.stringify({
//             contents: quill.root.innerHTML
//         })
//     })
//         .then(value => value.text())
//         .then(value => {
//             console.log("승인여부 >>>" + value);
//         })
//         .catch(reason => {
//             console.log("승인오류");
//         })
// }
writeBtn.addEventListener('click', notice_write_check)
cancelBtn.addEventListener('click', () => {
    location.href = '/community/notice';
})

function notice_write_check() {
    const check = confirm('정말 작성하시겠습니까?');
    if (check){
        const form = document.forms.item(0);
        form.submit();
    }
}
