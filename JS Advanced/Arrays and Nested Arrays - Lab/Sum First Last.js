function sumFirstLast(arr) {
    let res = 0;
    if(arr.length === 1){
      res = Number(arr.pop());
    }
    if(arr.length > 1){
      res = Number(arr.pop()) + Number(arr.shift());
    }
    return res;
  }
  