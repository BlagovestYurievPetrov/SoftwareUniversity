function area(str1 ) {
    if(typeof(str1)==='number'){
   console.log((Math.PI*str1**2).toFixed(2));
    }else{
   console.log(`We can not calculate the circle area, because we receive a ${typeof(str1)}.`)
    }
   }