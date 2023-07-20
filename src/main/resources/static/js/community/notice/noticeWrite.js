const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
const formBox = document.getElementById('insert-form');
let updateNo = document.getElementById('update-no')?.value;
const fileInput = document.querySelector('input[type="file"]');
let titleCheck = document.getElementById('board-header').firstElementChild;
let form = document.forms.item(0);
let uuidPath = self.crypto.randomUUID();
let dropzoneURL = '/community/insert';
quill_editor_init()
dropzone_init()

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
            const extension = ['jpg', 'jpeg', 'png'];
            const fileExtension = file.name.split('.').pop();
            console.log(fileExtension)
            const fileSize = Math.trunc((file.size / 1024 / 1024) * 100) / 100
            let check = extension.includes(fileExtension);
            console.log(check)
            if (!check) {
                alert('이미지만 업로드 가능합니다 (JPG, JPEG, PNG)');
                input.value = '';
                return;
            } else if (fileSize > 10) {
                alert('허용가능한 파일 용량을 초과하여 업로드하였습니다! ');
                input.value = '';
                return;
            }
            const formData = new FormData();
            formData.append('image', file);
            formData.append('uuidPath', uuidPath);

            try {
                // 이미지 업로드
                const response = await fetch('/board/quillImage', {
                    method : 'POST',
                    headers: {
                        'X-CSRF-TOKEN': csrfToken
                    },
                    body   : formData,
                });

                if (response.ok) {
                    const value = await response.text();
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


function dropzone_init() {
    Dropzone.autoDiscover = false;
    //fileDropzone dropzone 설정할 태그의 id로 지정
    const dropzone = new Dropzone("#fileDropzone", {
            url               : dropzoneURL, //업로드할 url (ex)컨트롤러)
            headers           : {
                // 요청 보낼때 헤더 설정
                'X-CSRF-TOKEN': csrfToken,
            },
            autoProcessQueue  : false, // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
            clickable         : true, // 클릭가능여부
            dictDefaultMessage: "<i class=\"fa-solid fa-download\"></i><br/>파일을 여기에 드래그하거나 클릭하여 업로드하세요", // 기본 텍스트
            thumbnailHeight   : 90, // Upload icon size
            thumbnailWidth    : 90, // Upload icon size
            maxFiles          : 5, // 업로드 파일수
            maxFilesize       : 10, // 최대업로드용량 : 10MB
            parallelUploads   : 5, // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
            addRemoveLinks    : true, // 삭제버튼 표시 여부
            dictRemoveFile    : '삭제', // 삭제버튼 표시 텍스트
            uploadMultiple    : true, // 다중업로드 기능
            init              : function () {
                const myDropzone = this;
                this.on('sending', function (file, xhr, formData) {
                    const boardType = document.querySelector('select[name="boardType"]').value;
                    const title = document.querySelector('input[name="title"]').value;
                    const contents = document.querySelector('input[name="contents"]').value;
                    formData.set('boardType', boardType);
                    formData.set('title', title);
                    formData.set('contents', contents);
                    formData.set('uuidPath', uuidPath)
                    if (updateNo !== undefined) {
                        formData.set('no', updateNo);
                    }
                })

                // 파일이 업로드되면 실행
                this.on('addedfile', function (file) {
                    const extension = ['jpg', 'jpeg', 'png', 'pdf'];
                    let check = false;
                    // 파일 확장자 및 사이즈 검사
                    this.files.forEach(file => {
                        const fileExtension = file.name.split('.').pop();
                        const fileSize = Math.trunc((file.size / 1024 / 1024) * 100) / 100
                        check = extension.includes(fileExtension);
                        if (!check) {
                            alert('올바른 파일 확장자가 아닙니다.\n * 사용가능한 확장자명 : JPG, JPEG, PNG, PDF');
                            this.removeFile(file);
                        } else if (fileSize > 10) {
                            alert('허용가능한 파일 용량을 초과하여 업로드하였습니다! ');
                            this.removeFile(file);
                        }
                    })
                    // 중복된 파일의 제거
                    if (this.files.length) {
                        // -1 to exclude current file
                        let hasFile = false;
                        for (let i = 0; i < this.files.length - 1; i++) {
                            if (
                                this.files[i].name === file.name &&
                                this.files[i].size === file.size &&
                                this.files[i].lastModifiedDate.toString() === file.lastModifiedDate.toString()
                            ) {
                                hasFile = true;
                                alert('이미 존재하는 파일입니다!');
                                this.removeFile(file);
                            }
                        }
                    } else {
                        addedFiles.push(file);

                    }

                });

                document.getElementById('confirm-btn').addEventListener('click', function (e) {
                    if (titleCheck.value.trim() === '' || titleCheck.value === undefined || titleCheck.value === null) {
                        alert('제목을 작성하여주세요!');
                        return;
                    }
                    if (myDropzone.getRejectedFiles().length > 0) {
                        let files = myDropzone.getRejectedFiles();
                        alert('거부된 파일이 있습니다 제거 후 등록해주세요')
                        return;
                    } else {
                        const check = confirm('정말 작성하시겠습니까?');
                        if (check && myDropzone.files.length > 0) {
                            e.preventDefault();
                            e.stopPropagation();

                            myDropzone.processQueue();
                        } else if (check && myDropzone.files.length <= 0) {
                            document.querySelector('input[name="uuidPath"]').value = uuidPath;
                            form.submit();
                        } else {
                            return;
                        }
                    }
                })
                this.on("maxfilesexceeded", function (file) {
                    // 파일등록개수 초과
                    alert('최대 업로드 파일 수는 5개 입니다.');
                    this.removeFile(file);
                });

                this.on('success', function (file, responseText) {
                    location.href = '/community/notice'
                });

                this.on('error', function (file, errorMessage) {

                    // if (this.files.length > 5) {
                    //     alert('파일 허용개수를 초과하였습니다!');
                    //     const excessFiles = this.files.slice(5);
                    //     for (const excessFile of excessFiles) {
                    //         this.removeFile(excessFile);
                    //     }
                    // }
                });

            }

        }
    );
}

// 수정하기로 왔을때
notice_update()

// 수정하기 폼 생성
function update_write(item) {
    formBox.innerHTML = '';
    formBox.insertAdjacentHTML('beforeend',
        `<form action="/community/notice/update" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="uuidPath">
                <div id="board-type">
                    <select name="boardType">
                        <option>공지사항</option>
                    </select>
                </div>
                <div id="board-header">
                    <input type="text" name="title" placeholder="제목을 입력하세요" value="${item.title}"></br>
                    <div class="dropzone" id="fileDropzone">
                            <div class="fallback">
                                <input type="file" name="files" multiple />
                            </div>
                        </div>
                </div>
                <div id="editor">
                    ${item.contents}
                </div>
                <input type="hidden" id="quill_html" name="contents" value="">
                <input type="hidden" name="_csrf" value="${csrfToken}"/>
                <input type="hidden" name="no" value="${updateNo}">
                <div id="btn">
                    <input type="button" value="작성하기" id="confirm-btn">
                    <input type="button" value="취소하기" onclick="back_notice()">
                </div>
                </form>`)
    quill_editor_init()
    dropzoneURL = '/community/notice/update';
    uuidPath = item.uuidPath;
    form = document.forms.item(0);
    dropzone_init()
    titleCheck = document.getElementById('board-header').firstElementChild;
}

function back_notice() {
    // quill 이미지 존재 확인
    const images = [...document.getElementById('editor').getElementsByTagName('img')];

    const check = confirm('정말 취소하시겠습니까?');
    if (images.length > 0 && check && updateNo === undefined) {
        fetch('/board/quillDelete', {
            method : 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body   : JSON.stringify({
                'uuidPath': uuidPath
            })
        }).then(value => value.text())
            .then(value => {
                console.log('취소하기 파일디렉토리 삭제 결과 : ' + value)
                location.href = '/community/notice'
            })
            .catch(reason => {
                console.log('취소하기 오류 파일 디렉토리 삭제 오류 : ' + reason)
            });
    } else if (check) {
        location.href = '/community/notice';
    } else {
        return;
    }
}


// 수정 요청
function notice_update() {
    if (updateNo != undefined) {
        const url = '/community/notice/update/write/' + updateNo;
        fetch(url)
            .then(value => value.json())
            .then(value => {
                update_write(value)
            })
            .catch(reason => {
                console.log(reason)
            })
    }
}

// 업로드 파일 체크
fileInput.addEventListener('change', (event) => {

});

// 작성여부 체크
function notice_write_check() {
    if (titleCheck.value.trim() === '' || titleCheck.value === undefined || titleCheck.value === null) {
        alert('제목을 작성하여주세요!');
        return;
    } else {
        const check = confirm('정말 작성하시겠습니까?');
        if (check) {
        }
    }
}