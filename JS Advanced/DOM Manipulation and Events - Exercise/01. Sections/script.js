function create(words) {
   let content = document.getElementById('content');
  words.map((x)=>{
     let div = document.createElement('div');
     let p = document.createElement('p');
     p.textContent = x;
     p.style.display = 'none';
     div.appendChild(p);
     content.appendChild(div);
  })
   content.addEventListener('click', (e) => {
      if(e.target.tagName === 'DIV'||e.target.tagName === 'P'){
         const para = e.target.children[0];
         e.target.style.display = '';
         const isVisibe = para.style.display === 'block';
         para.style.display = isVisibe ? 'none' : 'block';
      }
   });

}

