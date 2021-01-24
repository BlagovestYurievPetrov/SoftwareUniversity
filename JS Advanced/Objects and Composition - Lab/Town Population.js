function population(arr) {
    let towns = {};
      
      for (let key of arr) {
      let [name,popul] = key.split(" <-> ");
      popul = Number(popul);
          if (towns[name] != undefined){
        popul += towns[name];
        }
      towns[name] = popul;
      }
        for (let town in towns){
        console.log(`${town} : ${towns[town]}`);
        
        }
      
    }