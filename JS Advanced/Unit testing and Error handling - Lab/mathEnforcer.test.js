const mathEnforcer = require('./mathEnforcer');
const assert = require('chai').assert;
const expect = require('chai').expect;

describe ('add Five',()=>{
    it('not a nubmer', () => {
        assert.isUndefined(mathEnforcer.addFive('a'));
    })
    it('a nubmer', () => {
        assert.equal(mathEnforcer.addFive(5),10);
    })
    it('a negative nubmer', () => {
        assert.equal(mathEnforcer.addFive(-5),0);
    })
    it('float', () => {
        expect(mathEnforcer.addFive(2)).to.be.closeTo(7,0.01);
    })
});

describe('substract 10',()=>{
    it('not a nubmer', () => {
        assert.isUndefined(mathEnforcer.subtractTen('a'));
    })
    it('a nubmer', () => {
        assert.equal(mathEnforcer.subtractTen(5),-5);
    })
    it('a negative nubmer', () => {
        assert.equal(mathEnforcer.subtractTen(-5),-15);
    })
    it('float', () => {
        expect(mathEnforcer.subtractTen(12)).to.be.closeTo(2,0.01);
    })
})

describe('sum',() =>{
    it('first not a nubmer', () => {
        assert.isUndefined(mathEnforcer.sum('a',2));
    })
    it('second not a nubmer', () => {
        assert.isUndefined(mathEnforcer.sum(2,'a'));
    })
    it('both not a nubmer', () => {
        assert.isUndefined(mathEnforcer.sum('a','c'));
    })
    it('a nubmer', () => {
        assert.equal(mathEnforcer.sum(5,3),8);
    })
    it('a negative nubmer', () => {
        assert.equal(mathEnforcer.sum(-5,-3),-8);
    })
    it('float', () => {
        expect(mathEnforcer.sum(3.14,1.2)).to.be.closeTo(4.34,0.01);
    })
})

