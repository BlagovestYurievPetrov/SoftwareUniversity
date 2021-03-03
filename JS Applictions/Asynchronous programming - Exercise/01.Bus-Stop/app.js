async function getInfo() {
    let input = document.getElementById('stopId').value;
    let stopName = document.getElementById('stopName');
    let ul = document.getElementById('buses');
    let url = `http://localhost:3030/jsonstore/bus/businfo/${input}`;

    
    if (!Number(input)){
        alert('Invalid input! Please type a number for a bus stop.');
    }
    try{
        let response = await fetch(url);
        let data = await response.json();
        stopName.textContent = data.name;
        console.log(data);
        Object.entries(data.buses).map(([bus,times])=>{
            let li = document.createElement('li');
            li.textContent = `Bus ${bus} arrives in ${times}`
            ul.appendChild(li);
        })
    }catch(err){
        ul.innerHTML ='';
        stopName.textContent = 'Error';
    }   
    
}