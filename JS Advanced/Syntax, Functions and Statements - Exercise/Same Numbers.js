function integer(a) {
    let arr = String(a).split('');
    let control = Number(arr[0]);
    let sum = 0;
    let res = true;
    for (let digit of arr) {
        if (Number(digit) != control) {
            res = false;
        }
      sum += Number(digit);
    }
    console.log(res);
    console.log(sum);
}