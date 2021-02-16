function solve() {
   let task = document.getElementById('task');
   let desc = document.getElementById('description');
   let date = document.getElementById('date');
   let addBtn = document.getElementById('add');
   addBtn.addEventListener('click',e =>{
       e.preventDefault();
       
       if (task.value===''||desc.value===''||date.value===''){
           return;
       }
    //    console.log(task.value);
    //    console.log(desc.value);
    //    console.log(date.value);
       let divs = document.querySelectorAll('div');
       let open = divs[4];
       let a = document.createElement('article');
       let h3 = document.createElement('h3');
       h3.textContent = task.value;
       let p = document.createElement('p');
       p.textContent = `Description: ${desc.value}`;
       let p2 = document.createElement('p');
       p2.textContent = `Due Date: ${date.value}`;
       let currDiv = document.createElement('div');
       currDiv.setAttribute('class','flex');
       let grBtn = document.createElement('button');
       grBtn.setAttribute('class','green');
       grBtn.textContent = 'Start';
       let redBtn = document.createElement('button');
       redBtn.setAttribute('class', 'red');
       redBtn.textContent = 'Delete';
       
       currDiv.appendChild(grBtn);
       currDiv.appendChild(redBtn);
       a.appendChild(h3);
       a.appendChild(p);
       a.appendChild(p2);
       a.appendChild(currDiv);
       open.appendChild(a);
       grBtn.addEventListener('click', e=>{
        let inProgress = document.getElementById('in-progress');
        let article = e.currentTarget.parentNode.parentNode;
        let nextDiv = document.createElement('div');
        let secondRedBtn = document.createElement('button');
        secondRedBtn.setAttribute('class', 'red');
        secondRedBtn.textContent = 'Delete';
        let orgButton = document.createElement('button');
        orgButton.setAttribute('class', 'orange');
        orgButton.textContent = 'Finish';
        nextDiv.appendChild(secondRedBtn);
        nextDiv.appendChild(orgButton);
        //e.currentTarget.parentNode = nextDiv;
        article.appendChild(nextDiv);
        inProgress.appendChild(article);
        //e.currentTarget.parentNode = nextDiv;
           
        //      e.currentTarget.parentNode.appendChild(secondRedBtn);
        //   nextDiv.appendChild(orgButton);
        //    e.currentTarget.parentNode.remove();
        //    e.currentTarget.parentNode.parentNode.appendChild(nextDiv);
            secondRedBtn.addEventListener('click',e=>{
                e.currentTarget.parentNode.parentNode.remove();
            });
            orgButton.addEventListener('click',e=>{
                let lastDiv = divs[divs.length-1];
                lastDiv.appendChild(e.currentTarget.parentNode.parentNode);
                e.currentTarget.parentNode.remove();
            })

       })
       redBtn.addEventListener('click', e=>{
           e.currentTarget.parentNode.parentNode.remove();
    })
   })
}