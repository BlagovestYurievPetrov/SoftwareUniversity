const HolidayPackage = require('./holiday.js');
const assert = require('chai').assert;

describe('Tests',()=>{
    it('create instance of class',()=>{
        assert.deepEqual(new HolidayPackage('Petrich','Summer'),{vacationers: [], destination: 'Petrich', season:'Summer', _insuranceIncluded: false});

    })
    //addVacationer(vacationerName) {
    it('add vacationer that isnt string',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.throw(function (){h.addVacationer(21)},Error, 'Vacationer name must be a non-empty string');
    })
    it('add vacationer that is empty string',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.throw(function (){h.addVacationer('')},Error, 'Name must consist of first name and last name');
    })
    it('add vacationer that is whitespace',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.throw(function (){h.addVacationer(' ')},Error, 'Vacationer name must be a non-empty string');
    })
    it('add vacationer that is with one names',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.throw(function (){h.addVacationer('Vasko')},Error, 'Name must consist of first name and last name');
    })
    it('add corect vacationer with two names',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        assert.equal(h.vacationers.length,1);
    })
    //  showVacationers()
    it('vacationeers is empty',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.equal(h.showVacationers(),'No vacationers are added yet'); 
    })
    it('vacationeers has 1',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        assert.equal(h.showVacationers(),"Vacationers:\n" + `Vasko Shteryanov`); 
    })
    it('vacationeers has 2',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        h.addVacationer('Mario Grozdanov');
        assert.equal(h.showVacationers(),"Vacationers:\n" + `Vasko Shteryanov\n`+'Mario Grozdanov'); 
    })
    it ('get false insurance',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        assert.isFalse(h.insuranceIncluded);
    })
   /*
    it ('set insurance to non boolean',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        let vasko = 'Vasko Shteryanov';
        h.addVacationer(vasko);
        assert.throw(function(){vasko.insuranceIncluded(123)},Error,'Insurance status must be a boolean');
    })
    it ('set insurance to true',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        h.insuranceIncluded(true);
        assert.isTrue(h.insuranceIncluded);
    })
 */
    it ('generate package for less than 1 vacationeer',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        assert.throw(function(){h.generateHolidayPackage()},Error,'There must be at least 1 vacationer added');
    })
    it ('generate package for 1 vacationeer during summer with no insurance',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        assert.equal(h.generateHolidayPackage(),"Holiday Package Generated\n" +
        "Destination: " + "Petrich\n" +
        "Vacationers:\n" + `Vasko Shteryanov` + "\n" +
        "Price: " + '600')
    })

    it ('generate package for 1 vacationeer during autumn with no insurance',()=>{
        let h = new HolidayPackage('Petrich','Autumn');
        h.addVacationer('Vasko Shteryanov');
        assert.equal(h.generateHolidayPackage(),"Holiday Package Generated\n" +
        "Destination: " + "Petrich\n" +
        "Vacationers:\n" + `Vasko Shteryanov` + "\n" +
        "Price: " + '400')
    })

    it ('generate package for 1 vacationeer during summer with insurance',()=>{
        let h = new HolidayPackage('Petrich','Summer');
        h.addVacationer('Vasko Shteryanov');
        h.insuranceIncluded = true;
        assert.equal(h.generateHolidayPackage(),"Holiday Package Generated\n" +
        "Destination: " + "Petrich\n" +
        "Vacationers:\n" + `Vasko Shteryanov` + "\n" +
        "Price: " + '700')
    })

})