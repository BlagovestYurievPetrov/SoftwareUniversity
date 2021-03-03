function solve() {
    let div = document.getElementById('info');
    let info = div.querySelector('span');
    let depBtn = document.getElementById('depart');
    let arrBtn = document.getElementById('arrive');
    let nextId = 'depot';

    async function depart() {
        let response = await fetch(`http://localhost:3030/jsonstore/bus/schedule/${nextId}`);
        let data = await response.json();
        info.textContent = `Next stop ${data.name}`;
        depBtn.disabled = true;
        arrBtn.disabled = false;
        console.log(data);
    }

    async function arrive() {
        let response = await fetch(`http://localhost:3030/jsonstore/bus/schedule/${nextId}`);
        let data = await response.json();
        info.textContent = `Arriving at ${data.name}`;
        depBtn.disabled = false;
        arrBtn.disabled = true;
        nextId = data.next;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();