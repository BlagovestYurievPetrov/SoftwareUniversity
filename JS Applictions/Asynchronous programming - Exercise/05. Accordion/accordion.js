async function solve(){
    let response = await fetch('http://localhost:3030/jsonstore/advanced/articles/list');
    let dataJson = await response.json();
    let data = Object.values(dataJson);
    let main = document.getElementById('main');
    main.innerHTML = '';
    data.map(async (x)=>{
        let mainDiv = document.createElement('div');
        mainDiv.className = 'accordion';
        let headDiv = document.createElement('div');
        headDiv.className = 'head';
        let span = document.createElement('span');
        span.textContent = x.title;
        let button = document.createElement('button');
        button.className = 'button';
        button.id = x._id;
        button.textContent = 'More';

        let extraDiv = document.createElement('div');
        extraDiv.className = 'extra';
        let resp = await fetch(`http://localhost:3030/jsonstore/advanced/articles/details/${button.id}`);
        let respJson = await resp.json();
        let p = document.createElement('p');
        p.textContent = respJson.content;
        extraDiv.appendChild(p);
        extraDiv.style.display = 'none';
        button.addEventListener('click',async(e)=>{
            extraDiv.style.display === 'none'?extraDiv.style.display = 'block':extraDiv.style.display = 'none';
            button.textContent === 'More'?button.textContent = 'Less':button.textContent = 'More';
        })
        headDiv.appendChild(span);
        headDiv.appendChild(button);
        mainDiv.appendChild(headDiv);
        mainDiv.appendChild(extraDiv);
        main.appendChild(mainDiv);
    })
    
}

solve();