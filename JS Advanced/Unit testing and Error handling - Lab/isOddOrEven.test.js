const assert = require('chai').assert;
const isOddOrEven = require('./isOddOrEven');

describe('is odd or even', ()=>{
    it('parameter is not a string', ()=>{
        assert.equal(isOddOrEven(2),undefined);
    })
    
    it('parameter is odd string', ()=>{
        assert.equal(isOddOrEven('odd'),'odd');
    })
    
    it('parameter is even string', ()=>{
        assert.equal(isOddOrEven('even'),'even');
    })

})