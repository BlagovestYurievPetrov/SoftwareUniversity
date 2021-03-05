async function attachEvents() {
    let loadBtn = document.getElementById('btnLoadPosts');
    let viewBtn = document.getElementById('btnViewPost');
    let posts = document.getElementById('posts');
    let postBody = document.getElementById('post-body');
   let ul = document.getElementById('post-comments');
   let reqComments = await fetch(`http://localhost:3030/jsonstore/blog/comments`);
   let commentStream = await reqComments.json();
   let comments = Object.entries(commentStream);
   console.log(comments);
   loadBtn.addEventListener('click',async (e)=>{
       let loadResponse = await fetch(`http://localhost:3030/jsonstore/blog/posts`);
       let loadData = await loadResponse.json();
       let data = Object.entries(loadData);
       data.map((x)=>{
            let opt = document.createElement('option');
            opt.value = x[0];
            opt.textContent = x[1].title;
            posts.appendChild(opt);
        })
        viewBtn.addEventListener('click',async(e)=>{
            postBody.innerHTML = '';
            ul.innerHTML = '';
        let req = await fetch(`http://localhost:3030/jsonstore/blog/posts/${posts.value}`);
        let reqData = await req.json();
        let title = document.getElementById('post-title');
        title.textContent = reqData.title;
        postBody.textContent = reqData.body;
        comments.map((x)=>{
            // sravni id-tata na komentarite s tezi na posta i gi zakachi kato lita v ula

            
            if (x[1].postId === reqData.id){
               let li = document.createElement('li');
               li.textContent = x[1].text;
               ul.appendChild(li);
            }


        })
    })
   })

   

}

attachEvents();