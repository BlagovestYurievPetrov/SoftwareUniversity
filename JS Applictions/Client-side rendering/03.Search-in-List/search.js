import {towns} from './towns.js';
import {html, render} from 'https://unpkg.com/lit-html?module';

let temp = (town) => html`<li>${town}</li>`;
let townsList = document.getElementById('towns');
render(towns.map(temp),townsList);

let btn = document.querySelector('button');
let input = document.getElementById('searchText');
let result = document.getElementById('result');

btn.addEventListener('click',search);
let townsDom= document.querySelectorAll('li');
let lis = Array.from(townsDom);


function search() {
   let counter = 0;
   lis.map((x)=>{
      x.className='';
   })
   if(input.value !==''){
   lis.map((x)=>{
      if(x.textContent.toLowerCase().includes(input.value.toLowerCase())){
         counter++;
         x.className = 'active';
      }
   })
}
   result.innerText = `${counter} ${counter === 1 ? 'match found.': 'matches found.'}`;
   input.value = '';
}
