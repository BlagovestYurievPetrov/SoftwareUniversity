function sumBetweenNumbers(n1str, n2str) {
    const n1 = Number(n1str);
    const n2 = Number(n2str);
    let sum = 0;
    for (let i = n1; i <= n2; i++) {
      sum += i;
    }
    console.log(sum);
  }
  