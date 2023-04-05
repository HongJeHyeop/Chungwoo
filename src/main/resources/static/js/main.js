const mainImg = document.getElementById('main-img-items');
const imgLeftBtn = document.getElementsByClassName('fa-angle-left').item(0);
const ingRightBtn = document.getElementsByClassName('fa-angle-right').item(0);


/*** 이미지 슬라이드 ***/
imgLeftBtn.addEventListener('click', () => {
    mainImg.style.transform += 'translateX(-100vw)';
    console.log(mainImg)
})
ingRightBtn.addEventListener('click', () => {
    mainImg.style.transform += 'translateX(100vw)';
    console.log(mainImg.style.transform)
})