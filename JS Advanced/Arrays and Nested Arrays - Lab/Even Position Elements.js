function even(arr) {
    let res = '';
    for (let i = 0; i < arr.length; i++) {
      if (i % 2 == 0) {
        res += arr[i];
        res += ' ';
      }
    }
    return res;
  }
  