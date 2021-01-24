function rotate(arr,n) {
    let res = [];
    for (let index = 0; index < arr.length; index++) {
      const element = arr[index];
      res.push(element);
      
    }
    for (let i = 0; i < n; i++) {
      res.unshift(res.pop());
    }
    console.log(res.join(' '));
  }