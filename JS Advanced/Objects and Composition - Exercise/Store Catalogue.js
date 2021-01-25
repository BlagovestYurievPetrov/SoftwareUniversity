function store(arr){
    let dictioanary = {};
    arr.sort().map((x)=>{
        let [name, price] = x.split(" : ");
        let firstLetter = name[0];
        if(dictioanary[firstLetter] === undefined){
            dictioanary[firstLetter]=[];
        }
        dictioanary[firstLetter].push({name,price});
    })
    Object.entries(dictioanary).map((y)=>{
        console.log(y[0]);
        Object.entries(y[1]).map((z)=>{
             console.log(`  ${z[1]['name']}: ${z[1]['price']}`);
         })
    });
}