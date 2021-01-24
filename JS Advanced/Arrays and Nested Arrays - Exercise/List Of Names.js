function sorting(input){
    let arr = input.sort((a,b) =>a.localeCompare(b));
      for (let i = 0; i<arr.length; i++){
      console.log(`${i+1}.${arr[i]}`);
      }
    }