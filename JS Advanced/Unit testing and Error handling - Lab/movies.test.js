const assert = require('Chai').assert;
const ChristmasMovies = require('./02. Christmas Movies_Resources');

describe ('Tests',()=>{
    it('instance of object',()=>{
        let movies = new ChristmasMovies;
        assert.deepEqual(movies,{movieCollection:[], watched:{}, actors:[]});
    })
    it ('buy movie already owned throw error',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        assert.throw(function() { movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']) }, Error, `You already own Goodfellas in your collection!`);
    })
    it ('buy movie you dont own',()=>{
        let movies = new ChristmasMovies;
        assert.equal(movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']),`You just got Goodfellas to your collection in which DeNiro, Pesci are taking part!`);

    })
    it ('buy movie you dont own and check length',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas');
        assert.equal(movies.movieCollection.length, 1);

    })
    it ('discard non existing movie => throws Error',()=>{
        let movies = new ChristmasMovies;
        assert.throw(function() { movies.discardMovie('Goodfellas', ['DeNiro', 'Pesci']) }, Error, `Goodfellas is not at your collection!`);

    })
    it ('discard existing movie => if watched returns string',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.watchMovie('Goodfellas');
        assert.equal(movies.discardMovie('Goodfellas'),`You just threw away Goodfellas!`);

    })
    //come back here
    it ('discard existing movie => if watched watched size shrinks by 1',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.watchMovie('Goodfellas');
        movies.discardMovie('Goodfellas');
        assert.deepEqual(movies.watched,{});

    })
    it ('discard existing movie => if not watched returns error',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        assert.throw(function() { movies.discardMovie('Goodfellas', ['DeNiro', 'Pesci']) }, Error, `Goodfellas is not watched!`);

    })
    it ('try watching not existing movie => throw Error',()=>{
        let movies = new ChristmasMovies;
        assert.throw(function() { movies.watchMovie('Goodfellas') }, Error, 'No such movie in your collection!');
    })
    it ('watch movie for the first time',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.watchMovie('Goodfellas');
        assert.equal(movies.watched['Goodfellas'],1);
    })
    it ('watch movie for the second time',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        assert.equal(movies.watched['Goodfellas'],2);
    })
    it ('pick favorite without watching anything',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        assert.throw(function() { movies.favouriteMovie('Goodfellas') }, Error, 'You have not watched a movie yet this year!');
    })
    it ('pick favorite watched two times',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        assert.equal(movies.favouriteMovie(),`Your favourite movie is Goodfellas and you have watched it 2 times!`);
    })
    it ('pick favorite watched three times amongs other movies',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.buyMovie('No country for old men', ['Bardem', 'Pesci']);
        movies.buyMovie('Inception', ['DiCaprio', 'Pesci']);
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        movies.watchMovie('No country for old men');
        movies.watchMovie('Inception');
        assert.equal(movies.favouriteMovie(),`Your favourite movie is Goodfellas and you have watched it 3 times!`);
    })

    it ('try most starred actor with empty collection => throw Error',()=>{
        let movies = new ChristmasMovies;
        assert.throw(function() { movies.mostStarredActor() }, Error, 'You have not watched a movie yet this year!');
    })
    it ('try most starred actor with 1 participance',()=>{
        let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro']);
        assert.equal(movies.mostStarredActor(),`The most starred actor is DeNiro and starred in 1 movies!`);
    })
    it ('try most starred actor with many participances',()=>{
    let movies = new ChristmasMovies;
        movies.buyMovie('Goodfellas', ['DeNiro', 'Pesci']);
        movies.buyMovie('No country for old men', ['Bardem', 'Pesci']);
        movies.buyMovie('Inception', ['DiCaprio', 'Pesci']);
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        movies.watchMovie('Goodfellas');
        movies.watchMovie('No country for old men');
        movies.watchMovie('Inception');
        assert.equal(movies.mostStarredActor(),`The most starred actor is Pesci and starred in 3 movies!`);
    })
})