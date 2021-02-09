function sum (arr,first,last){
    if (!Array.isArray(arr)){
        return NaN;
    }
    if (first < 0){
        first = 0;
    }


    return arr.slice(first, last+1).map(Number).reduce((a, c) => a + c, 0);

}