function doubleSort(input) {
    let sortedArr =  input.sort((a,b)=>{
    if (a.length === b.length){
    return a.localeCompare(b);
    }
    return a.length - b.length;
    });
    for (let i = 0; i<sortedArr.length; i++){
        console.log(sortedArr[i]);
    }
                      
                      
                      
    }