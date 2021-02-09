const {expect,assert} = require('chai');
const isSymmetric = require('./CheckSymetry');
describe ('Symetry', ()=>{
    it ('test with no array', () => {
        assert.equal(isSymmetric(2),false);
    });

    it('test with non symmetric array', () => {
        assert.equal(isSymmetric([1,2]),false);
    });

    it('test with symmetric array', () => {
        assert.equal(isSymmetric([1,1]),true);
    });
});


