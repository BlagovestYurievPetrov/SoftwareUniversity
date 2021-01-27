function sumTable() {
let table = document.querySelectorAll('table tr');
let counter  = 0;
for (let i = 1; i < table.length; i++){
    let cols = table[i].children;
    let cost = cols[cols.length - 1].textContent;
    counter += Number(cost);
}
document.getElementById('sum').textContent = counter;
}