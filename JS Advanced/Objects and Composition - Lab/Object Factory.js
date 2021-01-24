function factory(library,orders){
const finishedOrders = [];
for (let order of orders){
    const composed = Object.assign({},order.template);
    for (let i of order.parts) {
      composed[i] = library[i]; 
    }
    finishedOrders.push(composed);
}
 return finishedOrders;
}