function addItem() {
    let newItemText = document.getElementById('newItemText').value;
    let newItemValue = document.getElementById('newItemValue').value;
    let creation = document.createElement('option');
    creation.textContent = newItemText;
    creation.value = newItemValue;
    document.getElementById('menu').appendChild(creation);
    document.getElementById('newItemText').value = '';
    document.getElementById('newItemValue').value = '';
}