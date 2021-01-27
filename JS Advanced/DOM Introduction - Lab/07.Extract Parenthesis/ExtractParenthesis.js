function extract(content) {
let txt = document.getElementById(content).textContent;
let pattern = /\(([^)]+)\)/g;
let result = [];

let match = pattern.exec(txt);
while(match){
    result.push(match[1]);
    match = pattern.exec(txt);
}
return result.join('; ');
}