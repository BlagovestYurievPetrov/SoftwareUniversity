function cars(order) {
    const returnCar = {};
    const carriageList = {};
   // model assignment
   returnCar.model = order['model'];
   let hp = order['power'];
   // engine assignment
   if(hp<=90){
     returnCar.engine = {power: 90,
       volume: 1800};
   }
   if (hp>90 && hp<=120){
     returnCar.engine = {power: 120,
       volume: 2400};
   }
   if (hp>120 && hp<=200){
     returnCar.engine = {power: 200,
       volume: 3500};
   }
   
   //assign carriage
   const carr = order.carriage;
   const col = order.color;
   returnCar.carriage = {type: carr, color: col};
   //wheels 
   let wSize = order.wheelsize;
   if (wSize%2==0){
     wSize--;
   }
   
   returnCar.wheels = [wSize,wSize,wSize,wSize];
   
   //debugger;
   return returnCar;
   }
   
   
   