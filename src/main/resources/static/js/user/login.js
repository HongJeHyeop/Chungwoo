const id = document.getElementById('user-input').firstElementChild;
const pw = id.nextElementSibling;

document.querySelector('h1').addEventListener('click', () => {

console.log(id.value, pw.value);
})
