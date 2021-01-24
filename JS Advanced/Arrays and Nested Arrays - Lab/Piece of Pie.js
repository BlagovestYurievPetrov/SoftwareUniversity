function pie(arr,str1,str2) {
    const firstIndex = arr.indexOf(str1);
    const secondIndex = arr.indexOf(str2)+1;
    const arr2 = arr.slice(firstIndex,secondIndex);
    return arr2;
  }