function createRecord(name, population, treasury) {
    const city = {
      name: name,
      population: population,
      treasury: treasury,
      taxRate: 10,
      collectTaxes() {
        this.treasury += this.population * this.taxRate;
                    },
      applyGrowth(percent) {
        this.population += Math.floor(this.population * (percent / 100));
      }, 
      applyRecesion(percent) {
        this.treasury -= Math.ceil(this.treasury * (percent / 100));
      }, 
    };
      
    return city;
  }