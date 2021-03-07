function attachEvents() {
    let ul = document.getElementById('phonebook');
    let loadBtn = document.getElementById('btnLoad');
    let person = document.getElementById('person');
    let phone = document.getElementById('phone');
    let createButton = document.getElementById('btnCreate');

    const click = new Event('click')
    loadBtn.addEventListener('click',async (e)=>{
        ul.innerHTML = '';
        let response = await fetch('http://localhost:3030/jsonstore/phonebook');
        let data = await response.json();
        let phones = Object.entries(data);
        console.log(phones);
        phones.map((x)=>{
            let li = document.createElement('li');
            li.textContent = x[1].person +':'+x[1].phone;
            let delBtn = document.createElement('button');
            delBtn.textContent = 'Delete';
            li.appendChild(delBtn);
            ul.appendChild(li);
            delBtn.addEventListener('click',async(e)=>{
                let key = x[0];
                let req = await fetch(`http://localhost:3030/jsonstore/phonebook/${key}`,{
                    method: 'delete'
                })
                ul.removeChild(li);
            })
        })
    })

    createButton.addEventListener('click',async (e)=>{

        let entry = {
            person: person.value,
            phone: phone.value
        };
        console.log(entry);
        await fetch('http://localhost:3030/jsonstore/phonebook',{
            method: 'post',
            body: JSON.stringify(entry)
        })
        loadBtn.dispatchEvent(click);
        person.value = '';
        phone.value = '';
    })
}

attachEvents();