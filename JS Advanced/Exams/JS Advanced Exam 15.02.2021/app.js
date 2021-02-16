function solution() {
   let name = document.querySelector('input');
   let addBtn = document.querySelector('button');
   let lists = document.querySelectorAll('ul');
   let list = lists[0];
   let sent = lists[1];
   let discarded = lists[2];

   addBtn.addEventListener('click',e=>{
       e.preventDefault();
       let li = document.createElement('li');
       li.className = 'gift';
       let sendBtn = document.createElement('button');
       sendBtn.className = 'sendButton';
       sendBtn.textContent = 'Send';
       let discardBtn = document.createElement('button');
       discardBtn.className = 'discardButton';
       discardBtn.textContent = 'Discard';
       li.textContent = name.value;
       li.appendChild(sendBtn);
       li.appendChild(discardBtn);
       list.appendChild(li);
       name.value = '';
       let lis = [...list.querySelectorAll('li')].sort((a,b)=>{
        return a.textContent.localeCompare(b.textContent);
       }).map((x)=>{
           list.appendChild(x);
       })
       sendBtn.addEventListener('click',e =>{
           li.removeChild(sendBtn);
           li.removeChild(discardBtn);
           sent.appendChild(li);
       })
       discardBtn.addEventListener('click',e=>{
            li.removeChild(sendBtn);
            li.removeChild(discardBtn);
           discarded.appendChild(li);
       })
   })
}