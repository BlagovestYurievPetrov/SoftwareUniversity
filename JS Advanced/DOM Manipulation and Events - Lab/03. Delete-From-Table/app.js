function deleteByEmail() {
    let input = document.querySelector('input[name="email"]').value;
    let table = Array.from(document.querySelectorAll('tbody tr'));
    let deleted = false;
    table.map((x)=>{
        if (x.children[1].textContent==input){
            x.remove();
            deleted = true;
        } 
    })
    document.getElementById('result').textContent = deleted === false ? 'Not found.' : 'Deleted.';
}