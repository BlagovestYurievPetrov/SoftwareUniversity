function negativePositive(arr) {
    let arr2 = [];
    arr.forEach(element => {
      if(element>-1){
        arr2.push(element);
      } else {
        arr2.unshift(element);
      }
    });
    return arr2;
  }
  