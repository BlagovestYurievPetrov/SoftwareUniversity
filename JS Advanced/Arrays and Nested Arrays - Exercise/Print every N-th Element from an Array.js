function printNElement(arr, step) {
    const arrRe = [];
    for (let i = 0; i < arr.length; i++) {
      if (i % step === 0) {
        arrRe.push(arr[i]);
      }
    }
    return arrRe;
  }
  