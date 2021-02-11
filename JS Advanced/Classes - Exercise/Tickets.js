function sortTickets (arr,sortCriteria) {
    class Ticket {
        constructor(destination,price,status){
        this.destination = destination;
        this.price = Number(price);
        this.status = status;
        }
    }
    let out = [];
    arr.map((x)=>{
        let split = x.split('|');
        out.push(new Ticket(split[0],split[1],split[2]));
    });
    if(sortCriteria !== 'price'){
        out.sort((y,z)=>{
            return y[sortCriteria].localeCompare(z[sortCriteria]);
        });
    } else {
        out.sort((y,z)=>{
            return y[sortCriteria] - z[sortCriteria];
        })
    }
    return out;
}