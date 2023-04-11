const headerList = document.getElementById('header-list');
const headerListDetails = document.getElementById('header-list-details');

/*** 헤더 상세메뉴 표시 이벤트 ***/
headerList.addEventListener('mouseover', () => {
    headerListDetails.style.opacity = '100%';
    headerListDetails.style.height = '13em';
})
headerList.addEventListener('mouseout', () => {
    headerListDetails.style.opacity = '0';
    headerListDetails.style.height = '0';
})
headerListDetails.addEventListener('mouseover', () => {
    headerListDetails.style.opacity = '100%';
    headerListDetails.style.height = '13em';
})
headerListDetails.addEventListener('mouseout', () => {
    headerListDetails.style.opacity = '0';
    headerListDetails.style.height = '0';
})