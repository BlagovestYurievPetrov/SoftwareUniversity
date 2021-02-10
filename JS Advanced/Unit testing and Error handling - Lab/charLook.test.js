const assert = require('chai').assert;
const lookupChar = require('./charLook');

describe ('char lookup',()=>{
    it('string is invalid - expect undefined', ()=>{
        assert.equal(lookupChar(221412412,2),undefined);
    });
    it('index is invalid - expect undefined', ()=>{
        assert.equal(lookupChar('221412412','hgfgj'),undefined);   
    });
    it('string length is smaller than index - expect Incorrect index', ()=>{
        assert.equal(lookupChar('abc',5),'Incorrect index');
    });
    it('index is smaller than 0 - expect Incorrect index', ()=>{
        assert.equal(lookupChar('abc',-5),'Incorrect index');
    });
    it('empty string', ()=>{
        assert.equal(lookupChar('',1),'Incorrect index');
    });
    it('float point index', ()=>{
        assert.isUndefined(lookupChar('abc',1.1));
    });
    it('happy path', ()=>{
        assert.equal(lookupChar('abc',1),'b');
    });
});