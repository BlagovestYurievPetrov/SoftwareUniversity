function diagonalSum(matrix) {
    let sumMain =0;
    let sumSecond = 0;
    for (let i = 0; i < matrix.length; i++) {
      const element = matrix[i];
      sumMain += element[i];
      sumSecond += element[element.length-1-i];
    }
    console.log(`${sumMain} ${sumSecond}`);
  }