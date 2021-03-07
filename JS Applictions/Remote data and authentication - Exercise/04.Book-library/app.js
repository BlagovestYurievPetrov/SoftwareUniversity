function work(){
    let loadBtn = document.getElementById('loadBooks');
    let tbody = document.getElementById('tbody');
    let title = document.getElementById('title');
    let author = document.getElementById('author');
    let form = document.getElementById('form');
    let h3 = form.querySelector('h3');
    let submitBtn = document.getElementById('submit');
    let click = new Event('click');
    let editForm = document.getElementById('editform');
    editForm.style.display = 'none';


    form.addEventListener('submit', async e=>{
        e.preventDefault()
        if (!title.value||!author.value){
            return alert('All fields should be filled!');
        }
        createBook();
        
        loadBtn.dispatchEvent(click);
        author.value = '';
        title.value = '';
    });

    async function loadBooks(){
        tbody.innerHTML = '';
        let response = await fetch('http://localhost:3030/jsonstore/collections/books');
        let data = await response.json();
        let books = Object.entries(data);
        console.log(books);
        books.map((x)=>{
            let tr = document.createElement('tr');
            let td = document.createElement('td');
            let td1 = document.createElement('td');
            let td2 = document.createElement('td');
            let editBtn = document.createElement('button');
            let delBtn = document.createElement('button');
            
            let rowId = x[0];
            console.log(rowId);
            td.textContent = x[1].title;
            td1.textContent = x[1].author;
            editBtn.textContent = 'Edit';
            delBtn.textContent = 'Delete';
            td2.appendChild(editBtn);
            td2.appendChild(delBtn);
            tr.appendChild(td);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tbody.appendChild(tr);
            delBtn.addEventListener('click',async (e)=>{
                console.log(rowId);
                await fetch(`http://localhost:3030/jsonstore/collections/books/${rowId}`,{
                    method: 'delete'
                })
                tr.remove();
            })
            editBtn.addEventListener('click',async (e)=>{
                editForm.style.display = 'block';
                form.style.display = 'none';
                editBtn.setAttribute('rowId',`${rowId}`);
                editForm.addEventListener('submit',async(e)=>{
                    // podmqna na kniga
                    e.preventDefault();
                    let editTitle = document.getElementById('edittitle');
                    let editAuthor = document.getElementById('editauthor');
                    if (!editTitle.value||!editAuthor.value){
                        return alert('All fields should be filled!');
                    }
                    let book = {
                    title: editTitle.value,
                    author: editAuthor.value
                    }
                    await fetch(`http://localhost:3030/jsonstore/collections/books/${rowId}`,{
                    method: 'put',
                    body: JSON.stringify(book)
                    })
                    td.textContent = editTitle.value;
                    td1.textContent = editAuthor.value;
                    editAuthor.value = '';
                    editTitle.value = '';
                })
            })
        })
    }
    loadBtn.addEventListener('click', loadBooks);
    async function createBook(){
        let book = {
            title: title.value,
            author: author.value
        }
        await fetch(`http://localhost:3030/jsonstore/collections/books`,{
            method: 'post',
            body: JSON.stringify(book)
        })
    }
    loadBooks();
}
work();