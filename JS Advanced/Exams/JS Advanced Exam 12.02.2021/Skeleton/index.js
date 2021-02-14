function solve() {
  let fields = document.querySelectorAll('form input');
  let [lectName, date] = fields;
  let option = document.querySelector('form select');
  let modules = document.querySelector('div');
  
  let addBtn = document.querySelector('form button');
  addBtn.addEventListener('click',e =>{
      e.preventDefault();
    if(lectName.value === ''){
        return;
        }
    if(date.value === ''){
        return;
    }
    if (option.value === 'Select module'){
        return;
    }
    let modulesChildren = Array.from(modules.children);
    let doesModuleExist = modulesChildren.some((x)=>
    x.querySelector('h3').innerText === option.value.toUpperCase() + '-MODULE');
    // if this is the first lecture from this module
    if (!doesModuleExist){
    let lecture = document.createElement('div');
    lecture.className = 'module';
    let h = document.createElement('h3');
    h.innerText = option.value.toUpperCase() + '-MODULE';
    let ul = document.createElement('ul');
    let li = document.createElement('li');
    li.className = 'flex';
    let h4 = document.createElement('h4');
    let dateFirstSplit = date.value.split('-');
    let secSplitDate = dateFirstSplit[2].split('T');

    h4.innerText = lectName.value + ' - ' + dateFirstSplit[0] + '/' + dateFirstSplit[1] + '/' + secSplitDate[0] + ' - ' + secSplitDate[1];
    let delBtn = document.createElement('button');
    delBtn.className = 'red';
    delBtn.innerText = 'Del';
    
    delBtn.addEventListener('click',e =>{
        let currLi = e.currentTarget.parentNode;
        let currUl = e.currentTarget.parentNode.parentNode;
        let currModule = e.currentTarget.parentNode.parentNode.parentNode;
        currLi.remove();
        let childArr = Array.from(currUl.children);
            if(childArr.length===0){
                 currModule.remove();
            }
        // e.currentTarget.parentNode.remove();
    })
    let lists = document.querySelectorAll('ul');
       console.log(lists.length);
    li.appendChild(h4);
    li.appendChild(delBtn);
    ul.appendChild(li);
    lecture.appendChild(h);
    lecture.appendChild(ul);
    modules.appendChild(lecture);
    } else {
    // if module exists
        let moduleToAddTo = modulesChildren.find(x=>
            x.querySelector('h3').innerText === option.value.toUpperCase() + '-MODULE');
        let list = moduleToAddTo.querySelector('ul');
        let li = document.createElement('li');
        li.className = 'flex';
        let h4 = document.createElement('h4');
        let dateFirstSplit = date.value.split('-');
        let secSplitDate = dateFirstSplit[2].split('T');

        h4.innerText = lectName.value + ' - ' + dateFirstSplit[0] + '/' + dateFirstSplit[1] + '/' + secSplitDate[0] + ' - ' + secSplitDate[1];
        let delBtn = document.createElement('button');
        delBtn.className = 'red';
        delBtn.innerText = 'Del';
        delBtn.addEventListener('click',e =>{
            let currLi = e.currentTarget.parentNode;
            let currUl = e.currentTarget.parentNode.parentNode;
            let currModule = e.currentTarget.parentNode.parentNode.parentNode;
            currLi.remove();
            let childArr = Array.from(currUl.children);
            if(childArr.length===0){
                 currModule.remove();
            }
            
        })
        li.appendChild(h4);
        li.appendChild(delBtn);
        list.appendChild(li);
        }
        [...document.querySelectorAll('.modules ul')]
        .forEach(u => {
            let li = [...u.querySelectorAll('li')].sort((a, b) => {
 
                let startIndexA = a.textContent.indexOf(' ');
                let startIndexB = b.textContent.indexOf(' ');
                let tempA = a.textContent.substr(startIndexA);
                let tempB = b.textContent.substr(startIndexB);
 
                return tempA.localeCompare(tempB); })
                .forEach(l => u.appendChild(l));
        })
  })
       
}
