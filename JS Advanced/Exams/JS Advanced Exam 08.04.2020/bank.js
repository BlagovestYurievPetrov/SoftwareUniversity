class Bank {
    #bankname;
    constructor(bankName){
        this.#bankname = bankName;
        this.allCustomers = [];
    }
    newCustomer(customer){
        if (this.allCustomers.find(x => x.personalId === customer.personalId)){
            throw new Error(`${customer.firstName} ${customer.lastName} is already our customer!`)
        }
        this.allCustomers.push(customer);
        customer.totalMoney = 0;
        customer.transactions = [];
        return customer;
    }
    depositMoney(personalId,amount){
        let currCustomer = this.allCustomers.find(x => x.personalId === personalId);
        if (!currCustomer){
            throw new Error(`We have no customer with this ID!`);
        }
        currCustomer.totalMoney += amount;
        currCustomer.transactions.unshift({type:'deposit',value:amount});
        return `${currCustomer.totalMoney}$`
    }
    withdrawMoney(personalId,amount){
        let currCustomer = this.allCustomers.find(x => x.personalId === personalId);
        if (!currCustomer){
            throw new Error(`We have no customer with this ID!`);
        }
        if (currCustomer.totalMoney < amount){
            throw new Error(`${currCustomer.firstName} ${currCustomer.lastName} does not have enough money to withdraw that amount!`)
        }
        currCustomer.totalMoney -= amount;
        currCustomer.transactions.unshift({type:'withdrew',value:amount});
        return `${currCustomer.totalMoney}$`;
    }
    customerInfo(personalId){
        let currCustomer = this.allCustomers.find(x => x.personalId === personalId);
        if (!currCustomer){
            throw new Error(`We have no customer with this ID!`);
        }
        let out = `Bank name: ${this.#bankname}\n`;
        out += `Customer name: ${currCustomer.firstName} ${currCustomer.lastName}\n`;
        out += `Customer ID: ${currCustomer.personalId}\n`;
        out += `Total Money: ${currCustomer.totalMoney}$\n`;
        out += 'Transactions:\n';
        let counter = currCustomer.transactions.length;

        currCustomer.transactions.map((x)=>{
            if (x.type === 'deposit'){
                out += `${counter}. ${currCustomer.firstName} ${currCustomer.lastName} made deposit of ${x.value}$!\n`
            } else {
                out += `${counter}. ${currCustomer.firstName} ${currCustomer.lastName} withdrew ${x.value}$!\n`
            }
            counter--;
        })


        return out.trim();
    }
}