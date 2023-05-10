const { jsPDF } = window.jspdf;
const doc = new jsPDF({
    orientation: "p", // p: 가로(기본), l: 세로
    unit: "mm", // 단위 : "pt" (points), "mm", "cm", "m", "in" or "px" 등)
    format: "a4", // 포맷 (페이지 크기).
});
const a = document.getElementById('a');
const btn = document.getElementById('btn-box').firstElementChild;
html2canvas(a).then(canvas => {
    let imgData = canvas.toDataURL('image/png');

    let imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm
    let pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
    let imgHeight = canvas.height * imgWidth / canvas.width;
    let heightLeft = imgHeight;
    let margin = 10; // 출력 페이지 여백설정
    let position = 0;
    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
    btn.addEventListener('click', () => {
        doc.save('test')
    })
});


