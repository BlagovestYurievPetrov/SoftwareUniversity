const numberOperations = require('./numberOperations.js')
const assert = require('chai').assert;

describe('Tests',()=>{
    // podai mu NAN
    it('powNum',()=>{
        assert.equal(numberOperations.powNumber(2),4);
    })
    it('Numchecker with NAN',()=>{
        assert.throw(function(){numberOperations.numberChecker('a')},Error,'The input is not a number!');
    })
    it('Numchecker with <100',()=>{
        assert.equal(numberOperations.numberChecker(99),'The number is lower than 100!')
    })
    it('Numchecker with <100 float',()=>{
        assert.equal(numberOperations.numberChecker(99.1),'The number is lower than 100!')
    })
    it('Numchecker with <100 float string',()=>{
        assert.equal(numberOperations.numberChecker('99.1'),'The number is lower than 100!')
    })
    it('Numchecker with >100',()=>{
        assert.equal(numberOperations.numberChecker(102),'The number is greater or equal to 100!')
    })
    it('Numchecker with 100',()=>{
        assert.equal(numberOperations.numberChecker(100),'The number is greater or equal to 100!')
    })
    // sum 2 arrays
    it('two arr with same length',()=>{
        assert.deepEqual(numberOperations.sumArrays([1, 2],[3, 4]),[4,6]);
    })
    it('two arr with different length',()=>{
        assert.deepEqual(numberOperations.sumArrays([1, 2, 3],[3, 4]),[4, 6, 3]);
    })
    

})


