function sortNumbers(input){
    const len = input.length;
  let sorted = input.sort((a,b) => b-a);
    let arr = [];
        for (let i = 0; i<len; i++){
            if (i%2===0){
            arr.push(sorted.pop());
            }else{
            arr.push(sorted.shift());
            }
    }
  
  return arr;
  }