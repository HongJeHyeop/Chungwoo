const mainImg = document.getElementById('main-img-items');
const imgLeftBtn = document.querySelector('#arrows').firstElementChild;
const imgRightBtn = document.querySelector('#arrows').lastElementChild;

console.log(imgRightBtn)
console.log(imgLeftBtn)
console.log(mainImg)
/*** 이미지 슬라이드 ***/
imgLeftBtn.addEventListener('click', () => {
    mainImg.style.transform += 'translateX(-100vw)';
    console.log(mainImg)
    console.log("이동");
})
imgRightBtn.addEventListener('click', () => {
    mainImg.style.transform += 'translateX(100vw)';
    console.log(mainImg.style.transform)
    console.log("이동");
})