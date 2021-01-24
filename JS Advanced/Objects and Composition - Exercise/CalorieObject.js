function calories(inArr){
    let result = {};
    for (let i = 0; i<inArr.length; i+=2){
        result[inArr[i]] = Number(inArr[i+1]);
    }
return result;
}