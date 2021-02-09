const {sum} = require('./sumNumbers');
const {expect, assert} = require('chai');

describe ('sum', () => {
    it('number array', () => {
        assert.equal(sum([1,2]),3);
    });

    it('string array', () => {
        assert.equal(sum(['1','2']),3);
    });

    it('empyt array', () => {
        assert.equal(sum([]),0);
    });
});