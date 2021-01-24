function oddPositions(arr) {
    let arr2 = [];
    for (let i = 0; i < arr.length; i++) {
     if(i%2!==0){
        arr2.push(arr[i]*2);
     }
    }
    arr2.reverse();
    return arr2;
  }