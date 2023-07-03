const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const formBox = document.getElementById('insert-form');
const updateNo = document.getElementById('update-no');

quill_editor_init()

// quill 툴바옵션
function quill_editor_init() {
    const contents = document.getElementById('quill_html');
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
            toolbar: toolbarOptions,
        },
        theme  : 'snow'
    });
// 서버로 보내기 위해 input태그로 카피
    contents.value = quill.root.innerHTML;
    quill.on('text-change', function () {
        contents.value = quill.root.innerHTML;
    });
    // quill에서 이미지 업로드 처리
    quill.getModule('toolbar').addHandler('image', function () {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.click();

        input.onchange = async () => {
            console.log("image onchange");
            const file = input.files[0];
            const formData = new FormData();
            formData.append('image', file);

            try {
                // 이미지 업로드
                const response = await fetch('/board/quillImage', {
                    method: 'POST',
                    headers: {
                        'X-CSRF-TOKEN': csrfToken
                    },
                    body: formData,
                });

                if (response.ok) {
                    const value = await response.text();
                    console.log("전송 성공");
                    console.log(value)
                    const imageUrl = value;
                    // 이미지를 Quill.js 편집기에 삽입
                    const range = quill.getSelection();
                    quill.insertEmbed(range.index, 'image', imageUrl);
                } else {
                    console.error('Image upload failed.');
                }
            } catch (error) {
                console.error('Error during image upload:', error);
            }
        };
    });
}

// 작성여부 체크
function notice_write_check() {
    const check = confirm('정말 작성하시겠습니까?');
    if (check) {
        const form = document.forms.item(0);
        form.submit();
    }
}

// 수정하기 폼 생성
function update_write(item) {
    formBox.innerHTML = '';
    formBox.insertAdjacentHTML('beforeend',
        `<form action="/community/notice/update" method="POST" enctype="multipart/form-data">
                <div id="board-type">
                    <select name="boardType">
                        <option>공지사항</option>
                        <option>자료실</option>
                    </select>
                </div>
                <div id="board-header">
                    <input type="text" name="title" placeholder="제목을 입력하세요" value="${item.title}"></br>
                    <input type="file" name="file">
                </div>
                <div id="editor">
                    ${item.contents}
                </div>
                <input type="hidden" id="quill_html" name="contents" value="">
                <input type="hidden" name="_csrf" value="${csrfToken}"/>
                <input type="hidden" name="no" value="${updateNo.value}">
                <div id="btn">
                    <input type="button" value="작성하기" onclick="notice_write_check()">
                    <input type="button" value="취소하기" onclick="location.href = '/community/notice'">
                </div>
                </form>`)
    quill_editor_init()
}

// 수정하기로 왔을때
notice_update()

// 수정 요청
function notice_update() {
    if (updateNo != null) {
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