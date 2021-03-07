function attachEvents() {
    let texts = document.getElementById('messages');
    let inputs = document.querySelectorAll('input');
    
    let submitBtn = document.getElementById('submit');
    let refreshBtn = document.getElementById('refresh');
    
    submitBtn.addEventListener('click', async (e)=>{
        let author = inputs[0].value;
        let content = inputs[1].value;
        let comment = {author,content}
        await fetch('http://localhost:3030/jsonstore/messenger',{
            method: 'post',
            body: JSON.stringify(comment)
        })
        texts.textContent += author + ': ' + content + '\n';
        alert('You have successfully published your comment! In order to see all comments please click on the refresh button.');
    })
    
    refreshBtn.addEventListener('click',async (e)=>{
        texts.textContent = '';
        let response = await fetch('http://localhost:3030/jsonstore/messenger');
        let data = await response.json();
        let allComments = Object.entries(data);
        allComments.map((x)=>{
            texts.textContent += x[1].author + ': ' + x[1].content + '\n';
        })
    })
}

attachEvents();