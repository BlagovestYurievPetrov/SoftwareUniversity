const pizzUni = require('./PizzaPlace');
const assert = require('Chai').assert;
const expect = require('Chai').expect;

describe('Pizzuni Tests',()=>{
    it('make a pizza and drink order',()=>{
        assert.equal(pizzUni.makeAnOrder({orderedPizza:'djob',orderedDrink:'matenica'}),'You just ordered djob and matenica.');
    });
    it('dont order pizza => throw error with msg',()=>{
    assert.throw(function() { pizzUni.makeAnOrder({orderedDrink:'matenica'}) }, Error, 'You must order at least 1 Pizza to finish the order.');
    });
    it('make only pizza order',()=>{
        assert.equal(pizzUni.makeAnOrder({orderedPizza:'djob'}),'You just ordered djob');
    });
    it('get remaining for one pizza still preparing',()=>{
        assert.equal(pizzUni.getRemainingWork([{pizzaName: 'djob', status: 'ready'}, {pizzaName: 'evreiska', status: 'preparing'}]),`The following pizzas are still preparing: evreiska.`);
    });
    it('get remaining for one two pizzas still preparing',()=>{
        assert.equal(pizzUni.getRemainingWork([{pizzaName: 'djob', status: 'ready'}, {pizzaName: 'evreiska', status: 'preparing'},{pizzaName: 'margarita', status: 'preparing'}]),`The following pizzas are still preparing: evreiska, margarita.`);
    });
    it('all pizzas are ready',()=>{
        assert.equal(pizzUni.getRemainingWork([{pizzaName: 'djob', status: 'ready'}, {pizzaName: 'evreiska', status: 'ready'},{pizzaName: 'margarita', status: 'ready'}]),'All orders are complete!');
    });
    it('order type Carry out',()=>{
        assert.equal(pizzUni.orderType(1,'Carry out'),0.9);
    });
    it('order type Delivery',()=>{
        assert.equal(pizzUni.orderType(10,'Delivery'),10);
    });

})