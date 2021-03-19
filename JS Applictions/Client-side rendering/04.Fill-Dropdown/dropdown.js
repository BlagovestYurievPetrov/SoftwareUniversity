import {html, render} from 'https://unpkg.com/lit-html?module';
let menu = document.getElementById('menu');
let temp = (data) => html`<option value="${data._id}">${data.text}</option>`;

async function renderItems(){
    let response = await fetch(`http://localhost:3030/jsonstore/advanced/dropdown`);
    let data = await response.json();
    let result = Object.values(data);
    render(result.map(temp),menu);
}

let itemText = document.getElementById('itemText');
let form = document.querySelector('form');
form.addEventListener('submit',addItem);

async function addItem() {
    let town = {
        text: itemText.value
    }
    let response = await fetch(`http://localhost:3030/jsonstore/advanced/dropdown`,{
        method: 'post',
        body: JSON.stringify(town)
    });
    renderItems();
}

renderItems();