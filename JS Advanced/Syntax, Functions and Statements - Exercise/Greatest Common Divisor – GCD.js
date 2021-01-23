function ngok(a,b) {
    let min = Math.min(a,b);
    let max = Math.max(a,b);
    let res = 0;

    for (let i = min; i>0; i--) {
        if (max%i ==0 && min%i ==0 ) {
            res = i;
            break;
        }
    }

    console.log(res);
 
}