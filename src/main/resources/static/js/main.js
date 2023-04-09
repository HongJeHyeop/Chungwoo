const mainImg = document.getElementById('main-img-items').children;
const imgChange = document.getElementById('img-num').children;

console.log(mainImg)
console.log(imgChange)
/*** 이미지 슬라이드 ***/

function img_changing(num, e) {
    for (let img of mainImg) {
        img.style.opacity = '0';
    }
    for (let changeBtn of imgChange) {
        changeBtn.style.background = 'rgba(255, 255, 255, 0.4)';
    }
    switch (num) {
        case 1:
            mainImg.item(0).style.opacity = '100%';
            e.style.background = 'rgba(255, 255, 255, 0.9)';
            break;
        case 2:
            mainImg.item(1).style.opacity = '100%';
            e.style.background = 'rgba(255, 255, 255, 0.9)';
            break;
        case 3:
            mainImg.item(2).style.opacity = '100%';
            e.style.background = 'rgba(255, 255, 255, 0.9)';
            break;
    }

}