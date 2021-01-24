function lastNumbers(n, k) {
    let arr = [];
    arr[0] = 1;
    let elem = 0;
    for (let i = 1; i < n; i++) {
      if(k>i){
          for (let j = 0; j < i; j++) {
            elem += arr[j];
          }
          arr[i]=elem;
          elem = 0;
      } else {
        for (let m = i-k; m < i; m++) {
          elem += arr[m];
        }
        arr[i]=elem;
          elem = 0;
      }
    }
  
    return arr;
  }
  