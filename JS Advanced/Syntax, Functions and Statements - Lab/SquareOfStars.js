function starSquare(input = 5) {
    let str = '';
        for (let i = 0; i < input; i++) {
            str = str.concat('* ')
        }
        for (let i = 0; i < input; i++) {
            console.log(str);
         }
  }