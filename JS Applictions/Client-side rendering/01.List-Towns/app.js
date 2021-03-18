import {html, render} from 'https://unpkg.com/lit-html?module';

let root = document.getElementById('root');
let input = document.getElementById('towns');
let btn = document.getElementById('btnLoadTowns');

let temp = (x) => {
    return html`<li>${x}</li>`;
}

btn.addEventListener('click',(e)=>{
    e.preventDefault();
    let towns = input.value;
    let townsArr = towns.split(', ');
    let result = townsArr.map(temp);
    render(result,root);
})