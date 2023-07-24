const headerListTitle = document.getElementsByClassName('header-list-title');
const headerDetailList =[...document.getElementsByClassName('header-detail-list')];
function show_header_list(e) {
    e.querySelector('ul').style.display = "block";
}

function hide_header_list(e) {
    e.querySelector('ul').style.display = "none";
}

/*** 헤더 메뉴 보여주기 ***/
for (let list of headerListTitle) {
    list.addEventListener('mouseover', () => {
        show_header_list(list);
    });
    list.addEventListener('mouseout', () => {
        hide_header_list(list);
    });
}
for (let list of headerDetailList) {
    list.addEventListener('mouseover', () => {
        list.style.display = "block";
    })
    list.addEventListener('mouseout', (e) => {
        list.style.display = "none";
    })
}
const headerListToggle = document.getElementById('header-list');
const headerToggleBtn = document.getElementById('header-menu-toggle').firstElementChild;
let toggleCheck = false;
headerToggleBtn.addEventListener('click', () => {
    toggleCheck = !toggleCheck;
    toggleCheck ? headerListToggle.classList.add('toggle-active') : headerListToggle.classList.remove('toggle-active');
    for (let list of headerDetailList) {
        list.remove();
    }
})