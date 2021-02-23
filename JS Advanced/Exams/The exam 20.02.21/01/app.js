function solve(){
   let creator = document.getElementById('creator');
   let title = document.getElementById('title');
   let cat = document.getElementById('category');
   let content = document.getElementById('content');
   let allButtons = document.querySelectorAll('button');
   let creBtn = allButtons[allButtons.length-1];
   let Allsection = document.querySelectorAll('section');
   let section = Allsection[1];
   let ol = document.querySelector('ol');


   // vuzmojno e butona da se bugne
   // classname ??
   creBtn.addEventListener('click', e=>{
      e.preventDefault();
      let art = document.createElement('article');
      let h1 = document.createElement('h1');
      h1.textContent = title.value;
      let catP = document.createElement('p');
      catP.textContent = 'Category:';
      let catStrong = document.createElement('strong');
      catStrong.textContent = cat.value;
      catP.appendChild(catStrong);
      let creaP = document.createElement('p');
      creaP.textContent = 'Creator:';
      let creaStrong = document.createElement('strong');
      creaStrong.textContent = creator.value;
      creaP.appendChild(creaStrong);
      let contentP = document.createElement('p');
      contentP.textContent = content.value;
      let btnDiv = document.createElement('div');
      btnDiv.className = 'buttons';
      let dltBtn = document.createElement('button');
      dltBtn.className = 'btn delete';
      dltBtn.textContent = 'Delete';
      btnDiv.appendChild(dltBtn);
      let archBtn = document.createElement('button');
      archBtn.className = 'btn archive';
      archBtn.textContent = 'Archive';
      btnDiv.appendChild(archBtn);

      art.appendChild(h1);
      art.appendChild(catP);
      art.appendChild(creaP);
      art.appendChild(contentP);
      art.appendChild(btnDiv);
      section.appendChild(art);
      
      dltBtn.addEventListener('click',e=>{
         art.remove();
      })

      archBtn.addEventListener('click', e=>{
         // try with append child if problems
         let archLi = document.createElement('li');
         archLi.textContent = h1.textContent;
         art.remove();
         ol.appendChild(archLi);

         let allLis = ol.querySelectorAll('li');
         let allLisArr = [...allLis];
         allLisArr.sort((a, b)=> a.textContent.localeCompare(b.textContent));
         allLisArr.forEach(u =>{
            ol.appendChild(u);
         })
      })
   })

  }
