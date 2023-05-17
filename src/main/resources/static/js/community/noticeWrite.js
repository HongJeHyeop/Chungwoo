const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const contents = document.getElementById('quill_html');
const formBox = document.getElementById('insert-form');
const updateNo = document.getElementById('update-no');

console.log(csrfToken)
quilljsediterInit()
// quill 툴바옵션
function quilljsediterInit() {
    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],        // toggled buttons

        [{'header': 1}, {'header': 2}],               // custom button values
        [{'list': 'ordered'}, {'list': 'bullet'}],
        [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
        [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
        [{'direction': 'rtl'}],                         // text direction

        [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
        [{'header': [1, 2, 3, 4, 5, 6, false]}],

        [{'color': []}, {'background': []}],          // dropdown with defaults from theme
        [{'font': []}],
        [{'align': []}],

        ['image'],
        ['clean']                                         // remove formatting button
    ];

// quill 생성
    const quill = new Quill('#editor', {
        modules: {
            toolbar: toolbarOptions
        },
        theme  : 'snow'
    });
// 서버로 보내기 위해 input태그로 카피
    quill.on('text-change', function () {
        contents.value = quill.root.innerHTML;
    });

    quill.getModule('toolbar').addHandler('image', function () {
        selectLocalImage();
    });
}

function selectLocalImage() {
    const fileInput = document.createElement('input');
    fileInput.setAttribute('type', 'file');
    console.log("input.type " + fileInput.type);

    fileInput.click();

    fileInput.addEventListener("change", function () {  // change 이벤트로 input 값이 바뀌면 실행
        const formData = new FormData();
        const file = fileInput.files[0];
        formData.append('uploadFile', file);

        $.ajax({
            type       : 'post',
            enctype    : 'multipart/form-data',
            url        : '/community/noticeWrite/image',
            data       : formData,
            processData: false,
            contentType: false,
            dataType   : 'json',
            success    : function (data) {
                const range = quill.getSelection(); // 사용자가 선택한 에디터 범위
                // uploadPath에 역슬래시(\) 때문에 경로가 제대로 인식되지 않는 것을 슬래시(/)로 변환
                data.uploadPath = data.uploadPath.replace(/\\/g, '/');

                quill.insertEmbed(range.index, 'image', "/board/display?fileName=" + data.uploadPath + "/" + data.uuid + "_" + data.fileName);

            },
            error      : function (err) {
                console.log(err);
            }
        });

    });
}


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

function notice_write_check() {
    const check = confirm('정말 작성하시겠습니까?');
    if (check) {
        const form = document.forms.item(0);
        form.submit();
    }
}

function update_write(item) {
    formBox.innerHTML = '';
    formBox.insertAdjacentHTML('beforeend',
        `<form action="/community/notice/update" method="POST">
                <div id="board-type">
                    <select name="boardType">
                        <option>공지사항</option>
                        <option>자료실</option>
                    </select>
                </div>
                <div id="board-header">
                    <input type="text" name="title" placeholder="제목을 입력하세요" value="${item.title}"></br>
                    <input type="file">
                </div>
                <div id="editor">
                    ${item.contents}
                </div>
                <input type="hidden" id="quill_html" name="contents" value="${item.contents}">
                <input type="hidden" name="_csrf" value="${csrfToken}"/>
                <input type="hidden" name="no" value="${updateNo.value}">
                <div id="btn">
                    <input type="button" value="작성하기" onclick="notice_write_check()">
                    <input type="button" value="취소하기" onclick="location.href = '/community/notice'">
                </div>
                </form>`)
    quilljsediterInit()
}

// 수정하기로 왔을때
notice_update()

function notice_update() {
    if (updateNo != null) {
        console.log("수정하기")
        fetch(`/community/notice/update/write/${updateNo.value}`)
            .then(value => value.json())
            .then(value => {
                update_write(value)
            })
            .catch(reason => {
                console.log(reason)
            })
    }
}