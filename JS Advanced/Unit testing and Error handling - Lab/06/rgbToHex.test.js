const rgbToHexColor = require('./rgbToHex.js');
const assert = require('chai').assert;

describe('rgb', () => {
    it('test lower red', ()=>{
        assert.equal(rgbToHexColor(-4,2,3),undefined);
    }) ;

    it('test lower green', ()=>{
        assert.equal(rgbToHexColor(1,-2,3),undefined);
    }) ;
    it('test lower blue', ()=>{
        assert.equal(rgbToHexColor(1,2,-3),undefined);
    }) ;
    it('test higher red', ()=>{
        assert.equal(rgbToHexColor(300,2,3),undefined);
    }) ;
    it('test higher green', ()=>{
        assert.equal(rgbToHexColor(1,300,3),undefined);
    }) ;
    it('test higher blue', ()=>{
        assert.equal(rgbToHexColor(1,3,300),undefined);
    }) ;

    it('test invalid red', ()=>{
        assert.equal(rgbToHexColor('red',2,3),undefined);
    }) ;

    it('test invalid green', ()=>{
        assert.equal(rgbToHexColor(2,'green',3),undefined);
    }) ;

    it('test invalid blue', ()=>{
        assert.equal(rgbToHexColor(2,3,'blue'),undefined);
    }) ;

    it('test valid input', ()=>{
        assert.equal(rgbToHexColor(1,1,1),'#010101');

    }) ;

    it('test invalid input with 2 args', ()=>{
        assert.equal(rgbToHexColor(1,1),undefined);

    }) ;
    
    it('test invalid input with 1 args', ()=>{
        assert.equal(rgbToHexColor(1),undefined);

    }) ;

    it('test invalid input with 0 args', ()=>{
        assert.equal(rgbToHexColor(),undefined);

    }) ;
});