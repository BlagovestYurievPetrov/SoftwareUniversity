let dealership = require('./deal');
let assert = require('chai').assert;

describe('Tests',()=>{
    // may need to be tested for invalid inputs
    // testing newCarCostMethod
    it('return old car',()=>{
        assert.equal(dealership.newCarCost('Audi A4 B8',20000),5000);
    })
    it('dont return old car',()=>{
        assert.equal(dealership.newCarCost('Benz',20000),20000);
    })
    //testing carEquipment method
    //params are two arrays
    it('carEquip with two params',()=>{
        assert.deepEqual(dealership.carEquipment(['bira','pica'],[0,1]),['bira','pica']);
    })
    it('carEquip with 1 param',()=>{
        assert.deepEqual(dealership.carEquipment(['bira','pica'],[0]),['bira']);
    })
    it('carEquip with empty arr params',()=>{
        assert.deepEqual(dealership.carEquipment(['bira','pica'],[]),[]);
    })
    //testing EU cat.
    //param is number
    it('category higher than 4',()=>{
        assert.equal(dealership.euroCategory(5),`We have added 5% discount to the final price: 14250.`);
    });
    it('category lower than 4',()=>{
        assert.equal(dealership.euroCategory(2),'Your euro category is low, so there is no discount from the final price!');
    })
    it('category equal to 4',()=>{
        assert.equal(dealership.euroCategory(4),`We have added 5% discount to the final price: 14250.`);
    });
    it('category is float',()=>{
        assert.equal(dealership.euroCategory(4.4),`We have added 5% discount to the final price: 14250.`);
    });
})