function solve() {
  const input = document.getElementById('input').value;
  let output = document.getElementById('output');
  let sentences = input.split('.');
  const lastEmpty = sentences.pop();
  let arr = Array.from(sentences);
  const paraCount = Math.ceil(arr.length/3);
  console.log(paraCount);
 
  for (let i = 0; i < paraCount; i++){
    let para = document.createElement("p");
    let node = '';
    let node1 = '';
    let node2 = '';
    if (arr.length >= 3){
      node = document.createTextNode(arr.shift() + '.');
      node1 = document.createTextNode(arr.shift() + '.');
      node2 = document.createTextNode(arr.shift() + '.');
      para.appendChild(node);
      para.appendChild(node1);
      para.appendChild(node2);
    } else if (arr.length == 2){
       node = document.createTextNode(arr.shift() + '.');
       node1 = document.createTextNode(arr.shift() + '.');
       para.appendChild(node);
       para.appendChild(node1);

    } else if (arr.length == 1){
       node = document.createTextNode(arr.shift() + '.');
       para.appendChild(node);
    }
    
    output.appendChild(para);
  }


}