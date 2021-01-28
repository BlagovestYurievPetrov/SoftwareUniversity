function toggle() {
    let btn = document.querySelector('.button');
    let extra = document.querySelector('#extra');
    extra.style.display = (extra.style.display == 'none' || extra.style.display == '') ? 'block' : 'none';
    btn.textContent = btn.textContent == 'More' ? 'Less' : 'More';
}