function extract(arr) {
    let res = [];
    let max = Number.NEGATIVE_INFINITY;
    if (arr[0]>=max){
    res.push(arr[0]);
      max = arr[0];
    }
    for (let i = 1; i < arr.length; i++) {
      if (arr[i]>=max){
        res.push(arr[i]);
        max = arr[i];
      }
    }
    return res;
  }