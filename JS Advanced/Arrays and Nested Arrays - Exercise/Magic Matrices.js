function magic(arr) {
    let rowSum =[];
    let colSum = [];
    
    for (let row = 0; row < arr.length; row++) {
      let curRow = arr[row];
      rowSum.push(curRow.reduce((a,c)=>a+c,0));
    }
    for (let col = 0; col < arr.length; col++) {
      let curColSum = 0;
      for (let row = 0; row < arr.length; row++) {
      curColSum += arr[row][col];
      }
      colSum.push(curColSum);
    }
    console.log(rowSum.concat(colSum).every((c,i,ar)=>c===ar[0]));
    
    }