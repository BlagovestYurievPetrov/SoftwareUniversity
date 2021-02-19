function solve() {
    let container = document.getElementById('container');
    let [name,hall,ticketPrice] = container.querySelectorAll('input');
    let onScrBtn = container.querySelector('button');
    let movies = document.getElementById('movies');
    let ul = movies.querySelector('ul');
    let archive = document.getElementById('archive');
    let ulAr = archive.querySelector('ul');
    let allArchiveBtns = archive.querySelectorAll('button');
    let clearButton = allArchiveBtns[allArchiveBtns.length-1];

    onScrBtn.addEventListener('click',e=>{
        e.preventDefault();
        if (isNaN(ticketPrice.value) || name.value==='' || hall.value==='' || ticketPrice.value===''){
            return;
        }
        let li = document.createElement('li');
        let span = document.createElement('span');
        span.textContent = name.value;
        let str1 = document.createElement('strong');
        str1.textContent = 'Hall: ' + hall.value;
        let div = document.createElement('div');
        let str2 = document.createElement('strong');
        // MOJE DA TRQBVA DA SE ZAKRUGLI DO VTORI ZNAK
        let forTicketPrice = Number(ticketPrice.value);
        str2.textContent = forTicketPrice.toFixed(2);
        let input = document.createElement('input');
        input.placeholder = 'Tickets sold'
        let archiveBtn = document.createElement('button');
        archiveBtn.textContent = 'Archive';
        div.appendChild(str2);
        div.appendChild(input);
        div.appendChild(archiveBtn);
        li.appendChild(span);
        li.appendChild(str1);
        li.appendChild(div);
        ul.appendChild(li);
        name.value = '';
        hall.value = '';
        let price = ticketPrice.value;
        ticketPrice.value = '';
        archiveBtn.addEventListener('click',e=>{
            if(isNaN(input.value)||input.value === ''){
                return;
            }
            str1.textContent = 'Total amount: ' + (price*input.value).toFixed(2);
            div.remove();
            let delBtn = document.createElement('button');
            delBtn.textContent = 'Delete';
            li.appendChild(delBtn);
            ulAr.appendChild(li);
            delBtn.addEventListener('click',e=>{
                li.remove();
            })
            clearButton.addEventListener('click',e=>{
                ulAr.innerHTML = '';
            })
    
        })
        
    })
       
}