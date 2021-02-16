const pizzUni = require('./PizzaPlace');
const assert = require('Chai').assert;

describe('Pizzuni Tests',()=>{
    it('first test',()=>{
        assert.equal(pizzUni.makeAnOrder({orderedPizza:'djob',orderedDrink:'matenica'}),'You just ordered djob and matenica.');
    });

})