import {cats} from './catSeeder.js';
import {html, render} from 'https://unpkg.com/lit-html?module';

let catTemp = (cat) =>html`
<li>
    <img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap">
    <div class="info">
        <button class="showBtn">Show status code</button>
        <div class="status" style="display: none" id="${cat.id}">
            <h4>Status Code: ${cat.statusCode}</h4>
            <p>${cat.statusMessage}</p>
        </div>
    </div>
</li>
`

let allCats = document.getElementById('allCats');
let cards = cats.map(catTemp);
render(cards,allCats);

allCats.addEventListener('click',(e)=>{
    if (e.target.className == 'showBtn'){
        let info = e.target.parentNode;
        let status = info.querySelector('.status');
        status.style.display === 'none'?status.style.display = 'block':status.style.display = 'none';
        e.target.textContent === 'Show status code'? e.target.textContent = 'Hide status code' : e.target.textContent = 'Show status code';
    }
})



