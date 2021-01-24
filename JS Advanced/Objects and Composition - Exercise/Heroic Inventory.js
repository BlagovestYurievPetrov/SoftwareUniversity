function heroic(arr){
    let heroes = [];
    for (const hero of arr) {
        let createdHero = {};
        let [name,age,items] = hero.split(' / ');
        age = Number(age);
        createdHero.name = name;
        createdHero.level = age;
        items = items ? items.split(', ') : [];
        createdHero.items = items;
        heroes.push(createdHero);
    }
return JSON.stringify(heroes);
}