let { Repository } = require("./solution.js");
let assert = require('chai').assert;

describe("Tests …", function () {
    describe("TODO …", function () {
        it("name", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
           assert.equal(repo.props.name,'string');
        });
        it("age", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
           assert.equal(repo.props.age, 'number');
        });
        it("birthday", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
           assert.equal(repo.props.birthday,'object');
        });
        it("map length", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
           assert.equal(repo.count,0);
        });
        it("map length", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
           assert.equal(repo.nextId(),0);
        });
        it("add invalid entity with invalid property", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: 1991
            };
            
            assert.throw(function() {repo.add(entity)}, TypeError);
        });
        it("add invalid entity with missing property", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29
            };
            
            assert.throw(function() {repo.add(entity)}, Error);
        });
        it("add entity and check id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            nextEntity = {
                name: 'Kiril',
                age: 22,
                birthday: new Date(1992, 0, 7)
            }
            
            assert.equal(repo.add(entity),0);
            assert.equal(repo.add(entity),1);
        });
        it("add entity and check if its in map", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            nextEntity = {
                name: 'Kiril',
                age: 22,
                birthday: new Date(1992, 0, 7)
            }
            repo.add(entity);
            repo.add(nextEntity);
            assert.equal(repo.getId(0),entity);
            assert.equal(repo.getId(1),nextEntity);
        });
        it("check invalid id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            repo.add(entity);
            assert.throw(function() {repo.getId(22)}, Error);
        });
        it("check to update entity with invalid id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            nextEntity = {
                name: 'Kiril',
                age: 22,
                birthday: new Date(1992, 0, 7)
            }
            repo.add(entity);
            assert.throw(function() {repo.update(22,nextEntity)}, Error);
        });
        it("check to update entity with invalid entity", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            nextEntity = {
                name: 'Kiril',
                age: 22
            }
            repo.add(entity);
            assert.throw(function() {repo.update(0,nextEntity)}, Error);
        });
        it("delet invalid id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            nextEntity = {
                name: 'Kiril',
                age: 22,
                birthday: new Date(1992, 0, 7)
            }
            repo.add(entity);
            assert.throw(function() {repo.del(8218)}, Error)
        });
        it("delet valid id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name: 'Stamat',
                age: 29,
                birthday: new Date(1998, 0, 7)
            };
            repo.add(entity);
            repo.del(0);
            assert.equal(repo.count,0)
        });
        it("add invalid entity with invalid property name", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repo = new Repository(properties);
            entity = {
                name1: 'Stamat',
                age: 29,
                birthday: 1991
            };
            
            assert.throw(function() {repo.add(entity)}, Error);
        });
    });
});
