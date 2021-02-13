class Parking {
    constructor(capacity){
        this.capacity = capacity;
        this.vehicles = [];
    }
    addCar(carModel, carNumber){
        if (this.vehicles.length>=this.capacity){
            throw new Error('Not enough parking space.');
        }
        let car = {
            carModel: carModel,
            carNumber: carNumber,
            payed: false
        };
        this.vehicles.push(car);
        return `The ${carModel}, with a registration number ${carNumber}, parked.`;
    }
    removeCar(carNumber){
        let found = this.vehicles.find(e=>e.carNumber===carNumber);
        if(!found){
            throw new Error("The car, you're looking for, is not found.");
        } else {
            if (!found.payed){
                throw new Error(`${carNumber} needs to pay before leaving the parking lot.`);
            } else {
                let carIndex = this.vehicles.indexOf(found);
                this.vehicles.splice(carIndex,1);
                return `${carNumber} left the parking lot.`
            }
        }

    }
    pay(carNumber){
        let found = this.vehicles.find(e=>e.carNumber===carNumber);
        if (!found){
            throw new Error(`${carNumber} is not in the parking lot.`); 
        } else {
            if (found.payed){
                throw new Error(`${carNumber}'s driver has already payed his ticket.`);
               } else {
                   found.payed = true;
                return (`${carNumber}'s driver successfully payed for his stay.`);
               }
        }
    }

    getStatistics(carNumber){
    if (carNumber){
        let found = this.vehicles.find(e=>e.carNumber===carNumber);
        if (!found){
            throw new Error(`${carNumber} is not in the parking lot.`); 
        } else {
            let out = '';
            out = `The Parking Lot has ${this.capacity - this.vehicles.length} empty spots left.\n`;
            let payment = found.payed ? 'Has payed' : 'Not payed';
            out += `${found.carModel} == ${found.carNumber} - ${payment}`;
            return out.trim();
        }
    } else {
        let sorted = this.vehicles.sort((a,b)=>a.carModel.localeCompare(b.carModel));
        let out = '';
        out=`The Parking Lot has ${this.capacity - this.vehicles.length} empty spots left.\n`
        sorted.map((x)=>{
            let payment = x.payed?'Has payed':'Not payed'; 
            out += `${x.carModel} == ${x.carNumber} - ${payment}\n`;
        });
        return out.trim();
        }
    }
    
}