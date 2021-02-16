class ChristmasDinner {
    constructor(budget){
        //validate budget might need to be moved
        if(budget<0){
            throw new Error('The budget cannot be a negative number');
        }
        this.budget = budget;
        this.dishes = [];
        this.products = [];
        this.guests = {};
    }
    shopping(product){
        let [name,value] = product;
        if (this.budget<product[1]){
            throw new Error('Not enough money to buy this product');
        }
        this.products.push(name);
        this.budget -= value;
        return `You have successfully bought ${name}!`;
    }

    recipes(recipe){
        // input is something like this { recipeName: string, productsList: array of strings };
        let bool = true;
        recipe.productsList.map((x)=>{
            if(!this.products.includes(x)){
                bool = false;
            }
        })
        if(bool){
            this.dishes.push(recipe);
            return `${recipe.recipeName} has been successfully cooked!`
        } else {
            return 'We do not have this product';
        }
    }

    inviteGuests(name,dish){
        //check if guest is already in guests
        if(this.guests.hasOwnProperty(name)){
            throw new Error("This guest has already been invited");
        }
        //check if dish is in dishes

        let found = this.dishes.some((x)=>x.recipeName === dish);
        if (found){
        //add guest
        this.guests[name] = dish;
        return `You have successfully invited ${name}!`
        } else {
            throw new Error("We do not have this dish");
        }
    }

    showAttendance(){
        let out ='';
        for (const [guest,dish] of Object.entries(this.guests)){
            out += `${guest} will eat ${dish}, which consists of `
            let found = this.dishes.find((x)=>x.recipeName === dish);
            out += `${found.productsList.join(', ')}`
            out +=`\n`
        }
        return out.trim();
    }
}