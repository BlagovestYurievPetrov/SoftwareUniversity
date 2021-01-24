function addAndRemove(arr) {
    let num = 0;
    let res =[];
    for (let i = 0; i < arr.length; i++) {
      num++;
      const element = arr[i];
      if (element === 'add') {
        res.push(num);
      }
      if (element === 'remove') {
        res.pop(num);
      }
    }
    if (res.length===0){
      console.log('Empty');
    } else {
    for (let index = 0; index < res.length; index++) {
      const element = res[index];
      console.log(element);
      
    }
  }
  }