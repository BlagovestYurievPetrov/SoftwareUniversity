function lowestPrice(arr){
    let products = {};
    let min = Number.POSITIVE_INFINITY;
    // Fill by product
    for (const line of arr) {
        let [name,product,price] = line.split(' | ');
        price = Number(price);
        if (products[product]=== undefined){
            products[product] = [];
        }
        let produce = {[name]:price,name,price};
            products[product].push(produce);
    }
//    sort lowest
Object.entries(products).map((x)=>{ 
    const sorted = (x[1].sort((a,b) => a.price-b.price))
    console.log(`${x[0]} -> ${(x[1][0].price)} (${x[1][0].name})`);
})
}
